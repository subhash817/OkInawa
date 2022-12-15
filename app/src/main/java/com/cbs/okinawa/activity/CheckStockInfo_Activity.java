package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityCheckStockInfoBinding;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

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
        onHand = CommonMethods.getPrefsData(mContext, PrefrenceKey.OnHand, "");
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

//        if(chkFirst.isChecked()){
//            txt.setText(txt.getText().toString()+" "+chkFirst.getText().toString());
//        }
//        if(chkSecond.isChecked()){
//            txt.setText(txt.getText().toString()+" "+chkSecond.getText().toString());
//        }
//        if(chkThird.isChecked()){
//            txt.setText(txt.getText().toString()+" "+chkThird.getText().toString());
//        }

                }

        });
    }

}