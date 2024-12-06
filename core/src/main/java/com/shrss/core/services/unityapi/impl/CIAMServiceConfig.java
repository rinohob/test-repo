package com.shrss.core.services.unityapi.impl;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.shrss.core.services.unityapi.Constants;

/**
 * CIAM API Configuration.
 * <p>
 * This interface provides the configuration for the CIAM API. It includes the host name, token path, logout path,
 * extended session path, guest API client ID, and guest API client secret.
 */
@ObjectClassDefinition(name = "CIAM API Configuration", description = "CIAM API Configuration")
public @interface CIAMServiceConfig {

    /**
     * Returns the CIAM API host name.
     *
     * @return the CIAM API host name
     */
    @AttributeDefinition(name = "CIAM API Host Name", description = "API host name, e.g. https://unity.qa.login.hardrock.com", type = AttributeType.STRING)
    String ciamHostName() default Constants.DEFAULT_CIAM_API_HOST_NAME;

    /**
     * Returns the API token path.
     *
     * @return the API token path
     */
    @AttributeDefinition(name = "API token path", description = "API token path, e.g. /oauth2/token", type = AttributeType.STRING)
    String tokenPath() default Constants.DEFAULT_TOKEN_PATH;

    /**
     * Returns the API logout path.
     *
     * @return the API logout path
     */
    @AttributeDefinition(name = "API logout path", description = "API logout path, e.g. /oidc/logout", type = AttributeType.STRING)
    String logouturi() default Constants.DEFAULT_LOGOUT_PATH;

    /**
     * Returns the API extended session path.
     *
     * @return the API extended session path
     */
    @AttributeDefinition(name = "API extended session Path", description = "API extended session Path, e.g. /identity/extend-session", type = AttributeType.STRING)
    String extendedSession() default Constants.DEFAULT_EXTENDED_SESSION_PATH;

    /**
     * Returns the guest API client ID.
     *
     * @return the guest API client ID
     */
    @AttributeDefinition(name = "Guest API Client Id", description = "Guest API Client Id", type = AttributeType.STRING)
    String guestAPIClientId() default Constants.GUEST_API_CLIENT_ID;

    /**
     * Returns the guest API client secret.
     *
     * @return the guest API client secret
     */
    @AttributeDefinition(name = "Guest API Client Secret", description = "Guest API Client Secret", type = AttributeType.STRING)
    String guestAPIClientSecret() default Constants.GUEST_API_CLIENT_SECRET;
}

