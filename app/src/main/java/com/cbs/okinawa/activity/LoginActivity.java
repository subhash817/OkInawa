package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding loginBinding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mContext = LoginActivity.this;
        getInitView();

    }

    private void getInitView() {

        String[] courses = {"OKINAWA","OKINAWA_1","OKINAWA_2","OKINAWA_3","OKINAWA_4","OKINAWA_5"};
        Spinner spino = findViewById(R.id.spinner_Project);
        // spin.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, courses);
        arrayAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        spino.setAdapter(arrayAdapter);
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DashBoard_Activity.class);
                startActivity(i);
            }
        });
    }

}