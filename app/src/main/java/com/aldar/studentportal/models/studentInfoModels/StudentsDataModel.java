package com.aldar.studentportal.models.studentInfoModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentsDataModel {

    @SerializedName("studentID")
    @Expose
    private Integer studentID;
    @SerializedName("givenStudentId")
    @Expose
    private String givenStudentId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("emailVerify")
    @Expose
    private Integer emailVerify;
    @SerializedName("mobileVerify")
    @Expose
    private Integer mobileVerify;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("concentration")
    @Expose
    private String concentration;
    @SerializedName("advisor")
    @Expose
    private String advisor;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("isLoginBlock")
    @Expose
    private String isLoginBlock;
    @SerializedName("isLoginBlock_Reason")
    @Expose
    private String isLoginBlockReason;
    @SerializedName("cgpa")
    @Expose
    private Double cgpa;
    @SerializedName("creditHoursCompleted")
    @Expose
    private Integer creditHoursCompleted;
    @SerializedName("studyPlanId")
    @Expose
    private String studyPlanId;
    @SerializedName("academicWarningLetter")
    @Expose
    private String academicWarningLetter;
    @SerializedName("isContactStored")
    @Expose
    private Integer isContactStored;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getGivenStudentId() {
        return givenStudentId;
    }

    public void setGivenStudentId(String givenStudentId) {
        this.givenStudentId = givenStudentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(Integer emailVerify) {
        this.emailVerify = emailVerify;
    }

    public Integer getMobileVerify() {
        return mobileVerify;
    }

    public void setMobileVerify(Integer mobileVerify) {
        this.mobileVerify = mobileVerify;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIsLoginBlock() {
        return isLoginBlock;
    }

    public void setIsLoginBlock(String isLoginBlock) {
        this.isLoginBlock = isLoginBlock;
    }

    public String getIsLoginBlockReason() {
        return isLoginBlockReason;
    }

    public void setIsLoginBlockReason(String isLoginBlockReason) {
        this.isLoginBlockReason = isLoginBlockReason;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getCreditHoursCompleted() {
        return creditHoursCompleted;
    }

    public void setCreditHoursCompleted(Integer creditHoursCompleted) {
        this.creditHoursCompleted = creditHoursCompleted;
    }

    public String getStudyPlanId() {
        return studyPlanId;
    }

    public void setStudyPlanId(String studyPlanId) {
        this.studyPlanId = studyPlanId;
    }

    public String getAcademicWarningLetter() {
        return academicWarningLetter;
    }

    public void setAcademicWarningLetter(String academicWarningLetter) {
        this.academicWarningLetter = academicWarningLetter;
    }

    public Integer getIsContactStored() {
        return isContactStored;
    }

    public void setIsContactStored(Integer isContactStored) {
        this.isContactStored = isContactStored;
    }

}
