package com.cbs.okinawa.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCode {

@SerializedName("OnHand")
@Expose
private Double onHand;

public Double getOnHand() {
return onHand;
}

public void setOnHand(Double onHand) {
this.onHand = onHand;
}

}