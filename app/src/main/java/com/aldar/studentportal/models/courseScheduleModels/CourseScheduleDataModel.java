package com.aldar.studentportal.models.courseScheduleModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseScheduleDataModel {
    @SerializedName("course_Code")
    @Expose
    private String courseCode;
    @SerializedName("section_Code")
    @Expose
    private String sectionCode;
    @SerializedName("course_Name")
    @Expose
    private String courseName;
    @SerializedName("day_Time")
    @Expose
    private String dayTime;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("lecturer")
    @Expose
    private String lecturer;
    @SerializedName("final_Exam_Date")
    @Expose
    private String finalExamDate;
    @SerializedName("final_Exam_Time")
    @Expose
    private String finalExamTime;
    @SerializedName("midTerm_Exam_Date")
    @Expose
    private String midTermExamDate;
    @SerializedName("midTerm_Exam_Time")
    @Expose
    private String midTermExamTime;
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("sem_Name")
    @Expose
    private String semName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getFinalExamDate() {
        return finalExamDate;
    }

    public void setFinalExamDate(String finalExamDate) {
        this.finalExamDate = finalExamDate;
    }

    public String getFinalExamTime() {
        return finalExamTime;
    }

    public void setFinalExamTime(String finalExamTime) {
        this.finalExamTime = finalExamTime;
    }

    public String getMidTermExamDate() {
        return midTermExamDate;
    }

    public void setMidTermExamDate(String midTermExamDate) {
        this.midTermExamDate = midTermExamDate;
    }

    public String getMidTermExamTime() {
        return midTermExamTime;
    }

    public void setMidTermExamTime(String midTermExamTime) {
        this.midTermExamTime = midTermExamTime;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

}
