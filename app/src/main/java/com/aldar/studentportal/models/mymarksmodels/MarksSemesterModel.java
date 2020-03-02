package com.aldar.studentportal.models.mymarksmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarksSemesterModel {
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("courses")
    @Expose
    private List<MarksDataModel> courses = null;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<MarksDataModel> getCourses() {
        return courses;
    }

    public void setCourses(List<MarksDataModel> courses) {
        this.courses = courses;
    }
}
