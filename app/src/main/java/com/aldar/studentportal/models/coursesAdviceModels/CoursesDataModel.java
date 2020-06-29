package com.aldar.studentportal.models.coursesAdviceModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoursesDataModel {
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("sections")
    @Expose
    private List<Sections> sections = null;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Sections> getSections() {
        return sections;
    }

    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }
}
