package com.aldar.studentportal.models.mymarksmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarksResponseModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cgpa")
    @Expose
    private Double cgpa;
    @SerializedName("totalCreditHourCompleted")
    @Expose
    private Double totalCreditHourCompleted;
    @SerializedName("data")
    @Expose
    private List<MarksSemesterModel> data = null;

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

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Double getTotalCreditHourCompleted() {
        return totalCreditHourCompleted;
    }

    public void setTotalCreditHourCompleted(Double totalCreditHourCompleted) {
        this.totalCreditHourCompleted = totalCreditHourCompleted;
    }

    public List<MarksSemesterModel> getData() {
        return data;
    }

    public void setData(List<MarksSemesterModel> data) {
        this.data = data;
    }

}
