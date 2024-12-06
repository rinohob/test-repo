package com.shrss.core.services.unityapi;

import java.io.IOException;

import com.shrss.core.services.unityapi.pojo.CIAMTokenResponse;

public interface CIAMService {
	
public String getGuestCIAMTokenService() throws IOException;

public String getCIAMLogoutService();

public String getCIAMSessionService(String idpSessionKey);

public String getClientCIAMTokenService();

public String getCIAMTokenFromRefreshToken();

public CIAMTokenResponse getCachedGuestToken();
	 

}
