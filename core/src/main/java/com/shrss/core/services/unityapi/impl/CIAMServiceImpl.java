package com.shrss.core.services.unityapi.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;
import com.google.gson.Gson;
import com.shrss.core.services.unityapi.CIAMService;
import com.shrss.core.services.unityapi.HttpClientFactory;
import com.shrss.core.services.unityapi.StringObjectResponseHandler;
import com.shrss.core.services.unityapi.pojo.CIAMTokenResponse;

@Component(service = CIAMService.class)
@Designate(ocd = CIAMServiceConfig.class)
public class CIAMServiceImpl implements CIAMService {

	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	private static final String CONTENT_TYPE = "Content-Type";

	private static final Logger log = LoggerFactory.getLogger(CIAMServiceImpl.class);

	@Reference
	private HttpClientFactory httpClientFactory;

	private static final StringObjectResponseHandler HANDLER = new StringObjectResponseHandler();

	 private String tokenPath;

	 private String logouturi;

	 private String extendedSession;

	  private String guestAPIClientId;

	  private String guestAPIClientSecret;
	
 CIAMTokenResponse guestCachedToken;

	@Activate
	@Modified
	protected void activate(CIAMServiceConfig config) throws Exception {
		if (config.ciamHostName() == null) {
			log.debug("Configuration is not valid. Hostname is mandatory.");
			throw new IllegalArgumentException("Configuration is not valid. Hostname is mandatory.");
		}

		this.tokenPath = StringUtils.join(config.ciamHostName(), config.tokenPath());
		this.logouturi = StringUtils.join(config.ciamHostName(), config.logouturi());
		this.extendedSession = StringUtils.join(config.ciamHostName(), config.extendedSession());
		this.guestAPIClientId = config.guestAPIClientId();
		this.guestAPIClientSecret = config.guestAPIClientSecret();
		getGuestCIAMTokenService();
	}

	@Override
	public synchronized String getGuestCIAMTokenService() {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("scope", "device_adobe1");
		String response = null;
		try {
			  response = httpClientFactory.getExecutor().auth(guestAPIClientId, guestAPIClientSecret)
					.execute(httpClientFactory.post(buildURL(tokenPath, params), Boolean.FALSE).setHeader(CONTENT_TYPE,
							APPLICATION_X_WWW_FORM_URLENCODED)).handleResponse(HANDLER);
					 Gson gson = new Gson();
					 guestCachedToken = gson.fromJson(response, CIAMTokenResponse.class);				 
		} catch (IOException e) {
			log.error("ClientProtocolException while invoking API, {}", e.getMessage(), e);
		}
		return response;
	}

	/**
	 * Constructs a URL by appending the provided parameters to the given API
	 * endpoint URI. *
	 * 
	 * @param apiEndpointURI the API endpoint URI
	 * @param parameterMap   a map containing the parameters to be appended to the
	 *                       URL
	 * @return the constructed URL with parameters appended if provided, else
	 *         returns the original API endpoint URI
	 */
	public String buildURL(@NotNull String apiEndpointURI, Map<String, String> parameterMap) {
		if (MapUtils.isNotEmpty(parameterMap)) {
			URIBuilder builder = new URIBuilder();
			List<NameValuePair> nvpList = new ArrayList<>(parameterMap.size());
			parameterMap.entrySet().stream().filter(entry -> StringUtils.isNoneBlank(entry.getKey(), entry.getValue()))
					.forEach(entry -> nvpList.add(new BasicNameValuePair(entry.getKey(), entry.getValue())));
			return returnApiEndpointURI(apiEndpointURI, builder, nvpList);
		}
		return returnApiEndpointURI(apiEndpointURI, null, null);
	}

	/**
	 * 
	 * Constructs and returns an API endpoint URI.
	 * 
	 * If a URIBuilder and a list of parameters are provided, the parameters are
	 * added to the URI. If the URIBuilder is null, the original API endpoint URI is
	 * returned.
	 *
	 * @param apiEndpointURI the original API endpoint URI
	 * @param builder        the URIBuilder to add parameters to the URI
	 * @param nvpList        the list of parameters to be added to the URI
	 * @return the constructed API endpoint URI with parameters appended if
	 *         provided, else returns the original API endpoint URI
	 */
	private String returnApiEndpointURI(String apiEndpointURI, URIBuilder builder, List<NameValuePair> nvpList) {
		return StringUtils.join(apiEndpointURI,
				null != builder ? builder.addParameters(nvpList).toString() : StringUtils.EMPTY);

	}

	@Override
	public String getCIAMLogoutService() {

		return null;
	}

	@Override
	public String getCIAMSessionService(String idpSessionKey) {
		Map<String, String> params = new HashMap<>();
		params.put("idpSessionKey",idpSessionKey);
		String response = null;
		try {
			response = httpClientFactory.getExecutor().auth(guestAPIClientId, guestAPIClientSecret)
					.execute(httpClientFactory.get(buildURL(extendedSession, params), Boolean.FALSE)
							.setHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED))
					.handleResponse(HANDLER);
		} catch (IOException e) {
			log.error("ClientProtocolException while invoking API, {}", e.getMessage(), e);
		}
		return response;
	}

	@Override
	public String getClientCIAMTokenService() {

		return null;
	}

	@Override
	public String getCIAMTokenFromRefreshToken() {

		return null;
	}

	@Override
	public CIAMTokenResponse getCachedGuestToken() {
		if(guestCachedToken!=null && StringUtils.isNotBlank(guestCachedToken.getAccessToken())) {
			return guestCachedToken;
		}
		return null;
	}

}
