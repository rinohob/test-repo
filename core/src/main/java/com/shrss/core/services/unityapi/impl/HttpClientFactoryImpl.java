package com.shrss.core.services.unityapi.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.servlets.post.JSONResponse;
import org.apache.sling.api.servlets.HttpConstants;
import com.shrss.core.services.unityapi.HttpClientFactory;

 
/**
 * Implementation of @{@link HttpClientFactory}.
 * <p>
 * HttpClientFactory provides service to handle API connection and executor.
 */
@Component(service = HttpClientFactory.class)
@Designate(ocd = HttpClientFactoryConfig.class)
public class HttpClientFactoryImpl implements HttpClientFactory {
 
    private static final String HTTPS = "https";

	private static final String HTTP = "http";

	private static final Logger log = LoggerFactory.getLogger(HttpClientFactoryImpl.class);
 
    private Executor executor;
    private String baseUrl;
    private String apiHostName;
    private String uriType;
    private CloseableHttpClient httpClient;
    private HttpClientFactoryConfig config;
 
    @Reference
    private HttpClientBuilderFactory httpClientBuilderFactory;
    /**
     * Activates the HttpClientFactory with the provided configuration.
     * <p>
     * This method closes any existing HTTP connection, validates the configuration, and initializes the executor.
     * If the configuration is not valid (i.e., the hostname is not provided), it throws an IllegalArgumentException.
     * It also sets the base URL for the API by joining the hostname and URI type from the configuration.
     * If the SSL setting in the configuration is relaxed, it initializes the executor with a relaxed SSL context.
     *
     * @param config the configuration for the HttpClientFactory
     * @throws KeyManagementException if an error occurs when initializing the SSL context
     * @throws NoSuchAlgorithmException if the SSL context requires an algorithm that is not available
     * @throws KeyStoreException if there is an error with the keystore used for SSL connections
     * @throws IllegalArgumentException if the configuration is not valid (i.e., the hostname is not provided)
     */
 
