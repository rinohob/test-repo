package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class ExtensibilityFields {

   @SerializedName("key")
   String key;

   @SerializedName("value")
   String value;


    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    
}