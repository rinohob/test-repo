package com.shrss.core.services.unityapi.pojo.location;

import com.google.gson.annotations.SerializedName;

   
public class SubCategories {

   @SerializedName("categoryShortName")
   String categoryShortName;

   @SerializedName("subCategoryName")
   String subCategoryName;

   @SerializedName("subCategoryShortName")
   String subCategoryShortName;


    public void setCategoryShortName(String categoryShortName) {
        this.categoryShortName = categoryShortName;
    }
    public String getCategoryShortName() {
        return categoryShortName;
    }
    
    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
    public String getSubCategoryName() {
        return subCategoryName;
    }
    
    public void setSubCategoryShortName(String subCategoryShortName) {
        this.subCategoryShortName = subCategoryShortName;
    }
    public String getSubCategoryShortName() {
        return subCategoryShortName;
    }
    
}