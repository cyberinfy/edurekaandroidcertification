package com.tcs.projecttwomoviesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tcs.projecttwomoviesapp.R;
import com.tcs.projecttwomoviesapp.datamodel.Dashboard;
import com.tcs.projecttwomoviesapp.interfaces.DashboardJSONPlaceHolderAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashFragment extends Fragment {
    View rootView;
    private final String baseURL = "https://api.themoviedb.org/3/movie/";
    private final String searchPath = "now_playing";
    private final String page = "undefined";
    private final String language = "en-US";
    private final String api_key = "55957fcf3ba81b137f8fc01ac5a31fb5";
    static Dashboard movies;

    public SplashFragment() {
    }

    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DashboardJSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(DashboardJSONPlaceHolderAPI.class);
        Call<Dashboard> call = jsonPlaceHolderAPI.getMovies(searchPath, api_key, language, page);
        call.enqueue(new Callback<Dashboard>() {
            @Override
            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity().getApplication(),response.code()+"",Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d("Splash","parsed");
                movies = response.body();
                inflateDashboard();
            }

            @Override
            public void onFailure(Call<Dashboard> call, Throwable t) {
                Toast.makeText(getActivity().getApplication(), getString(R.string.please_check_internet),Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void inflateDashboard() {
        Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_dashboardFragment);
    }
}