package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityStartScanningBinding;

public class StartScanning_Activity extends AppCompatActivity {
    ActivityStartScanningBinding startScanningBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startScanningBinding = DataBindingUtil.setContentView(this, R.layout.activity_start_scanning);
        mContext = StartScanning_Activity.this;
        initView();


    }

    private void initView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Start Scanning");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }
}