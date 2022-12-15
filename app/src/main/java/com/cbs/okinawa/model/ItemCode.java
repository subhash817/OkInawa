package com.cbs.okinawa.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCode {

@SerializedName("OnHand")
@Expose
private String onHand;

public String getOnHand() {
return onHand;
}

public void setOnHand(String onHand) {
this.onHand = onHand;
}

}