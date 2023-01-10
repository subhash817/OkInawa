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
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

import java.util.List;

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
                if (CommonMethods.isOnline(mContext)) {


                    String userId = loginBinding.edtUserId.getText().toString();
                    String pass = loginBinding.edtPassword.getText().toString();
                    if (userId.isEmpty()) {
                        loginBinding.edtUserId.setError("Enter User Id");
                    } else if (pass.isEmpty()) {
                        loginBinding.edtPassword.setError("Enter User Id");
                    }
                    RetrofitClient.getClient1().login(userId, pass).enqueue(new Callback<List<ValidateUser>>() {
                        @Override
                        public void onResponse(Call<List<ValidateUser>> call, Response<List<ValidateUser>> response) {
                            if (response.code() == 200 && response.body() != null) {
                                Log.d("Login", response.toString());
                                // List<ValidateUser> validateUserList=response.body();
                                ValidateUser validateUser = response.body().get(0);
                                CommonMethods.setPrefsData(mContext, PrefrenceKey.UserName, validateUser.getUserName());
                                CommonMethods.setPrefsData(mContext, PrefrenceKey.UserId, validateUser.getUserId());
                                CommonMethods.setPrefsData(mContext, PrefrenceKey.Password, validateUser.getPassword());
                                Toast.makeText(mContext, validateUser.getUserName().toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DashBoard_Activity.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<List<ValidateUser>> call, Throwable t) {

                        }
                    });

                } else {
                    CommonMethods.setSnackBar(loginBinding.rlRoot, getString(R.string.net));
                }

            }
        });
        loginBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

}