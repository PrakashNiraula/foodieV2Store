package com.devpj.foodiestorev2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BroadcastResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("recipients")
    @Expose
    private Integer recipients;
    @SerializedName("external_id")
    @Expose
    private Object externalId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRecipients() {
        return recipients;
    }

    public void setRecipients(Integer recipients) {
        this.recipients = recipients;
    }

    public Object getExternalId() {
        return externalId;
    }

    public void setExternalId(Object externalId) {
        this.externalId = externalId;
    }
}
