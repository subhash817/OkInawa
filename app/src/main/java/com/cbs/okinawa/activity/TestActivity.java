package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityTestBinding;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding testBinding;
    Context mContext;
    String userName, userId, password;
    private Button scanBtn;
    private TextView formatTxt, contentTxt, batteryContentTxt, chargerContentTxt;
    int buttonId = 0;
    private Button btnClick;
    ImageView imgScanVinNo, imgScanBattery, imgScanCharger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        mContext = TestActivity.this;
        userName = CommonMethods.getPrefsData(mContext, PrefrenceKey.UserName, "");
        userId = CommonMethods.getPrefsData(mContext, PrefrenceKey.UserId, "");
        password = CommonMethods.getPrefsData(mContext, PrefrenceKey.Password, "");
        contentTxt = (TextView) findViewById(R.id.scan_content);
        batteryContentTxt = (TextView) findViewById(R.id.barcodeBatteryNo);
        chargerContentTxt = (TextView) findViewById(R.id.barcodeCharger);
        imgScanVinNo = findViewById(R.id.imgScanVinNo);
        imgScanCharger = findViewById(R.id.imgScanCharger);
        imgScanBattery = findViewById(R.id.imgScanBattery);
        testBinding.imgScanVinNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.imgScanVinNo) {

                }

                IntentIntegrator scanIntegrator = new IntentIntegrator(TestActivity.this);
                scanIntegrator.initiateScan();
            }

        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {


//we have a result
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
        String scanContent = scanningResult.getContents();
        //String scanFormat = scanningResult.getFormatName();

        contentTxt.setText(scanContent);
        batteryContentTxt.setText(scanContent);
        chargerContentTxt.setText(scanContent);
    }
}