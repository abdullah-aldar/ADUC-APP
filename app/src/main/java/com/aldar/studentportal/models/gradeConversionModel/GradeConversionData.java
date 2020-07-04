package com.aldar.studentportal.models.gradeConversionModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GradeConversionData {
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("instructerName")
    @Expose
    private String instructerName;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("sectionID")
    @Expose
    private Integer sectionID;
    @SerializedName("insID")
    @Expose
    private Integer insID;
    @SerializedName("cw")
    @Expose
    private Double cw;
    @SerializedName("fm")
    @Expose
    private Double fm;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getInstructerName() {
        return instructerName;
    }

    public void setInstructerName(String instructerName) {
        this.instructerName = instructerName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getSectionID() {
        return sectionID;
    }

    public void setSectionID(Integer sectionID) {
        this.sectionID = sectionID;
    }

    public Integer getInsID() {
        return insID;
    }

    public void setInsID(Integer insID) {
        this.insID = insID;
    }

    public Double getCw() {
        return cw;
    }

    public void setCw(Double cw) {
        this.cw = cw;
    }

    public Double getFm() {
        return fm;
    }

    public void setFm(Double fm) {
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
}
