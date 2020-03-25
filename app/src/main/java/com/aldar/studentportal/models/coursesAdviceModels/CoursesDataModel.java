package com.aldar.studentportal.models.coursesAdviceModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesDataModel {
    @SerializedName("studentID")
    @Expose
    private Integer studentID;
    @SerializedName("givenStudentID")
    @Expose
    private String givenStudentID;
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("courseId")
    @Expose
    private Integer courseId;
    @SerializedName("courseCode")
    @Expose
    private String courseCode;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("creditHours")
    @Expose
    private Integer creditHours;
    @SerializedName("programeId")
    @Expose
    private Integer programeId;
    @SerializedName("streamId")
    @Expose
    private Integer streamId;
    @SerializedName("studyModeID")
    @Expose
    private Integer studyModeID;
    @SerializedName("remark1")
    @Expose
    private String remark1;
    @SerializedName("remark2")
    @Expose
    private String remark2;
    @SerializedName("remark3")
    @Expose
    private String remark3;
    @SerializedName("remark4")
    @Expose
    private String remark4;
    @SerializedName("remark5")
    @Expose
    private String remark5;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("insName")
    @Expose
    private String insName;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getGivenStudentID() {
        return givenStudentID;
    }

    public void setGivenStudentID(String givenStudentID) {
        this.givenStudentID = givenStudentID;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Integer getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(Integer creditHours) {
        this.creditHours = creditHours;
    }

    public Integer getProgrameId() {
        return programeId;
    }

    public void setProgrameId(Integer programeId) {
        this.programeId = programeId;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public Integer getStudyModeID() {
        return studyModeID;
    }

    public void setStudyModeID(Integer studyModeID) {
        this.studyModeID = studyModeID;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }
}
