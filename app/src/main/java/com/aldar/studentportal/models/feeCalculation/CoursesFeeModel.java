package com.aldar.studentportal.models.feeCalculation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesFeeModel {
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("books")
    @Expose
    private Double books;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBooks() {
        return books;
    }

    public void setBooks(Double books) {
        this.books = books;
    }
}
