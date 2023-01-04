package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityCheckStockInfoBinding;
import com.cbs.okinawa.model.ItemCode;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

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
        itemCode = CommonMethods.getPrefsData(mContext, PrefrenceKey.ItemCode, "");
       // onHand = CommonMethods.getPrefsData(mContext, PrefrenceKey.OnHand, "");
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

        RetrofitClient.getClient().getItemCode(itemCode).enqueue(new Callback<List<ItemCode>>() {
            @Override
            public void onResponse(Call<List<ItemCode>> call, Response<List<ItemCode>> response) {
                if (response.code() == 200 && response.body() != null) {
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

            }
        });

    }

}