package com.cbs.okinawa.retrofit;


import com.cbs.okinawa.model.ItemCode;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.model.OkinaProduDC;
import com.cbs.okinawa.model.login.ValidateUser;
import com.cbs.okinawa.model.userregistration.UserReg;
import com.cbs.okinawa.model.userregistration.UserRegistration;
import com.cbs.okinawa.postmodel.ItemNew;
import com.cbs.okinawa.postmodel.OkinProdAPINEWPost;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("api/OkinaProdu")
    Call<List<OkinaProdu>> getOkinaProdu(
            @Query("status") String status);


    @GET("api/OkinaProduDC")
    Call<List<OkinaProduDC>> getOkinaProduDC(
            @Query("DocEntry") String DocEntry);


    @GET("api/OkinaProduITM")
    Call<List<ItemCode>> getItemCode(
            @Query("ItemCode") String ItemCode);

    @GET("api/ValidateUser")
    Call<ValidateUser> loginUser(
        @Query("UserId") String UserId,
        @Query("Password") String Password);

    @GET("api/ValidateUser")
    Call<List<ValidateUser>> login(
            @Query("UserId") String UserId,
            @Query("Password") String Password);


//    @Headers("Content-Type: application/json")
//    @POST("api/VehicleExpense")
//    Call<VehicleExpense> vehicleExpensePost(@Body VehicleExpenseList vehicleExpenseList);


    @Headers("Content-Type: application/json")
    @POST("api/OkinawaProductionAPINEW")
    Call<ItemNew> itemNewPost(@Body OkinProdAPINEWPost okinProdAPINEWPost);

    @Headers("Content-Type: application/json")
    @POST("UserRegistration/UserReg")
    Call<UserReg> postUserRegistration(@Body UserRegistration userRegistration);


}
