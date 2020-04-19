package com.aldar.studentportal.models.announcementModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnnouncementDataModel {
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("orderID")
    @Expose
    private Integer orderID;
    @SerializedName("isNew")
    @Expose
    private Boolean isNew;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

}
