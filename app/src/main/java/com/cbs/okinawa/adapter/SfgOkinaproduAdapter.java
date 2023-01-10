package com.cbs.okinawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.SfgItemClcikListener;
import com.cbs.okinawa.model.OkinaProdu;

import java.util.List;

public class SfgOkinaproduAdapter extends RecyclerView.Adapter<SfgOkinaproduAdapter.myViewHolder> {
    private List<OkinaProdu> okinaProdulist;
    Context context;
    SfgItemClcikListener sfgItemClcikListener;

    public SfgOkinaproduAdapter(Context context, List<OkinaProdu> okinaProdulist, SfgItemClcikListener sfgItemClcikListener) {
        this.context = context;
        this.okinaProdulist = okinaProdulist;
        this.sfgItemClcikListener = sfgItemClcikListener;
    }

    @NonNull
    @Override
    public SfgOkinaproduAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.okina_produ_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SfgOkinaproduAdapter.myViewHolder holder, int position) {
        OkinaProdu okinaProdu = okinaProdulist.get(position);
        holder.tv_Date.setText(okinaProdu.getDate());
        holder.tv_Docentry.setText(okinaProdu.getDocentry());
        holder.tv_ProOrder.setText(okinaProdu.getProOrder());
        holder.tv_Status.setText(okinaProdu.getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv_Docentry.setText(okinaProdu.getDocentry());
                holder.tv_Date.setText(okinaProdu.getDate());
                if (sfgItemClcikListener != null) {
                    sfgItemClcikListener.onClick(okinaProdu.getDocentry());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return okinaProdulist.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Status, tv_ProOrder, tv_Docentry, tv_Date;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_Status = itemView.findViewById(R.id.tv_Status);
            this.tv_ProOrder = itemView.findViewById(R.id.tv_ProOrder);
            this.tv_Docentry = itemView.findViewById(R.id.tv_Docentry);
            this.tv_Date = itemView.findViewById(R.id.tv_Date);
        }
    }
}
