package com.shrss.core.services.unityapi.pojo;

import com.google.gson.annotations.SerializedName;

   
public class Fault {

   @SerializedName("code")
   int code;

   @SerializedName("message")
   String message;

   @SerializedName("description")
   String description;


    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
}