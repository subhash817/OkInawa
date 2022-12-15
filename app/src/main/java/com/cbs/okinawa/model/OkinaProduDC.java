package com.cbs.okinawa.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OkinaProduDC {

@SerializedName("ItemCode")
@Expose
private String itemCode;
@SerializedName("Description")
@Expose
private String description;
@SerializedName("Quantity")
@Expose
private String quantity;
@SerializedName("IssueRef")
@Expose
private String issueRef;
@SerializedName("ReceiptRef")
@Expose
private String receiptRef;

public String getItemCode() {
return itemCode;
}

public void setItemCode(String itemCode) {
this.itemCode = itemCode;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getQuantity() {
return quantity;
}

public void setQuantity(String quantity) {
this.quantity = quantity;
}

public String getIssueRef() {
return issueRef;
}

public void setIssueRef(String issueRef) {
this.issueRef = issueRef;
}

public String getReceiptRef() {
return receiptRef;
}

public void setReceiptRef(String receiptRef) {
this.receiptRef = receiptRef;
}

}