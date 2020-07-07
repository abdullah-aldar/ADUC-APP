package com.aldar.studentportal.models.addAndDropModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sections {
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("courses")
    @Expose
    private List<StudentRegisteredCoursesData> courses = null;

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

    public List<StudentRegisteredCoursesData> getCourses() {
        return courses;
    }

    public void setCourses(List<StudentRegisteredCoursesData> courses) {
        this.courses = courses;
    }
}
