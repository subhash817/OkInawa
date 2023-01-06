package com.cbs.okinawa.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String URL_BASE = "http://192.168.1.16:89/";
    private static String URL_BASE_Local = "http://103.75.33.100:92/";


    public static ApiInterface getClient() {


        return retrofitBuilder().create(ApiInterface.class);
    }
    public static ApiInterface getClient1() {


        return retrofitBuilder1().create(ApiInterface.class);
    }



    public static Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-M  M-dd'T'HH:mm:ssZ").create();
    }

    private static OkHttpClient okHttp() {

        //set your desired log level
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        return new OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.HOURS)
                .readTimeout(2, TimeUnit.HOURS)
                .writeTimeout(2, TimeUnit.HOURS)
                .build();


    }

    private static Retrofit retrofitBuilder() {


        return new Retrofit.Builder()
                .client(okHttp())
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();

    }
    private static Retrofit retrofitBuilder1() {


        return new Retrofit.Builder()
                .client(okHttp())
                .baseUrl(URL_BASE_Local)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();

    }

    /*private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }*/
}
