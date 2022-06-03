package com.devpj.foodiestorev2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppSettings {
    @SerializedName("data")
    @Expose
    private AppSettings.data data;
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("ResponseMsg")
    @Expose
    private String responseMsg;

    public AppSettings.data getData() {
        return data;
    }

    public void setData(AppSettings.data data) {
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }



public class data{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("one_key")
    @Expose
    private String oneKey;
    @SerializedName("one_hash")
    @Expose
    private String oneHash;
    @SerializedName("r_key")
    @Expose
    private String rKey;
    @SerializedName("r_hash")
    @Expose
    private String rHash;
    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("admin_hash")
    @Expose
    private String adminHash;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("privacy_policy")
    @Expose
    private String privacyPolicy;
    @SerializedName("about_us")
    @Expose
    private String aboutUs;
    @SerializedName("contact_us")
    @Expose
    private String contactUs;
    @SerializedName("o_min")
    @Expose
    private String oMin;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("favicon")
    @Expose
    private String favicon;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("terms")
    @Expose
    private String terms;
    @SerializedName("maintaince")
    @Expose
    private String maintaince;
    @SerializedName("signupcredit")
    @Expose
    private String signupcredit;
    @SerializedName("refercredit")
    @Expose
    private String refercredit;
    @SerializedName("delivery_charge")
    @Expose
    private String deliveryCharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOneKey() {
        return oneKey;
    }

    public void setOneKey(String oneKey) {
        this.oneKey = oneKey;
    }

    public String getOneHash() {
        return oneHash;
    }

    public void setOneHash(String oneHash) {
        this.oneHash = oneHash;
    }

    public String getrKey() {
        return rKey;
    }

    public void setrKey(String rKey) {
        this.rKey = rKey;
    }

    public String getrHash() {
        return rHash;
    }

    public void setrHash(String rHash) {
        this.rHash = rHash;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminHash() {
        return adminHash;
    }

    public void setAdminHash(String adminHash) {
        this.adminHash = adminHash;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getContactUs() {
        return contactUs;
    }

    public void setContactUs(String contactUs) {
        this.contactUs = contactUs;
    }

    public String getoMin() {
        return oMin;
    }

    public void setoMin(String oMin) {
        this.oMin = oMin;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getMaintaince() {
        return maintaince;
    }

    public void setMaintaince(String maintaince) {
        this.maintaince = maintaince;
    }

    public String getSignupcredit() {
        return signupcredit;
    }

    public void setSignupcredit(String signupcredit) {
        this.signupcredit = signupcredit;
    }

    public String getRefercredit() {
        return refercredit;
    }

    public void setRefercredit(String refercredit) {
        this.refercredit = refercredit;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }



}

}
