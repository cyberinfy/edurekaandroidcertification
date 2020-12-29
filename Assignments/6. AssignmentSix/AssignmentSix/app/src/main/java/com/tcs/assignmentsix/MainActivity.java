package com.tcs.assignmentsix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tcs.assignmentsix.Model.WeatherObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    EditText editTextCity;
    Button buttonFetch;
    TextView textView;
    String city;
    String weatherObjData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        buttonFetch = (Button) findViewById(R.id.buttonFetch);
        textView = (TextView) findViewById(R.id.textView);
        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherData(editTextCity.getText().toString());
            }
        });
    }

    public void getWeatherData(String city){
        textView.setText("Loading weather data for "+city);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JSONPlaceHolderApi.class);
        Call<WeatherObj> call = jsonPlaceHolderApi.getWeatherData("data/2.5/weather",city,"ebfcac32bda131ed5a160f2757938396");
        call.enqueue(new Callback<WeatherObj>() {
            @Override
            public void onResponse(Call<WeatherObj> call, Response<WeatherObj> response) {
                if(!response.isSuccessful()){
                    Log.d("tag",String.valueOf(response.code()));
                    return;
                }
                Log.d("tag","parsed");
                WeatherObj weatherObj = response.body();
                textView.setText(weatherObj.toString());
            }

            @Override
            public void onFailure(Call<WeatherObj> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Please check your internet",Toast.LENGTH_LONG).show();
            }
        });
    }

    private interface JSONPlaceHolderApi {
        @GET("{search}")
        Call<WeatherObj> getWeatherData(@Path("search") String search, @Query("q") String city, @Query("AppID") String appID);
    }
}