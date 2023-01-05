package com.cbs.okinawa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivityLoginBinding;
import com.cbs.okinawa.model.login.ValidateUser;
import com.cbs.okinawa.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String[] courses = {"OKINAWA", "OKINAWA_1", "OKINAWA_2", "OKINAWA_3", "OKINAWA_4", "OKINAWA_5"};
        Spinner spino = findViewById(R.id.spinner_Project);
        // spin.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, courses);
        arrayAdapter.setDropDownViewResource(androidx.databinding.library.baseAdapters.R.layout.support_simple_spinner_dropdown_item);
        spino.setAdapter(arrayAdapter);
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = loginBinding.edtUserId.getText().toString();
                String pass = loginBinding.edtPassword.getText().toString();
//                if (userId.isEmpty()) {
//                    loginBinding.edtUserId.setError("Enter User Id");
//                } else if (pass.isEmpty()) {
//                    loginBinding.edtPassword.setError("Enter User Id");
//                }
//                RetrofitClient.getClient().loginUser(userId,pass).enqueue(new Callback<ValidateUser>() {
//                    @Override
//                    public void onResponse(Call<ValidateUser> call, Response<ValidateUser> response) {
//                        if (response.code()==200 && response.body()!=null){
//                            Toast.makeText(mContext, "successful", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ValidateUser> call, Throwable t) {
//                        Toast.makeText(mContext, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });

              Intent i = new Intent(mContext, DashBoard_Activity.class);
                startActivity(i);
            }
        });
    }

}