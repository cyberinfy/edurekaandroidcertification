package com.tcs.projecttwomoviesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tcs.projecttwomoviesapp.R;
import com.tcs.projecttwomoviesapp.datamodel.Dashboard;
import com.tcs.projecttwomoviesapp.datamodel.Movie;
import com.tcs.projecttwomoviesapp.interfaces.DashboardJSONPlaceHolderAPI;
import com.tcs.projecttwomoviesapp.interfaces.MovieJSONPlaceHolderAPI;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment{

    View rootView;
    private final String baseURL = "https://api.themoviedb.org/3/movie/";
    private final String api_key = "55957fcf3ba81b137f8fc01ac5a31fb5";
    private long movieID;
    private Movie movie;

    public MovieFragment() {
    }

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        movieID = getArguments().getLong("movieID");
        Log.d("tag",movieID+"");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieJSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(MovieJSONPlaceHolderAPI.class);
        Call<Movie> call = jsonPlaceHolderAPI.getMovies(movieID, api_key);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity().getApplication(),response.code()+"",Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d("Movie","parsed");
                movie = response.body();
                inflateMovie();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getActivity().getApplication(), getString(R.string.please_check_internet),Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void inflateMovie() {
        ImageView imageViewMovieBackdrop = (ImageView) rootView.findViewById(R.id.imageViewMovieBackdrop);
        TextView textViewMovieName = (TextView) rootView.findViewById(R.id.textViewMovieName);
        TextView textViewMovieTag = (TextView) rootView.findViewById(R.id.textViewMovieTag);
        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);
        TextView textViewRating = (TextView) rootView.findViewById(R.id.textViewRating);
        TextView textViewOverview = (TextView) rootView.findViewById(R.id.textViewOverview);
        TextView textViewGenre = (TextView) rootView.findViewById(R.id.textViewGenre);
        TextView textViewMovieLanguage = (TextView) rootView.findViewById(R.id.textViewMovieLanguage);
        TextView textViewSpokenLanguages = (TextView) rootView.findViewById(R.id.textViewSpokenLanguages);
        TextView textViewProductionCompanies = (TextView) rootView.findViewById(R.id.textViewProductionCompanies);
        TextView textViewProductionCountries = (TextView) rootView.findViewById(R.id.textViewProductionCountries);

        Picasso.get().load(movie.getBackdrop_path()).into(imageViewMovieBackdrop);
        textViewMovieName.setText(movie.getTitle());
        textViewMovieTag.setText(movie.getTagline());
        textViewRating.setText((movie.getVote_average()*10)+"%");
        textViewOverview.setText(movie.getOverview());
        ratingBar.setRating(movie.getVote_average()/2);
        textViewGenre.setText(movie.getMovieGenres());
        textViewMovieLanguage.setText(new Locale(movie.getOriginal_language()).getDisplayLanguage());
        textViewSpokenLanguages.setText(movie.getSpokenLanguages());
        textViewProductionCompanies.setText(movie.getProductionCompanies());
        textViewProductionCountries.setText(movie.getProductionCountries());

    }
}