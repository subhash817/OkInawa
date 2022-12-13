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
import com.cbs.okinawa.databinding.ActivityProAllocationSfgBinding;

public class ProAllocationSFG_Activity extends AppCompatActivity {
    ActivityProAllocationSfgBinding sfgBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sfgBinding = DataBindingUtil.setContentView(this, R.layout.activity_pro_allocation_sfg);
        mContext = ProAllocationSFG_Activity.this;
        initView();
    }

    private void initView() {
        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName = findViewById(R.id.txtScreenName);
        screenName.setText("Pro Allocation (SFG)");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        sfgBinding.btnSCANVINInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ScanVinInfo_Activity.class);
                startActivity(intent);
            }
        });

    }
}