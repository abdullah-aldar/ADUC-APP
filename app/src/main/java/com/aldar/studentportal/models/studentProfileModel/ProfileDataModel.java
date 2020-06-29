package com.aldar.studentportal.models.studentProfileModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataModel {

    @SerializedName("studentID")
    @Expose
    private String studentID;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("arabicStudentName")
    @Expose
    private String arabicStudentName;
    @SerializedName("personID")
    @Expose
    private String personID;
    @SerializedName("telephoneNumber")
    @Expose
    private String telephoneNumber;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("dateofBirth")
    @Expose
    private String dateofBirth;
    @SerializedName("highSchoolName")
    @Expose
    private String highSchoolName;
    @SerializedName("firstSemester")
    @Expose
    private String firstSemester;
    @SerializedName("lastSemester")
    @Expose
    private String lastSemester;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("highSchoolAddress")
    @Expose
    private String highSchoolAddress;
    @SerializedName("highSchoolPer")
    @Expose
    private String highSchoolPer;
    @SerializedName("yearofCompletion")
    @Expose
    private String yearofCompletion;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("english")
    @Expose
    private String english;
    @SerializedName("englishScore")
    @Expose
    private String englishScore;
    @SerializedName("englishPlacementScore")
    @Expose
    private Object englishPlacementScore;
    @SerializedName("mathematicsPlacementScore")
    @Expose
    private String mathematicsPlacementScore;
    @SerializedName("academicAdvisor")
    @Expose
    private String academicAdvisor;
    @SerializedName("maritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("residence")
    @Expose
    private String residence;
    @SerializedName("visaIssuePlace")
    @Expose
    private Object visaIssuePlace;
    @SerializedName("visaExpiryDate")
    @Expose
    private String visaExpiryDate;
    @SerializedName("emirateID")
    @Expose
    private String emirateID;
    @SerializedName("visaNumber")
    @Expose
    private String visaNumber;
    @SerializedName("religion")
    @Expose
    private String religion;

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

    public String getArabicStudentName() {
        return arabicStudentName;
    }

    public void setArabicStudentName(String arabicStudentName) {
        this.arabicStudentName = arabicStudentName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getHighSchoolName() {
        return highSchoolName;
    }

    public void setHighSchoolName(String highSchoolName) {
        this.highSchoolName = highSchoolName;
    }

    public String getFirstSemester() {
        return firstSemester;
    }

    public void setFirstSemester(String firstSemester) {
        this.firstSemester = firstSemester;
    }

    public String getLastSemester() {
        return lastSemester;
    }

    public void setLastSemester(String lastSemester) {
        this.lastSemester = lastSemester;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighSchoolAddress() {
        return highSchoolAddress;
    }

    public void setHighSchoolAddress(String highSchoolAddress) {
        this.highSchoolAddress = highSchoolAddress;
    }

    public String getHighSchoolPer() {
        return highSchoolPer;
    }

    public void setHighSchoolPer(String highSchoolPer) {
        this.highSchoolPer = highSchoolPer;
    }

    public String getYearofCompletion() {
        return yearofCompletion;
    }

    public void setYearofCompletion(String yearofCompletion) {
        this.yearofCompletion = yearofCompletion;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(String englishScore) {
        this.englishScore = englishScore;
    }

    public Object getEnglishPlacementScore() {
        return englishPlacementScore;
    }

    public void setEnglishPlacementScore(Object englishPlacementScore) {
        this.englishPlacementScore = englishPlacementScore;
    }

    public String getMathematicsPlacementScore() {
        return mathematicsPlacementScore;
    }

    public void setMathematicsPlacementScore(String mathematicsPlacementScore) {
        this.mathematicsPlacementScore = mathematicsPlacementScore;
    }

    public String getAcademicAdvisor() {
        return academicAdvisor;
    }

    public void setAcademicAdvisor(String academicAdvisor) {
        this.academicAdvisor = academicAdvisor;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Object getVisaIssuePlace() {
        return visaIssuePlace;
    }

    public void setVisaIssuePlace(Object visaIssuePlace) {
        this.visaIssuePlace = visaIssuePlace;
    }

    public String getVisaExpiryDate() {
        return visaExpiryDate;
    }

    public void setVisaExpiryDate(String visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
    }

    public String getEmirateID() {
        return emirateID;
    }

    public void setEmirateID(String emirateID) {
        this.emirateID = emirateID;
    }

    public String getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(String visaNumber) {
        this.visaNumber = visaNumber;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
