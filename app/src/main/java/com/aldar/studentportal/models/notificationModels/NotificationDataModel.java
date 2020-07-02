package com.aldar.studentportal.models.notificationModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationDataModel {

    @SerializedName("notificationId")
    @Expose
    private Integer notificationId;
    @SerializedName("messageTitle")
    @Expose
    private String messageTitle;
    @SerializedName("messageBody")
    @Expose
    private String messageBody;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
