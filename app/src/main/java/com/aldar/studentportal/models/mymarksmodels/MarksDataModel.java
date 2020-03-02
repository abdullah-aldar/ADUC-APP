package com.aldar.studentportal.models.mymarksmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarksDataModel {
    @SerializedName("studentID")
    @Expose
    private String studentID;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("semID")
    @Expose
    private Integer semID;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("courseWork")
    @Expose
    private String courseWork;
    @SerializedName("finalExam")
    @Expose
    private String finalExam;
    @SerializedName("letterGrade")
    @Expose
    private String letterGrade;
    @SerializedName("schemaId")
    @Expose
    private Integer schemaId;
    @SerializedName("totalPercentage")
    @Expose
    private Double totalPercentage;
    @SerializedName("honorPoints")
    @Expose
    private Double honorPoints;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSemID() {
        return semID;
    }

    public void setSemID(Integer semID) {
        this.semID = semID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseWork() {
        return courseWork;
    }

    public void setCourseWork(String courseWork) {
        this.courseWork = courseWork;
    }

    public String getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(String finalExam) {
        this.finalExam = finalExam;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Integer getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Integer schemaId) {
        this.schemaId = schemaId;
    }

    public Double getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(Double totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    public Double getHonorPoints() {
        return honorPoints;
    }

    public void setHonorPoints(Double honorPoints) {
        this.honorPoints = honorPoints;
    }
}
