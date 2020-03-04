package com.aldar.studentportal.models.semesterScheduleModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SemesterDataModel {
    @SerializedName("semID")
    @Expose
    private Integer semID;
    @SerializedName("semName")
    @Expose
    private String semName;

    public Integer getSemID() {
        return semID;
    }

    public void setSemID(Integer semID) {
        this.semID = semID;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }
}
