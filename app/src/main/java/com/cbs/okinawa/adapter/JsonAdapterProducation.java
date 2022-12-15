package com.cbs.okinawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.model.OkinaProdu;

import java.util.ArrayList;
import java.util.List;

public class JsonAdapterProducation extends RecyclerView.Adapter<JsonAdapterProducation.myViewHolder> {
    private List<OkinaProdu> okinaProdus;
    private List<OkinaProdu> exampleListFull;
    Context context;

    public JsonAdapterProducation(Context context, List<OkinaProdu> okinaProdus) {
        this.okinaProdus = okinaProdus;
        this.context = context;
    }

    @NonNull
    @Override
    public JsonAdapterProducation.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doc_num_adapter, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonAdapterProducation.myViewHolder holder, int position) {
        OkinaProdu okinaProdu = okinaProdus.get(position);
        holder.tv_status.setText(okinaProdu.getStatus());
        holder.tv_pro_order.setText(okinaProdu.getProOrder());
        holder.tv_doc_enter.setText(okinaProdu.getDocentry());
        holder.tv_date.setText(okinaProdu.getDate());

    }



    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tv_status, tv_pro_order, tv_doc_enter, tv_date;
        ImageView imgSearchdata;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_pro_order = itemView.findViewById(R.id.tv_pro_order);
            tv_doc_enter = itemView.findViewById(R.id.tv_doc_enter);
            tv_date = itemView.findViewById(R.id.tv_date);
            imgSearchdata = itemView.findViewById(R.id.imgSearchdata);
        }
    }
    @Override
    public int getItemCount() {
        return okinaProdus.size();
    }
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<OkinaProdu> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (OkinaProdu item : exampleListFull) {
                    if (item.getProOrder().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            exampleList.clear();
//            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
}
