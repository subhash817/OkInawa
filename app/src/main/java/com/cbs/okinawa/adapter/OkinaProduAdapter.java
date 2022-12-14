package com.cbs.okinawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.ItemClickListener;
import com.cbs.okinawa.R;
import com.cbs.okinawa.model.OkinaProdu;

import java.util.ArrayList;
import java.util.List;

public class OkinaProduAdapter extends RecyclerView.Adapter<OkinaProduAdapter.myViewHolder> implements Filterable {
    private List<OkinaProdu> okinaProdus;
    private List<OkinaProdu> okinaProduFullList;
    Context context;
    ItemClickListener itemClickListener;
    String docNumber;


    public OkinaProduAdapter(Context context, List<OkinaProdu> okinaProdus, ItemClickListener itemClickListener) {
        this.okinaProdus = okinaProdus;
        this.context = context;
        this.itemClickListener = itemClickListener;
        okinaProduFullList = new ArrayList<>(okinaProdus);
    }

    public void setFilterList(List<OkinaProdu> okinaProduList) {
        this.okinaProdus = okinaProduList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OkinaProduAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.okina_produ_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OkinaProduAdapter.myViewHolder holder, int position) {
        OkinaProdu okinaProdu = okinaProdus.get(position);
        holder.tv_Status.setText(okinaProdu.getStatus());
        holder.tv_ProOrder.setText(okinaProdu.getProOrder());
        holder.tv_Docentry.setText(okinaProdu.getDocentry());
        holder.tv_Date.setText(okinaProdu.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tv_Docentry.setText(okinaProdu.getDocentry());

                if (itemClickListener != null) {
                    itemClickListener.onClick(okinaProdu.getDocentry());

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return okinaProdus.size();
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


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<OkinaProdu> filterList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(okinaProduFullList);

            } else {
                String p = charSequence.toString().toString().trim();
                for (OkinaProdu okinaProdu : okinaProduFullList) {
                    if (okinaProdu.getDocentry().toLowerCase().contains(p)) {
                        filterList.add(okinaProdu);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            okinaProdus.clear();
            okinaProdus.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };
}
