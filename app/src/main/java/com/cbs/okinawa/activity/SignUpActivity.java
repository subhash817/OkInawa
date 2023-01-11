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
import com.cbs.okinawa.model.userregistration.UserReg;
import com.cbs.okinawa.model.userregistration.UserRegistration;
import com.cbs.okinawa.retrofit.RetrofitClient;
import com.cbs.okinawa.utils.CommonMethods;
import com.cbs.okinawa.utils.PrefrenceKey;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signUpBinding;
    Context mContext;
    String userName, userId, password, mobileNo, emailID, databaseName, tran_Mode, status;
    ArrayList<UserRegistration> mArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mContext = SignUpActivity.this;
        userId=CommonMethods.getPrefsData(mContext,PrefrenceKey.UserId,"");
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
                if (mobileNo.isEmpty()) {
                    signUpBinding.edtMobile.setError("Please Enter Mobile Number");
                }
                if (emailID.isEmpty()) {
                    signUpBinding.edtEmail.setError("Please Enter Email ");
                }
                UserRegistration userRegistration = new UserRegistration();
                //  String request = new Gson().toJson(userRegistration);
                userRegistration.setUserName(userName);
                userRegistration.setUserId("");
                userRegistration.setPassword("");
                userRegistration.setMobileNo(mobileNo);
                userRegistration.setEmailID(emailID);
                userRegistration.setDatabaseName("");
                userRegistration.setTranMode("");
                userRegistration.setStatus("");

//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(request);
//                    userRegistration1.setUserName(jsonObject.getString(userName));
//                    userRegistration1.setUserId(jsonObject.getString("0"));
//                    userRegistration1.setPassword(jsonObject.getString("0"));
//                    userRegistration1.setMobileNo(jsonObject.getString(mobileNo));
//                    userRegistration1.setEmailID(jsonObject.getString(emailID));
//                    userRegistration1.setDatabaseName(jsonObject.getString("test"));
//                    userRegistration1.setTranMode(jsonObject.getString("0"));
//                    userRegistration1.setStatus(jsonObject.getString("0 "));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


//                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(request).toString());


                RetrofitClient.getClient1().postUserRegistration(userRegistration).enqueue(new Callback<UserReg>() {
                    @Override
                    public void onResponse(Call<UserReg> call, Response<UserReg> response) {
                        UserReg userReg = response.body();
                        Log.d("user", response.toString());
                        if (response.code() == 200 && response.body() != null) {
                            if (userReg.getReturNCODE().equalsIgnoreCase("1")) {
                                Toast.makeText(mContext, userReg.getReturNMESSAGE(), Toast.LENGTH_SHORT).show();
                            } else {

                                signUpBinding.txtUserId.setText("Your User Id Is :"+userReg.getReturNCODE());
                                CommonMethods.setPrefsData(mContext, PrefrenceKey.UserId, userReg.getReturNCODE().toString());
                                Toast.makeText(mContext, "Your User Is: " + userReg.getReturNMESSAGE(), Toast.LENGTH_SHORT).show();

                            }


                        } else {

                            Toast.makeText(mContext, "Regisatration failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserReg> call, Throwable t) {
                        Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}