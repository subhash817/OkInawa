package com.cbs.okinawa.retrofit;


import com.cbs.okinawa.model.OkinaProdu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/OkinaProdu")
    Call<OkinaProdu> getOkinaProdu(
            @Query("status") String status);



}
