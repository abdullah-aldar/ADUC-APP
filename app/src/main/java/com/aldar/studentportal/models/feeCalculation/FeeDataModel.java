package com.aldar.studentportal.models.feeCalculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeeDataModel {
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("tuition_Fees")
    @Expose
    private Double tuitionFees;
    @SerializedName("scholarship_Amount")
    @Expose
    private Double scholarshipAmount;
    @SerializedName("admin_Fees")
    @Expose
    private Double adminFees;
    @SerializedName("book_Fees")
    @Expose
    private Double bookFees;
    @SerializedName("vat")
    @Expose
    private Double vat;
    @SerializedName("previous_Balance")
    @Expose
    private Double previousBalance;
    @SerializedName("net_Fees")
    @Expose
    private Double netFees;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("courses")
    @Expose
    private List<CoursesFeeModel> courses = null;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(Double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public Double getScholarshipAmount() {
        return scholarshipAmount;
    }

    public void setScholarshipAmount(Double scholarshipAmount) {
        this.scholarshipAmount = scholarshipAmount;
    }

    public Double getAdminFees() {
        return adminFees;
    }

    public void setAdminFees(Double adminFees) {
        this.adminFees = adminFees;
    }

    public Double getBookFees() {
        return bookFees;
    }

    public void setBookFees(Double bookFees) {
        this.bookFees = bookFees;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(Double previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Double getNetFees() {
        return netFees;
    }

    public void setNetFees(Double netFees) {
        this.netFees = netFees;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<CoursesFeeModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesFeeModel> courses) {
        this.courses = courses;
    }
}
