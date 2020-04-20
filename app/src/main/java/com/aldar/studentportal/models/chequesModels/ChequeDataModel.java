package com.aldar.studentportal.models.chequesModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChequeDataModel {
    @SerializedName("transId")
    @Expose
    private String transId;
    @SerializedName("chequeNo")
    @Expose
    private String chequeNo;
    @SerializedName("chequeDate")
    @Expose
    private String chequeDate;
    @SerializedName("amount")
    @Expose
    private Double amount;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
