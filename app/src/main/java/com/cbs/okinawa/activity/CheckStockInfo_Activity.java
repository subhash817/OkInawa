package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityCheckStockInfoBinding;

public class CheckStockInfo_Activity extends AppCompatActivity {
    ActivityCheckStockInfoBinding checkStockInfoBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkStockInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_stock_info);
        mContext = CheckStockInfo_Activity.this;
        initView();
    }

    private void initView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Check Stock Info");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }
}