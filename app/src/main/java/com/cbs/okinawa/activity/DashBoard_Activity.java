package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityDashBoardBinding;

public class DashBoard_Activity extends AppCompatActivity {
    ActivityDashBoardBinding dashBoardBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoardBinding= DataBindingUtil.setContentView(this,R.layout.activity_dash_board);
        mContext=DashBoard_Activity.this;
        initView();
    }

    private void initView() {

        dashBoardBinding.llProAllFG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ProAllocationFGActivity.class);
                startActivity(intent);
            }
        });
        dashBoardBinding.llProAllSFG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ProAllocationSFG_Activity.class);
                startActivity(intent);
            }
        });
        dashBoardBinding.llCheckStockInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,CheckStockInfo_Activity.class);
                startActivity(intent);
            }
        });
        dashBoardBinding.llScanPickList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ScanPickList_Activity.class);
                startActivity(intent);
            }
        });
        dashBoardBinding.llScanDeliveryNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ScanDeliveryNote_Activity.class);
                startActivity(intent);
            }
        });

    }
}