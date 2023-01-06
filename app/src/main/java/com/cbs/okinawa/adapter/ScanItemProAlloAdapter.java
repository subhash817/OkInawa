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

public class ScanItemProAlloAdapter extends RecyclerView.Adapter<ScanItemProAlloAdapter.myViewHolder> {
    private List<ItemNew> itemNewList;
    Context context;

    public ScanItemProAlloAdapter(Context context, List<ItemNew> itemNewList) {
        this.context = context;
        this.itemNewList = itemNewList;

    }

    @NonNull
    @Override
    public ScanItemProAlloAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.scan_item_proallocation, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanItemProAlloAdapter.myViewHolder holder, int position) {
        ItemNew itemNew = itemNewList.get(position);
        holder.txtVinNo.setText(itemNew.getVinNo());
        holder.txtBatteryNo.setText(itemNew.getBatteryNO());
        holder.txtChargerNo.setText(itemNew.getChargerNO());


    }

    @Override
    public int getItemCount() {
        return itemNewList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView txtVinNo, txtBatteryNo, txtChargerNo;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtVinNo = itemView.findViewById(R.id.txtVinNo);
            this.txtBatteryNo = itemView.findViewById(R.id.txtBatteryNo);
            this.txtChargerNo = itemView.findViewById(R.id.txtChargerNo);
        }
    }
}
