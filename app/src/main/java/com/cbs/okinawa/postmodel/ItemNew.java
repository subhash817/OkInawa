package com.cbs.okinawa.postmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemNew {

@SerializedName("DocEntry")
@Expose
private String docEntry;
@SerializedName("DT")
@Expose
private String dt;
@SerializedName("Production_Order_No")
@Expose
private String productionOrderNo;
@SerializedName("FG_ITEM")
@Expose
private String fgItem;
@SerializedName("FG_QTY")
@Expose
private String fgQty;
@SerializedName("VIN_NO")
@Expose
private String vinNo;
@SerializedName("Battery_NO")
@Expose
private String batteryNO;
@SerializedName("Charger_NO")
@Expose
private String chargerNO;

public String getDocEntry() {
return docEntry;
}

public void setDocEntry(String docEntry) {
this.docEntry = docEntry;
}

public String getDt() {
return dt;
}

public void setDt(String dt) {
this.dt = dt;
}

public String getProductionOrderNo() {
return productionOrderNo;
}

public void setProductionOrderNo(String productionOrderNo) {
this.productionOrderNo = productionOrderNo;
}

public String getFgItem() {
return fgItem;
}

public void setFgItem(String fgItem) {
this.fgItem = fgItem;
}

public String getFgQty() {
return fgQty;
}

public void setFgQty(String fgQty) {
this.fgQty = fgQty;
}

public String getVinNo() {
return vinNo;
}

public void setVinNo(String vinNo) {
this.vinNo = vinNo;
}

public String getBatteryNO() {
return batteryNO;
}

public void setBatteryNO(String batteryNO) {
this.batteryNO = batteryNO;
}

public String getChargerNO() {
return chargerNO;
}

public void setChargerNO(String chargerNO) {
this.chargerNO = chargerNO;
}

}