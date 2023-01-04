package com.cbs.okinawa.postmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OkinProdAPINEWPost {

@SerializedName("ItemNew")
@Expose
private List<ItemNew> itemNew = null;

public List<ItemNew> getItemNew() {
return itemNew;
}

public void setItemNew(List<ItemNew> itemNew) {
this.itemNew = itemNew;
}

}