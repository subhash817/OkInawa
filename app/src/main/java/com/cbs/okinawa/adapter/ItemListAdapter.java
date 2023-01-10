package com.cbs.okinawa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cbs.okinawa.R;
import com.cbs.okinawa.model.Item;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.myViewHolder> {
    private List<Item> itemList;
    Context context;

    public ItemListAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.myViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textView.setText(item.getOnHand() + "");

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.txt_OnHand);
        }
    }
}
