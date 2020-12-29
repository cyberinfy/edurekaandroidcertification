package com.tcs.projectoneweatherapp.interfaces;


import com.tcs.projectoneweatherapp.datamodel.WeatherObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {
    @GET("{search}")
    Call<WeatherObj> getWeatherData(@Path("search") String search, @Query("q") String city, @Query("cnt") int cnt, @Query("AppID") String appID);
}