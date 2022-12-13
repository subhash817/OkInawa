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
import com.cbs.okinawa.databinding.ActivityScanVinInfoBinding;

public class ScanVinInfo_Activity extends AppCompatActivity {
    ActivityScanVinInfoBinding vinInfoBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vinInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_scan_vin_info);
        mContext = ScanVinInfo_Activity.this;
        intiView();
    }

    private void intiView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Scan VIN Info");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }


}