package com.aldar.studentportal.models.gradeConversionModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendRequestData {

    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("gcCode")
    @Expose
    private String gcCode;
    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;

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

    public String getGcCode() {
        return gcCode;
    }

    public void setGcCode(String gcCode) {
        this.gcCode = gcCode;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


}
