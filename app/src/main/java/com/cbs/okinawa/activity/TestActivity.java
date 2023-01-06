package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityTestBinding;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding testBinding;
    Context mContext;
    String userName, userId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        mContext = TestActivity.this;
        userName = CommonMethods.getPrefsData(mContext, PrefrenceKey.UserName, "");
        userId = CommonMethods.getPrefsData(mContext, PrefrenceKey.UserId, "");
        password = CommonMethods.getPrefsData(mContext, PrefrenceKey.Password, "");
        testBinding.txtuserName.setText(userName);
        testBinding.txtPassword.setText(password);
        testBinding.txtUserId.setText(userId);


    }

}