package com.shrss.core.services.unityapi.pojo.location;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class Locations {

   @SerializedName("locationId")
   int locationId;

   @SerializedName("locationCode")
   String locationCode;

   @SerializedName("locationLegalName")
   String locationLegalName;

   @SerializedName("locationLongName")
   String locationLongName;

   @SerializedName("locationShortName")
   String locationShortName;

   @SerializedName("displayOnDigital")
   boolean displayOnDigital;

   @SerializedName("marketingEmail")
   boolean marketingEmail;

   @SerializedName("marketingDirectMail")
   boolean marketingDirectMail;

   @SerializedName("bookingUrl")
   String bookingUrl;

   @SerializedName("reservationDisclaimer")
   String reservationDisclaimer;

   @SerializedName("appearOnVisitationMap")
   boolean appearOnVisitationMap;

   @SerializedName("brierleyParticipant")
   boolean brierleyParticipant;

   @SerializedName("favoriteLocation")
   boolean favoriteLocation;

   @SerializedName("communicationPreferenceDisplay")
   boolean communicationPreferenceDisplay;

   @SerializedName("openDate")
   Date openDate;

   @SerializedName("closeDate")
   String closeDate;

   @SerializedName("unityParticipant")
   boolean unityParticipant;

   @SerializedName("webPage")
   String webPage;

   @SerializedName("email")
   String email;

   @SerializedName("categories")
   List<Categories> categories;

   @SerializedName("subCategories")
   List<SubCategories> subCategories;

   @SerializedName("lineOfBusiness")
   List<LineOfBusiness> lineOfBusiness;

   @SerializedName("venues")
   List<Venues> venues;

   @SerializedName("subLineOfBusiness")
   String subLineOfBusiness;

   @SerializedName("brand")
   String brand;

   @SerializedName("locationType")
   String locationType;

   @SerializedName("addressLine1")
   String addressLine1;

   @SerializedName("addressLine2")
   String addressLine2;

   @SerializedName("addressLine3")
   String addressLine3;

   @SerializedName("city")
   String city;

   @SerializedName("stateProvince")
   String stateProvince;

   @SerializedName("stateCode")
   String stateCode;

   @SerializedName("countryCode")
   String countryCode;

   @SerializedName("country")
   String country;

   @SerializedName("postalCode")
   String postalCode;

   @SerializedName("fullAddress")
   String fullAddress;

   @SerializedName("phoneNumber")
   String phoneNumber;

   @SerializedName("phoneCountryCode")
   String phoneCountryCode;

   @SerializedName("areaCode")
   String areaCode;

   @SerializedName("regionCode")
   String regionCode;

   @SerializedName("regionName")
   String regionName;

   @SerializedName("latitude")
   double latitude;

   @SerializedName("longitude")
   double longitude;

   @SerializedName("locationStatus")
   String locationStatus;

   @SerializedName("subLocation")
   boolean subLocation;

   @SerializedName("modifiedDate")
   Date modifiedDate;

   @SerializedName("socialMediaLinks")
   List<SocialMediaLinks> socialMediaLinks;

   @SerializedName("extensibilityFields")
   List<ExtensibilityFields> extensibilityFields;


    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getLocationId() {
        return locationId;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
    public String getLocationCode() {
        return locationCode;
    }
    
    public void setLocationLegalName(String locationLegalName) {
        this.locationLegalName = locationLegalName;
    }
    public String getLocationLegalName() {
        return locationLegalName;
    }
    
    public void setLocationLongName(String locationLongName) {
        this.locationLongName = locationLongName;
    }
    public String getLocationLongName() {
        return locationLongName;
    }
    
    public void setLocationShortName(String locationShortName) {
        this.locationShortName = locationShortName;
    }
    public String getLocationShortName() {
        return locationShortName;
    }
    
    public void setDisplayOnDigital(boolean displayOnDigital) {
        this.displayOnDigital = displayOnDigital;
    }
    public boolean getDisplayOnDigital() {
        return displayOnDigital;
    }
    
    public void setMarketingEmail(boolean marketingEmail) {
        this.marketingEmail = marketingEmail;
    }
    public boolean getMarketingEmail() {
        return marketingEmail;
    }
    
    public void setMarketingDirectMail(boolean marketingDirectMail) {
        this.marketingDirectMail = marketingDirectMail;
    }
    public boolean getMarketingDirectMail() {
        return marketingDirectMail;
    }
    
    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }
    public String getBookingUrl() {
        return bookingUrl;
    }
    
    public void setReservationDisclaimer(String reservationDisclaimer) {
        this.reservationDisclaimer = reservationDisclaimer;
    }
    public String getReservationDisclaimer() {
        return reservationDisclaimer;
    }
    
    public void setAppearOnVisitationMap(boolean appearOnVisitationMap) {
        this.appearOnVisitationMap = appearOnVisitationMap;
    }
    public boolean getAppearOnVisitationMap() {
        return appearOnVisitationMap;
    }
    
    public void setBrierleyParticipant(boolean brierleyParticipant) {
        this.brierleyParticipant = brierleyParticipant;
    }
    public boolean getBrierleyParticipant() {
        return brierleyParticipant;
    }
    
    public void setFavoriteLocation(boolean favoriteLocation) {
        this.favoriteLocation = favoriteLocation;
    }
    public boolean getFavoriteLocation() {
        return favoriteLocation;
    }
    
    public void setCommunicationPreferenceDisplay(boolean communicationPreferenceDisplay) {
        this.communicationPreferenceDisplay = communicationPreferenceDisplay;
    }
    public boolean getCommunicationPreferenceDisplay() {
        return communicationPreferenceDisplay;
    }
    
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    public Date getOpenDate() {
        return openDate;
    }
    
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }
    public String getCloseDate() {
        return closeDate;
    }
    
    public void setUnityParticipant(boolean unityParticipant) {
        this.unityParticipant = unityParticipant;
    }
    public boolean getUnityParticipant() {
        return unityParticipant;
    }
    
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    public String getWebPage() {
        return webPage;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    
    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
    public List<Categories> getCategories() {
        return categories;
    }
    
    public void setSubCategories(List<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }
    public List<SubCategories> getSubCategories() {
        return subCategories;
    }
    
    public void setLineOfBusiness(List<LineOfBusiness> lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }
    public List<LineOfBusiness> getLineOfBusiness() {
        return lineOfBusiness;
    }
    
    public void setVenues(List<Venues> venues) {
        this.venues = venues;
    }
    public List<Venues> getVenues() {
        return venues;
    }
    
    public void setSubLineOfBusiness(String subLineOfBusiness) {
        this.subLineOfBusiness = subLineOfBusiness;
    }
    public String getSubLineOfBusiness() {
        return subLineOfBusiness;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }
    
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    public String getLocationType() {
        return locationType;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    public String getAddressLine3() {
        return addressLine3;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }
    
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getStateCode() {
        return stateCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    public String getFullAddress() {
        return fullAddress;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }
    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }
    
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaCode() {
        return areaCode;
    }
    
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    public String getRegionCode() {
        return regionCode;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public String getRegionName() {
        return regionName;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return latitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLongitude() {
        return longitude;
    }
    
    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }
    public String getLocationStatus() {
        return locationStatus;
    }
    
    public void setSubLocation(boolean subLocation) {
        this.subLocation = subLocation;
    }
    public boolean getSubLocation() {
        return subLocation;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    
    public void setSocialMediaLinks(List<SocialMediaLinks> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }
    public List<SocialMediaLinks> getSocialMediaLinks() {
        return socialMediaLinks;
    }
    
    public void setExtensibilityFields(List<ExtensibilityFields> extensibilityFields) {
        this.extensibilityFields = extensibilityFields;
    }
    public List<ExtensibilityFields> getExtensibilityFields() {
        return extensibilityFields;
    }
    
}