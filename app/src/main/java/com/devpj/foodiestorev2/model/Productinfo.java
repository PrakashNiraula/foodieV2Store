
package com.devpj.foodiestorev2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Productinfo {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("psdesc")
    @Expose
    private String psdesc;
    @SerializedName("pgms")
    @Expose
    private String pgms;
    @SerializedName("pprice")
    @Expose
    private String pprice;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("pimg")
    @Expose
    private String pimg;
    @SerializedName("prel")
    @Expose
    private String prel;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("popular")
    @Expose
    private String popular;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPsdesc() {
        return psdesc;
    }

    public void setPsdesc(String psdesc) {
        this.psdesc = psdesc;
    }

    public String getPgms() {
        return pgms;
    }

    public void setPgms(String pgms) {
        this.pgms = pgms;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getPrel() {
        return prel;
    }

    public void setPrel(String prel) {
        this.prel = prel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }

}
