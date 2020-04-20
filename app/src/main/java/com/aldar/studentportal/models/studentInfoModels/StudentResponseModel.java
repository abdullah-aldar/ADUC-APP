package com.aldar.studentportal.models.studentInfoModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentResponseModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private StudentsDataModel data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
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

    public StudentsDataModel getData() {
        return data;
    }

    public void setData(StudentsDataModel data) {
        this.data = data;
    }
}
