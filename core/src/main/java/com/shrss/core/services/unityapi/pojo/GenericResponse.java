package com.shrss.core.services.unityapi.pojo;

import com.google.gson.annotations.SerializedName;

public class GenericResponse {

	@SerializedName(value = "errorcode")
	private long errorCode;

	@SerializedName("fault")
	Fault fault;

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	public Fault getFault() {
		return fault;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

}