    @Activate
    @Modified
    protected void activate(HttpClientFactoryConfig config) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        closeHttpConnection();
        this.config = config;
        if (this.config.apiHostName() == null) {
            log.debug("Configuration is not valid. Hostname is mandatory.");
            throw new IllegalArgumentException("Configuration is not valid. Hostname is mandatory.");
        }
        this.uriType = this.config.uriType();      
        this.apiHostName = this.config.apiHostName();
        this.baseUrl = StringUtils.join(this.config.apiHostName(), config.uriType());
        initExecutor();
    }
    /**
     * Initializes the executor for making HTTP requests.
     * <p>
     * This method sets up the connection manager, request configuration, and default headers for the HTTP client.
     * It also determines whether to use a relaxed SSL context based on the configuration.
     * The executor is then created with the configured HTTP client.
     * 
     * @throws KeyManagementException if an error occurs when initializing the SSL context
     * @throws NoSuchAlgorithmException if the SSL context requires an algorithm that is not available
     * @throws KeyStoreException if there is an error with the keystore used for SSL connections
     */
    private void initExecutor() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        PoolingHttpClientConnectionManager connMgr = null; 
        RequestConfig requestConfig = initRequestConfig();
        HttpClientBuilder builder = httpClientBuilderFactory.newBuilder();
        builder.setDefaultRequestConfig(requestConfig); 
        if (config.relaxedSSL()) {
            connMgr = initPoolingConnectionManagerWithRelaxedSSL();
        } else {
            connMgr = new PoolingHttpClientConnectionManager();
        } 
        connMgr.closeExpiredConnections();
        connMgr.setMaxTotal(config.maxTotalOpenConnections());
        connMgr.setDefaultMaxPerRoute(config.maxConcurrentConnectionPerRoute());
        builder.setConnectionManager(connMgr);
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpConstants.HEADER_ACCEPT, JSONResponse.RESPONSE_CONTENT_TYPE));
        builder.setDefaultHeaders(headers);
        ConnectionKeepAliveStrategy keepAliveStratey =(HttpResponse response, HttpContext context)
        	-> TimeUnit.SECONDS.toMillis(config.defaultKeepAliveconnection());
        builder.setKeepAliveStrategy(keepAliveStratey);
        httpClient = builder.build();
        executor = Executor.newInstance(httpClient);
    }
    /**
     * Initializes a PoolingHttpClientConnectionManager with a relaxed SSL context.
     * <p>
     * This method creates a new SSLContextBuilder and loads it with a TrustAllStrategy, which accepts any certificate 
     * (even self-signed ones) as valid. It then creates a new SSLConnectionSocketFactory with the SSL context from the 
     * builder and a NoopHostnameVerifier, which accepts any hostname as valid.
     * <p>
     * The method then creates a registry of ConnectionSocketFactories, registering the default PlainConnectionSocketFactory 
     * for HTTP and the newly created SSLConnectionSocketFactory for HTTPS.
     * <p>
     * Finally, it creates a new PoolingHttpClientConnectionManager with the registry of socket factories and returns it.
     *
     * @return a PoolingHttpClientConnectionManager with a relaxed SSL context
     * @throws NoSuchAlgorithmException if the SSL context requires an algorithm that is not available
     * @throws KeyStoreException if there is an error with the keystore used for SSL connections
     * @throws KeyManagementException if an error occurs when initializing the SSL context
     */
    private PoolingHttpClientConnectionManager initPoolingConnectionManagerWithRelaxedSSL()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException { 
        PoolingHttpClientConnectionManager connMgr;
        SSLContextBuilder sslbuilder = new SSLContextBuilder();
        sslbuilder.loadTrustMaterial(new TrustAllStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslbuilder.build(),
                NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HTTP, PlainConnectionSocketFactory.getSocketFactory()).register(HTTPS, sslsf).build();
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        return connMgr;
    }
 
    /**
     * Initializes a RequestConfig with the default connection, socket, and connection request timeouts from the configuration.
     * <p>
     * This method creates a new RequestConfig.Builder and sets the connect, socket, and connection request timeouts to the
     * corresponding values from the configuration (converted from seconds to milliseconds).
     * It then builds and returns the RequestConfig.
     *
     * @return a RequestConfig with the default timeouts from the configuration
     */
    private RequestConfig initRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultConnectionTimeout())))
                .setSocketTimeout(Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultSocketTimeout())))
                .setConnectionRequestTimeout(
                        Math.toIntExact(TimeUnit.SECONDS.toMillis(config.defaultConnectionRequestTimeout())))
                .build();
    }

    /**
     * Deactivates the HttpClientFactory.
     * <p>
     * This method closes the HTTP connection.
     */
    @Deactivate
    protected void deactivate() {
        closeHttpConnection();
    }

    /**
     * Closes the HTTP connection.
     * <p>
     * This method checks if the httpClient is not null and then attempts to close it.
     * If an IOException occurs during the closing, it logs a debug message with the exception's message.
     */
    private void closeHttpConnection() {
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (final IOException exception) {
                log.debug("IOException while closing API, {}", exception.getMessage());
            }
        }
    }

    /**
     * Returns the executor for making HTTP requests.
     *
     * @return the executor for making HTTP requests
     */
    @Override
    public Executor getExecutor() {
        return executor;
    }

    /**
     * Returns a GET Request for the given partial URL.
     * <p>
     * This method appends the partial URL to the base URL and creates a GET Request for the resulting URL.
     *
     * @param partialUrl the partial URL to append to the base URL
     * @return a GET Request for the resulting URL
     */
    @Override
    public Request get(String partialUrl,Boolean includeBaseUrl) {
    	String url = createUrl(partialUrl, includeBaseUrl);
        return Request.Get(url);
    }

    /**
     * Returns a POST Request for the given partial URL.
     * <p>
     * This method appends the partial URL to the base URL and creates a POST Request for the resulting URL.
     *
     * @param partialUrl the partial URL to append to the base URL
     * @return a POST Request for the resulting URL
     */
    @Override
    public Request post(String partialUrl,Boolean includeBaseUrl) {
    	String url = createUrl(partialUrl, includeBaseUrl);
        return Request.Post(url);
    }


    /**
     * Returns a PUT Request for the given partial URL.
     * <p>
     * This method appends the partial URL to the base URL and creates a PUT Request for the resulting URL.
     *
     * @param partialUrl the partial URL to append to the base URL
     * @return a PUT Request for the resulting URL
     */
    @Override
    public Request put(String partialUrl,Boolean includeBaseUrl) {
    	String url = createUrl(partialUrl, includeBaseUrl);
        return Request.Put(url);
    }

    /**
     * Returns a DELETE Request for the given partial URL.
     * <p>
     * This method appends the partial URL to the base URL and creates a DELETE Request for the resulting URL.
     *
     * @param partialUrl the partial URL to append to the base URL
     * @return a DELETE Request for the resulting URL
     */
    @Override
    public Request delete(String partialUrl,Boolean includeBaseUrl) {
    	String url = createUrl(partialUrl, includeBaseUrl);
        return Request.Delete(url);
    }

    /**
     * Returns an OPTIONS Request for the given partial URL.
     * <p>
     * This method appends the partial URL to the base URL and creates an OPTIONS Request for the resulting URL.
     *
     * @param partialUrl the partial URL to append to the base URL
     * @return an OPTIONS Request for the resulting URL
     */
    @Override
    public Request options(String partialUrl,Boolean includeBaseUrl) {
    	String url = createUrl(partialUrl, includeBaseUrl);
        return Request.Options(url);
    }
    
	private String createUrl(String partialUrl, Boolean includeBaseUrl) {
		String url = null;
    	if(includeBaseUrl == Boolean.TRUE) {
    		 url = baseUrl + partialUrl;
    	}else {
    		    url = partialUrl;
    	}
		return url;
	}



    /**
     * Returns the URI type.
     *
     * @return the URI type
     */
    @Override
    public String getURIType() {
        return uriType;
    }

    /**
     * Returns the API hostname.
     *
     * @return the API hostname
     */
    @Override
    public String getApiHostName() {        
        return apiHostName;
    }
}