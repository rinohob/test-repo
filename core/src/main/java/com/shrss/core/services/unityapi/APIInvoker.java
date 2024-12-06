package com.shrss.core.services.unityapi;

import java.net.MalformedURLException;
import java.util.Map;

public interface APIInvoker {

	String invokeUnityLocationAPI();

	String buildURL(String apiEndpointURI, Map<String, String> parameterMap)
			throws MalformedURLException;

}
