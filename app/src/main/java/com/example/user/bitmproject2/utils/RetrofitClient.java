package com.example.user.bitmproject2.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
