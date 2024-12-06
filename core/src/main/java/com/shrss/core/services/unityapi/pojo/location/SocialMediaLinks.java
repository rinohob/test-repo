package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class SocialMediaLinks {

   @SerializedName("type")
   String type;

   @SerializedName("userName")
   String userName;

   @SerializedName("displayName")
   String displayName;

   @SerializedName("url")
   String url;


    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    
}