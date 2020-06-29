package com.aldar.studentportal.models.financeModel;

import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinanceDataModel {
    @SerializedName("rec")
    @Expose
    private Integer rec;
    @SerializedName("tranDate")
    @Expose
    private String tranDate;
    @SerializedName("jounalID")
    @Expose
    private String jounalID;
    @SerializedName("descrption")
    @Expose
    private String descrption;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("amountD")
    @Expose
    private String amountD;
    @SerializedName("amountC")
    @Expose
    private String amountC;
    @SerializedName("accCodeM")
    @Expose
    private String accCodeM;
    @SerializedName("accName")
    @Expose
    private String accName;
    @SerializedName("totalCount")
    @Expose
    private Double totalCount;
    @SerializedName("chequeNo")
    @Expose
    private String chequeNo;
    @SerializedName("settelmment")
    @Expose
    private Object settelmment;
    @SerializedName("cheqStatus")
    @Expose
    private String cheqStatus;
    @SerializedName("symbol")
    @Expose
    private String symbol;

    public Integer getRec() {
        return rec;
    }

    public void setRec(Integer rec) {
        this.rec = rec;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getJounalID() {
        return jounalID;
    }

    public void setJounalID(String jounalID) {
        this.jounalID = jounalID;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getAmount() {

        return amount;
    }

    public void setAmount(String amount) {

        this.amount = amount;
    }

    public String getAmountD() {
        return amountD;
    }

    public void setAmountD(String amountD) {
        this.amountD = amountD;
    }

    public String getAmountC() {
        return amountC;
    }

    public void setAmountC(String amountC) {
        this.amountC = amountC;

    }

    public String getAccCodeM() {
        return accCodeM;
    }

    public void setAccCodeM(String accCodeM) {
        this.accCodeM = accCodeM;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;

    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Object getSettelmment() {
        return settelmment;
    }

    public void setSettelmment(Object settelmment) {
        this.settelmment = settelmment;
    }

    public String getCheqStatus() {
        return cheqStatus;
    }

    public void setCheqStatus(String cheqStatus) {
        this.cheqStatus = cheqStatus;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
