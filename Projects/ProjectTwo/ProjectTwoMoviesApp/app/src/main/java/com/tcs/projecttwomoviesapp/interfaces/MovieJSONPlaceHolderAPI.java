package com.tcs.projecttwomoviesapp.interfaces;

import com.tcs.projecttwomoviesapp.datamodel.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieJSONPlaceHolderAPI {
        @GET("{movieID}")
        Call<Movie> getMovies(
                @Path("movieID") long movieID,
                @Query("api_key") String api_key);
}
