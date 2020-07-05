package com.aldar.studentportal.models.gradeConversionModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {

    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("instructerName")
    @Expose
    private Object instructerName;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("cw")
    @Expose
    private Object cw;
    @SerializedName("fm")
    @Expose
    private Object fm;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gcCode")
    @Expose
    private String gcCode;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("studentId")
    @Expose
    private String studentId;

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Object getInstructerName() {
        return instructerName;
    }

    public void setInstructerName(Object instructerName) {
        this.instructerName = instructerName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Object getCw() {
        return cw;
    }

    public void setCw(Object cw) {
        this.cw = cw;
    }

    public Object getFm() {
        return fm;
    }

    public void setFm(Object fm) {
        this.fm = fm;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGcCode() {
        return gcCode;
    }

    public void setGcCode(String gcCode) {
        this.gcCode = gcCode;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
