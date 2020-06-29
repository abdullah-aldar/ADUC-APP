package com.aldar.studentportal.models.studyplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyPlanDataModel {
    @SerializedName("courseID")
    @Expose
    private Integer courseID;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("courseType")
    @Expose
    private Integer courseType;
    @SerializedName("typeDesc")
    @Expose
    private String typeDesc;
    @SerializedName("typeDescOrderID")
    @Expose
    private Integer typeDescOrderID;
    @SerializedName("sectionID")
    @Expose
    private Integer sectionID;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    @SerializedName("semID")
    @Expose
    private Integer semID;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("preReq")
    @Expose
    private String preReq;
    @SerializedName("courseCH")
    @Expose
    private Integer courseCH;
    @SerializedName("compCourseCH")
    @Expose
    private Integer compCourseCH;

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
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

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getTypeDescOrderID() {
        return typeDescOrderID;
    }

    public void setTypeDescOrderID(Integer typeDescOrderID) {
        this.typeDescOrderID = typeDescOrderID;
    }

    public Integer getSectionID() {
        return sectionID;
    }

    public void setSectionID(Integer sectionID) {
        this.sectionID = sectionID;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getSemID() {
        return semID;
    }

    public void setSemID(Integer semID) {
        this.semID = semID;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreReq() {
        return preReq;
    }

    public void setPreReq(String preReq) {
        this.preReq = preReq;
    }

    public Integer getCourseCH() {
        return courseCH;
    }

    public void setCourseCH(Integer courseCH) {
        this.courseCH = courseCH;
    }

    public Integer getCompCourseCH() {
        return compCourseCH;
    }

    public void setCompCourseCH(Integer compCourseCH) {
        this.compCourseCH = compCourseCH;
    }
}
