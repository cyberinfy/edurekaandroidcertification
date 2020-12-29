package com.tcs.projecttwomoviesapp.interfaces;

import com.tcs.projecttwomoviesapp.datamodel.Dashboard;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DashboardJSONPlaceHolderAPI {
        @POST("{search}")
        Call<Dashboard> getMovies(
                @Path("search") String search,
                @Query("api_key") String api_key,
                @Query("language") String language,
                @Query("page") String page);
}
