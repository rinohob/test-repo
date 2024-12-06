package com.shrss.core.services.unityapi.pojo;

import com.google.gson.annotations.SerializedName;

public class CIAMTokenResponse extends GenericResponse {
	
	@SerializedName(value = "access_token")
	private String accessToken;
	
	@SerializedName(value = "token_type")
	private String tokenType;
	
	@SerializedName(value = "scope")
	private String scope;
	
	@SerializedName(value = "expires_in")
	private long expiresTime;
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public long getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}

}
