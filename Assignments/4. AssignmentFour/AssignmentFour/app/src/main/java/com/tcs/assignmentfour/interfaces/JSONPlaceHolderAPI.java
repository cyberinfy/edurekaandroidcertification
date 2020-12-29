package com.tcs.assignmentfour.interfaces;


import com.tcs.assignmentfour.datamodel.PictureDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderAPI {
    @GET("{search}")
    Call<List<PictureDataModel>> getPictures(@Path("search") String search);
}
