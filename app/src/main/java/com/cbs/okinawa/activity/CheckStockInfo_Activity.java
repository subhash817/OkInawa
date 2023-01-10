package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityCheckStockInfoBinding;
import com.cbs.okinawa.model.ItemCode;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.CustomProgressbar;
import com.cbs.okinawa.utils.PrefrenceKey;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckStockInfo_Activity extends AppCompatActivity {
    ActivityCheckStockInfoBinding checkStockInfoBinding;
    Context mContext;
    String itemCode, onHand;

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
        checkStockInfoBinding.txtBarCode.setText(itemCode);
        checkStockInfoBinding.tvStock.setText(onHand);
        getInitView();
    }

    private void getInitView() {


        checkStockInfoBinding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStockInfoBinding.txtBarCode.setText("");
                checkStockInfoBinding.tvStock.setText("");
            }

        });
        String itemCode = checkStockInfoBinding.txtBarCode.getText().toString();
        CustomProgressbar.showProgressBar(mContext,false);
        RetrofitClient.getClient().getItemCode(itemCode).enqueue(new Callback<List<ItemCode>>() {
            @Override
            public void onResponse(Call<List<ItemCode>> call, Response<List<ItemCode>> response) {
                if (response.code() == 200 && response.body() != null) {
                    CustomProgressbar.hideProgressBar();
                    Log.d("Url", response.toString());
                    List<ItemCode> itemCodes = response.body();
                    if (itemCodes.size() > 0) {
                        for (int i = 0; i < itemCodes.size(); i++) {
                            checkStockInfoBinding.tvStock.setText(itemCodes.get(i).getOnHand());
                            CommonMethods.setPrefsData(mContext, PrefrenceKey.OnHand, itemCodes.get(i).getOnHand());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ItemCode>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        checkStockInfoBinding.imgScanItemCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.imgScanVinNo) {

                }

                IntentIntegrator scanIntegrator = new IntentIntegrator(CheckStockInfo_Activity.this);
                scanIntegrator.initiateScan();
            }
        });
        checkStockInfoBinding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStockInfoBinding.txtBarCode.setText("");
                checkStockInfoBinding.tvStock.setText("");
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
        String scanFormat = scanningResult.getFormatName();
        checkStockInfoBinding.txtBarCode.setText(scanContent);
    }
}