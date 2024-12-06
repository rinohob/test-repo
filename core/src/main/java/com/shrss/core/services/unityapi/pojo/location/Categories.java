package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class Categories {

   @SerializedName("categoryName")
   String categoryName;

   @SerializedName("categoryShortName")
   String categoryShortName;


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryShortName(String categoryShortName) {
        this.categoryShortName = categoryShortName;
    }
    public String getCategoryShortName() {
        return categoryShortName;
    }
    
}