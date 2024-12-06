package com.shrss.core.services.unityapi.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;
import com.shrss.core.services.unityapi.APIInvoker;
import com.shrss.core.services.unityapi.CIAMService;
import com.shrss.core.services.unityapi.HttpClientFactory;
import com.shrss.core.services.unityapi.StringObjectResponseHandler;

@Component(service = APIInvoker.class)
public class SHRSSAPIInvoker implements APIInvoker {
	private static final String CONTENT_TYPE = "Content-Type";
	private static final Logger LOGGER = LoggerFactory.getLogger(SHRSSAPIInvoker.class);
	private static final StringObjectResponseHandler HANDLER = new StringObjectResponseHandler();

	@Reference
	private HttpClientFactory httpClientFactory;
	
	@Reference
	private CIAMService ciamService;

	@Override
	public String invokeUnityLocationAPI() {
		String response =  null;
		try {
			if(ciamService.getCachedGuestToken() == null) {
				ciamService.getGuestCIAMTokenService();				
			}else {
				LOGGER.info("Guest Token is already available in cache");
			}
			response =  httpClientFactory.getExecutor().execute(httpClientFactory
						.get(buildURL("/location", null), Boolean.TRUE).addHeader("Authorization", "Bearer "+ciamService.getCachedGuestToken().getAccessToken()))
						.handleResponse(HANDLER);

		} catch (IOException exception) {
			LOGGER.debug("IOException while invoking API, {}", exception.getMessage(), exception);
		}
		return response;
	}

	@Override
	public String buildURL(@NotNull String apiEndpointURI, Map<String, String> parameterMap)
			throws MalformedURLException {
		if (MapUtils.isNotEmpty(parameterMap)) {
			URIBuilder builder = new URIBuilder();
			List<NameValuePair> nvpList = new ArrayList<>(parameterMap.size());
			parameterMap.entrySet().stream().filter(entry -> StringUtils.isNoneBlank(entry.getKey(), entry.getValue()))
					.forEach(entry -> nvpList.add(new BasicNameValuePair(entry.getKey(), entry.getValue())));
			return returnApiEndpointURI(apiEndpointURI, builder, nvpList);
		}
		return returnApiEndpointURI(apiEndpointURI, null, null);
	}

	private String returnApiEndpointURI(String apiEndpointURI, URIBuilder builder, List<NameValuePair> nvpList) {
		return StringUtils.join( apiEndpointURI,
				null != builder ? builder.addParameters(nvpList).toString() : StringUtils.EMPTY);

	}
}