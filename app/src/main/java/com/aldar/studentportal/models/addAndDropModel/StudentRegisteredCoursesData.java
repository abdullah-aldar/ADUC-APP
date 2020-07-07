package com.aldar.studentportal.models.addAndDropModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentRegisteredCoursesData {
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("semId")
    @Expose
    private Integer semId;
    @SerializedName("semName")
    @Expose
    private String semName;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("ch")
    @Expose
    private Integer ch;
    @SerializedName("insName")
    @Expose
    private String insName;
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("invoiceCode")
    @Expose
    private String invoiceCode;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getSemId() {
        return semId;
    }

    public void setSemId(Integer semId) {
        this.semId = semId;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

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

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
