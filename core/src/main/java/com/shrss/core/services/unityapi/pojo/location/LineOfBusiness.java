package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class LineOfBusiness {

   @SerializedName("name")
   String name;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
}