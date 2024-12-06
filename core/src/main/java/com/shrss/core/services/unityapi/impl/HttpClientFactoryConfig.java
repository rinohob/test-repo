package com.shrss.core.services.unityapi.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.shrss.core.services.unityapi.Constants;
/**
 * HTTP Client API Configuration.
 * <p>
 * This interface provides the configuration for the HTTP Client API. It includes the API host name, URI type path,
 * relaxed SSL, maximum number of total open connections, maximum number of concurrent connections per route,
 * default keep alive connection in seconds, default connection timeout in seconds, default socket timeout in seconds,
 * and default connection request timeout in seconds.
 */
@ObjectClassDefinition(name = "Http Client API Configuration", description = "Http Client API Configuration")
public @interface HttpClientFactoryConfig {

    /**
     * Returns the API host name.
     *
     * @return the API host name
     */
    @AttributeDefinition(name = "API Host Name", description = "API host name, e.g. https://apis.qa.hardrock.com", type = AttributeType.STRING)
    String apiHostName() default Constants.DEFAULT_API_HOST_NAME;

    /**
     * Returns the API URI type path.
     *
     * @return the API URI type path
     */
    @AttributeDefinition(name = "API URI Type Path", description = "API URI type path, e.g. /unity-web-exp/v1/", type = AttributeType.STRING)
    String uriType() default Constants.DEFAULT_API_URI_TYPE_PATH;

    /**
     * Returns the relaxed SSL setting.
     *
     * @return the relaxed SSL setting
     */
    @AttributeDefinition(name = "Relaxed SSL", description = "Defines if self-certified certificates should be allowed to SSL transport", type = AttributeType.BOOLEAN)
    boolean relaxedSSL() default Constants.DEFAULT_RELAXED_SSL;

    /**
     * Returns the maximum number of total open connections.
     *
     * @return the maximum number of total open connections
     */
    @AttributeDefinition(name = "Maximum number of total open connections", description = "Set maximum number of total open connections, default 5", type = AttributeType.INTEGER)
    int maxTotalOpenConnections() default Constants.DEFAULT_MAXIMUM_TOTAL_OPEN_CONNECTION;

    /**
     * Returns the maximum number of concurrent connections per route.
     *
     * @return the maximum number of concurrent connections per route
     */
    @AttributeDefinition(name = "Maximum number of concurrent connections per route", description = "Set the maximum number of concurrent connections per route, default 5", type = AttributeType.INTEGER)
    int maxConcurrentConnectionPerRoute() default Constants.DEFAULT_MAXIMUM_CONCURRENT_CONNECTION_PER_ROUTE;

    /**
     * Returns the default keep alive connection in seconds.
     *
     * @return the default keep alive connection in seconds
     */
    @AttributeDefinition(name = "Default Keep alive connection in seconds", description = "Default Keep alive connection in seconds, default value is 1", type = AttributeType.INTEGER)
    int defaultKeepAliveconnection() default Constants.DEFAULT_KEEP_ALIVE_CONNECTION;

    /**
     * Returns the default connection timeout in seconds.
     *
     * @return the default connection timeout in seconds
     */
    @AttributeDefinition(name = "Default connection timeout in seconds", description = "Default connection timout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultConnectionTimeout() default Constants.DEFAULT_CONNECTION_TIMEOUT;

    /**
     * Returns the default socket timeout in seconds.
     *
     * @return the default socket timeout in seconds
     */
    @AttributeDefinition(name = "Default socket timeout in seconds", description = "Default socket timeout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultSocketTimeout() default Constants.DEFAULT_SOCKET_TIMEOUT;

    /**
     * Returns the default connection request timeout in seconds.
     *
     * @return the default connection request timeout in seconds
     */
    @AttributeDefinition(name = "Default connection request timeout in seconds", description = "Default connection request timeout in seconds, default value is 30", type = AttributeType.LONG)
    long defaultConnectionRequestTimeout() default Constants.DEFAULT_CONNECTION_REQUEST_TIMEOUT;
}