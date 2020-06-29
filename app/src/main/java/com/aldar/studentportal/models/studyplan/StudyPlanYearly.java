package com.aldar.studentportal.models.studyplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudyPlanYearly {
    @SerializedName("yearLevel")
    @Expose
    private String yearLevel;
    @SerializedName("totalCH")
    @Expose
    private Integer totalCH;
    @SerializedName("compTotalCH")
    @Expose
    private Integer compTotalCH;
    @SerializedName("courses")
    @Expose
    private List<StudyPlanDataModel> courses = null;

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Integer getTotalCH() {
        return totalCH;
    }

    public void setTotalCH(Integer totalCH) {
        this.totalCH = totalCH;
    }

    public Integer getCompTotalCH() {
        return compTotalCH;
    }

    public void setCompTotalCH(Integer compTotalCH) {
        this.compTotalCH = compTotalCH;
    }

    public List<StudyPlanDataModel> getCourses() {
        return courses;
    }

    public void setCourses(List<StudyPlanDataModel> courses) {
        this.courses = courses;
    }
}
