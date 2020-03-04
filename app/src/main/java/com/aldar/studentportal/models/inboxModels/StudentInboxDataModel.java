package com.aldar.studentportal.models.inboxModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInboxDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("sentBy")
    @Expose
    private String sentBy;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("sentTo")
    @Expose
    private String sentTo;
    @SerializedName("inOut")
    @Expose
    private String inOut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

}
