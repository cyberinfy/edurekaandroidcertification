package com.tcs.projectoneweatherapp.interfaces;

import com.tcs.projectoneweatherapp.datamodel.WeatherObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI2 {
    @GET("{search}")
    Call<WeatherObj> getWeatherData(@Path("search") String search,
                                    @Query("lat") double lat,
                                    @Query("lon") double lon,
                                    @Query("cnt") int cnt,
                                    @Query("AppID") String appID);
}