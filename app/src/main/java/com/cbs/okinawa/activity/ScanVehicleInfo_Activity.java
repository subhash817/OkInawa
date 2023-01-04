package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityScanVehicleInfoBinding;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

public class ScanVehicleInfo_Activity extends AppCompatActivity {
    ActivityScanVehicleInfoBinding scanVehInfoBinding;
    Context mContext;
    String proOrder,Quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       scanVehInfoBinding= DataBindingUtil.setContentView(this,R.layout.activity_scan_vehicle_info);
       mContext=ScanVehicleInfo_Activity.this;
        proOrder= CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder,"");
        Quantity= CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity,"");
        getInitView();
    }

    private void getInitView() {

        ImageView setActivitback = findViewById(R.id.back);
        TextView screenName=findViewById(R.id.txtScreenName);
        screenName.setText("Scan Vehicle Info");
        setActivitback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        scanVehInfoBinding.tvProdOrder.setText(proOrder);
        scanVehInfoBinding.tvQuantity.setText(Quantity);


    }
}