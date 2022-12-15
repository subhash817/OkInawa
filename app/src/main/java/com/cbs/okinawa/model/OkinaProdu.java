package com.cbs.okinawa.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OkinaProdu {

@SerializedName("Status")
@Expose
private String status;
@SerializedName("ProOrder")
@Expose
private String proOrder;
@SerializedName("Docentry")
@Expose
private String docentry;

@SerializedName("Date")
@Expose
private String date;

    public OkinaProdu(String proOrder, String status, String docentry) {
    }

    public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getProOrder() {
return proOrder;
}

public void setProOrder(String proOrder) {
this.proOrder = proOrder;
}

public String getDocentry() {
return docentry;
}

public void setDocentry(String docentry) {
this.docentry = docentry;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

}