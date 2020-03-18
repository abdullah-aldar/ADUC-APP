package com.aldar.studentportal.models.newDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsDataModel {
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("img")
    @Expose
    private String img;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
