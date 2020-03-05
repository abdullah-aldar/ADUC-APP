package com.aldar.studentportal.models.letterModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LetterRequestDataModel {
    @SerializedName("serviceId")
    @Expose
    private Integer serviceId;
    @SerializedName("serviceName")
    @Expose
    private String serviceName;
    @SerializedName("eng_Letter")
    @Expose
    private Boolean engLetter;
    @SerializedName("arb_Letter")
    @Expose
    private Boolean arbLetter;
    @SerializedName("retailPrice")
    @Expose
    private Double retailPrice;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Boolean getEngLetter() {
        return engLetter;
    }

    public void setEngLetter(Boolean engLetter) {
        this.engLetter = engLetter;
    }

    public Boolean getArbLetter() {
        return arbLetter;
    }

    public void setArbLetter(Boolean arbLetter) {
        this.arbLetter = arbLetter;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

}
