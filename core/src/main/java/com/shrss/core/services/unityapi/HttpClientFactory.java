package com.shrss.core.services.unityapi;

 
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
 
/**
 * Factory for building pre-configured HttpClient Fluent Executor and Request objects
 * based a configure host, port and (optionally) username/password.
 * Factories will generally be accessed by service lookup using the factory.name property.
 */
public interface HttpClientFactory {
 
    /**
     * Get the configured Executor object from this factory.
     *
     * @return an Executor object
     */
    Executor getExecutor();
 
    /**
     * Create a GET request using the base hostname and port defined in the factory configuration.
     *
     * @param partialUrl the portion of the URL after the port (and slash)
     *
     * @return a fluent Request object
     */
    Request get(String partialUrl,Boolean includeBaseUrl);
 

	String getURIType();
	
	/**
	 * Returns the API hostname.
	 *
	 * @return the API hostname
	 */
	String getApiHostName();
	
	/**
	 * Returns a POST Request for the given partial URL.
	 * <p>
	 * This method appends the partial URL to the base URL and creates a POST Request for the resulting URL.
	 *
	 * @param partialUrl the partial URL to append to the base URL
	 * @return a POST Request for the resulting URL
	 */
	Request post(String partialUrl, Boolean includeBaseUrl);

	/**
	 * Returns an OPTIONS Request for the given partial URL.
	 * <p>
	 * This method appends the partial URL to the base URL and creates an OPTIONS Request for the resulting URL.
	 *
	 * @param partialUrl the partial URL to append to the base URL
	 * @return an OPTIONS Request for the resulting URL
	 */
	Request options(String partialUrl, Boolean includeBaseUrl);

	/**
	 * Returns a DELETE Request for the given partial URL.
	 * <p>
	 * This method appends the partial URL to the base URL and creates a DELETE Request for the resulting URL.
	 *
	 * @param partialUrl the partial URL to append to the base URL
	 * @return a DELETE Request for the resulting URL
	 */
	Request delete(String partialUrl, Boolean includeBaseUrl);

	/**
	 * Returns a PUT Request for the given partial URL.
	 * <p>
	 * This method appends the partial URL to the base URL and creates a PUT Request for the resulting URL.
	 *
	 * @param partialUrl the partial URL to append to the base URL
	 * @return a PUT Request for the resulting URL
	 */
	Request put(String partialUrl, Boolean includeBaseUrl);
}