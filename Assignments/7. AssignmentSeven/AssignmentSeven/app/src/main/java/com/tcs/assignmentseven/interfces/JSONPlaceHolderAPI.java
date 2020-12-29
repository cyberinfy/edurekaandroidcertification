package com.tcs.assignmentseven.interfces;

import com.tcs.assignmentseven.datamodel.MapData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {
    @GET("{search}")
    Call<MapData> getAreas(@Path("search") String search,
                                    @Query("location") String location,
                                    @Query("radius") String radius,
                                    @Query("type") String type,
                                    @Query("sensor") String sensor,
                                    @Query("key") String key);
}