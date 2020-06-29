package com.aldar.studentportal.models.libraryModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DigitalLibraryDataModel {
    @SerializedName("resourceName")
    @Expose
    private String resourceName;
    @SerializedName("resourceLink")
    @Expose
    private String resourceLink;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
