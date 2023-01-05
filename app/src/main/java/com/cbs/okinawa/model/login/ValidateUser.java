package com.cbs.okinawa.model.login;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateUser {

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
private Object mobileNo;
@SerializedName("emailID")
@Expose
private Object emailID;
@SerializedName("databaseName")
@Expose
private Object databaseName;
@SerializedName("tran_Mode")
@Expose
private Object tranMode;
@SerializedName("status")
@Expose
private Object status;

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

public Object getMobileNo() {
return mobileNo;
}

public void setMobileNo(Object mobileNo) {
this.mobileNo = mobileNo;
}

public Object getEmailID() {
return emailID;
}

public void setEmailID(Object emailID) {
this.emailID = emailID;
}

public Object getDatabaseName() {
return databaseName;
}

public void setDatabaseName(Object databaseName) {
this.databaseName = databaseName;
}

public Object getTranMode() {
return tranMode;
}

public void setTranMode(Object tranMode) {
this.tranMode = tranMode;
}

public Object getStatus() {
return status;
}

public void setStatus(Object status) {
this.status = status;
}

}