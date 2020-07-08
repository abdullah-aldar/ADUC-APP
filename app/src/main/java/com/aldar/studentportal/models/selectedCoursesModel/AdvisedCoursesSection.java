package com.aldar.studentportal.models.selectedCoursesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvisedCoursesSection {
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("courses")
    @Expose
    private List<AdvisedCourseDataModel> courses = null;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<AdvisedCourseDataModel> getCourses() {
        return courses;
    }

    public void setCourses(List<AdvisedCourseDataModel> courses) {
        this.courses = courses;
    }
}
