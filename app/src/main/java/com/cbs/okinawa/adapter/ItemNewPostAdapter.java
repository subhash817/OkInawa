package com.cbs.okinawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.postmodel.ItemNew;

import java.util.List;

public class ItemNewPostAdapter extends RecyclerView.Adapter<ItemNewPostAdapter.myViewHolder> {
    private List<ItemNew> itemNewList;
    Context context;

    public ItemNewPostAdapter(Context context, List<ItemNew> itemNewList) {
        this.itemNewList = itemNewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemNewPostAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_new_post, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewPostAdapter.myViewHolder holder, int position) {
        ItemNew itemNew = itemNewList.get(position);
        holder.tvDocEntry.setText(itemNew.getDocEntry());
        holder.tvDT.setText(itemNew.getDt());
        holder.tvProOrdNo.setText(itemNew.getProductionOrderNo());
        holder.tvFGITEM.setText(itemNew.getFgItem());
        holder.tvFGQTY.setText(itemNew.getFgQty());
        holder.tvVinNo.setText(itemNew.getVinNo());
        holder.tvBatteryNO.setText(itemNew.getBatteryNO());
        holder.tvChargerNO.setText(itemNew.getChargerNO());

    }

    @Override
    public int getItemCount() {
        return itemNewList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvDocEntry, tvDT, tvProOrdNo, tvFGITEM, tvFGQTY, tvVinNo, tvBatteryNO, tvChargerNO;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDocEntry = itemView.findViewById(R.id.tvDocEntry);
            tvDT = itemView.findViewById(R.id.tvDT);
            tvProOrdNo = itemView.findViewById(R.id.tvProOrdNo);
            tvFGITEM = itemView.findViewById(R.id.tvFGITEM);
            tvFGQTY = itemView.findViewById(R.id.tvFGQTY);
            tvVinNo = itemView.findViewById(R.id.tvVinNo);
            tvBatteryNO = itemView.findViewById(R.id.tvBatteryNO);
            tvChargerNO = itemView.findViewById(R.id.tvChargerNO);
        }
    }
}
