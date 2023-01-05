package com.cbs.okinawa.retrofit;


import com.cbs.okinawa.model.ItemCode;
import com.cbs.okinawa.model.OkinaProdu;
import com.cbs.okinawa.model.OkinaProduDC;
import com.cbs.okinawa.model.login.ValidateUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
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


}
