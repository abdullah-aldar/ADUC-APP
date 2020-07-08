package com.aldar.studentportal.models.selectedCoursesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvisedCourseResponseModel {
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
    private List<AdvisedCoursesSection> data = null;

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

    public List<AdvisedCoursesSection> getData() {
        return data;
    }

    public void setData(List<AdvisedCoursesSection> data) {
        this.data = data;
    }
}
