package com.aldar.studentportal.models.coursesAdviceModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sections {
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("insName")
    @Expose
    private String insName;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("time")
    @Expose
    private List<Time> time = null;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }

}
