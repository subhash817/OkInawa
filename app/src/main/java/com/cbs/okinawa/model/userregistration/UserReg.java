package com.cbs.okinawa.model.userregistration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReg {

@SerializedName("returN_CODE")
@Expose
private String returNCODE;
@SerializedName("returN_MESSAGE")
@Expose
private String returNMESSAGE;

public String getReturNCODE() {
return returNCODE;
}

public void setReturNCODE(String returNCODE) {
this.returNCODE = returNCODE;
}

public String getReturNMESSAGE() {
return returNMESSAGE;
}

public void setReturNMESSAGE(String returNMESSAGE) {
this.returNMESSAGE = returNMESSAGE;
}

}