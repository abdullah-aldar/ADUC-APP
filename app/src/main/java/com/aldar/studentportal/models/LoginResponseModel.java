package com.aldar.studentportal.models;

import android.service.autofill.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseModel {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userdata")
    @Expose
    private List<LoginDataModel> userdata = null;
    @SerializedName("nexttbl")
    @Expose
    private Object nexttbl;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoginDataModel> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<LoginDataModel> userdata) {
        this.userdata = userdata;
    }

    public Object getNexttbl() {
        return nexttbl;
    }

    public void setNexttbl(Object nexttbl) {
        this.nexttbl = nexttbl;
    }
}
