package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class Venues {

   @SerializedName("venueName")
   String venueName;

   @SerializedName("venueShortName")
   String venueShortName;

   @SerializedName("subCategoryShortName")
   String subCategoryShortName;


    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public String getVenueName() {
        return venueName;
    }
    
    public void setVenueShortName(String venueShortName) {
        this.venueShortName = venueShortName;
    }
    public String getVenueShortName() {
        return venueShortName;
    }
    
    public void setSubCategoryShortName(String subCategoryShortName) {
        this.subCategoryShortName = subCategoryShortName;
    }
    public String getSubCategoryShortName() {
        return subCategoryShortName;
    }
    
}