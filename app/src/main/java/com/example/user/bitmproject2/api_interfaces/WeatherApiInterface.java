package com.example.user.bitmproject2.api_interfaces;


import android.widget.EditText;


import com.example.user.bitmproject2.models.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherApiInterface {
   @GET
   retrofit2.Call<Weather> getWeatherByCity(@Url String url);

}