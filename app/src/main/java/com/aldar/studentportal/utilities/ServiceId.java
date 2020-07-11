package com.aldar.studentportal.utilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceId {
    @SerializedName("serviceId")
    @Expose
    private String serviceId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
