package com.devpj.foodiestorev2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Broadcast {


    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("filters")
    @Expose
    private List<Filter> filters = null;
    @SerializedName("contents")
    @Expose
    private Contents contents;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }







    public class Contents {

        @SerializedName("en")
        @Expose
        private String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

    }



    public static class Filter {

        @SerializedName("field")
        @Expose
        private String field;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("relation")
        @Expose
        private String relation;
        @SerializedName("value")
        @Expose
        private String value;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}
