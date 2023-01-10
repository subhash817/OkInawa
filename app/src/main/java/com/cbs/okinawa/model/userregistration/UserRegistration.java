package com.cbs.okinawa.model.userregistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegistration {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("databaseName")
    @Expose
    private String databaseName;
    @SerializedName("tran_Mode")
    @Expose
    private String tranMode;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("returN_CODE")
    @Expose
    private String returN_CODE;
    @SerializedName("returN_MESSAGE")
    @Expose
    private String returN_MESSAGE;


    public UserRegistration(String userName, String mobileNo, String emailID) {
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.emailID = emailID;
    }

    public String getReturN_CODE() {
        return returN_CODE;
    }

    public void setReturN_CODE(String returN_CODE) {
        this.returN_CODE = returN_CODE;
    }

    public String getReturN_MESSAGE() {
        return returN_MESSAGE;
    }

    public void setReturN_MESSAGE(String returN_MESSAGE) {
        this.returN_MESSAGE = returN_MESSAGE;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTranMode() {
        return tranMode;
    }

    public void setTranMode(String tranMode) {
        this.tranMode = tranMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}