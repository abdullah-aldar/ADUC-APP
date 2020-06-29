package com.aldar.studentportal.models.studyplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudyPlanResponseModel {
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
    @SerializedName("totalCreditHours")
    @Expose
    private Double totalCreditHours;
    @SerializedName("data")
    @Expose
    private List<StudyPlanYearly> data = null;

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

    public Double getTotalCreditHours() {
        return totalCreditHours;
    }

    public void setTotalCreditHours(Double totalCreditHours) {
        this.totalCreditHours = totalCreditHours;
    }

    public List<StudyPlanYearly> getData() {
        return data;
    }

    public void setData(List<StudyPlanYearly> data) {
        this.data = data;
    }

}
