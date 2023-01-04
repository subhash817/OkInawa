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
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

public class ScanVinInfo_Activity extends AppCompatActivity {
    ActivityScanVinInfoBinding vinInfoBinding;
    Context mContext;
    String proOrder,Quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vinInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_scan_vin_info);
        mContext = ScanVinInfo_Activity.this;
        proOrder= CommonMethods.getPrefsData(mContext, PrefrenceKey.ProOrder,"");
        Quantity= CommonMethods.getPrefsData(mContext, PrefrenceKey.Quantity,"");
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
        vinInfoBinding.tvProdOrder.setText(proOrder);
        vinInfoBinding.tvQuantity.setText(Quantity);

    }


}