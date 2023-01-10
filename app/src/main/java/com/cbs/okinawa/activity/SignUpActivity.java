package com.cbs.okinawa.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cbs.okinawa.R;
import com.cbs.okinawa.databinding.ActivitySignUpBinding;
import com.cbs.okinawa.model.userregistration.UserRegistration;
import com.cbs.okinawa.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signUpBinding;
    Context mMontext;
    String userName, mobileNo, emailID;
    ArrayList<UserRegistration> mArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mMontext = SignUpActivity.this;
        getInitView();
    }

    private void getInitView() {
        signUpBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = signUpBinding.edtName.getText().toString().trim();
                mobileNo = signUpBinding.edtMobile.getText().toString().trim();
                emailID = signUpBinding.edtEmail.getText().toString().trim();
                if (userName.isEmpty()) {
                    signUpBinding.edtName.setError("Please Enter Name");
                }
                if (userName.isEmpty()) {
                    signUpBinding.edtMobile.setError("Please Enter Mobile Number");
                }
                if (userName.isEmpty()) {
                    signUpBinding.edtEmail.setError("Please Enter Email ");
                }
                UserRegistration userRegistration=new UserRegistration(userName,mobileNo,emailID);
                RetrofitClient.getClient1().postUserRegistration(userRegistration).enqueue(new Callback<UserRegistration>() {
                    @Override
                    public void onResponse(Call<UserRegistration> call, Response<UserRegistration> response) {
                        if (response.code() == 200 && response.body() != null) {
                            Log.d("User_Registration", response.toString());
                            UserRegistration userRegistration1 = response.body();
                            Toast.makeText(mMontext, userRegistration1.getReturN_MESSAGE().toString(), Toast.LENGTH_SHORT).show();

//                            if (userRegistration1.getReturN_CODE().equalsIgnoreCase("1")) {
//                                Toast.makeText(mMontext, userRegistration1.getReturN_MESSAGE().toString(), Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                String code = userRegistration1.getReturN_CODE();
//                                System.out.println("UserCode" + code);
//                                Toast.makeText(mMontext, userRegistration1.getUserId().toString(), Toast.LENGTH_SHORT).show();
//
//                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<UserRegistration> call, Throwable t) {
                        Toast.makeText(mMontext, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }
}