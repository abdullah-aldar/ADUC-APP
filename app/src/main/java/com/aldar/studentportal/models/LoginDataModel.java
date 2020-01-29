package com.aldar.studentportal.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataModel {


    @SerializedName("studentID")
    @Expose
    private Integer studentID;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("mobile")
    @Expose
    private Object mobile;
    @SerializedName("altMobile")
    @Expose
    private Object altMobile;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("changedate")
    @Expose
    private Object changedate;
    @SerializedName("userId")
    @Expose
    private Object userId;
    @SerializedName("counter")
    @Expose
    private Object counter;
    @SerializedName("alLowAccess")
    @Expose
    private Object alLowAccess;
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

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(Object altMobile) {
        this.altMobile = altMobile;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getChangedate() {
        return changedate;
    }

    public void setChangedate(Object changedate) {
        this.changedate = changedate;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getCounter() {
        return counter;
    }

    public void setCounter(Object counter) {
        this.counter = counter;
    }

    public Object getAlLowAccess() {
        return alLowAccess;
    }

    public void setAlLowAccess(Object alLowAccess) {
        this.alLowAccess = alLowAccess;
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

}

