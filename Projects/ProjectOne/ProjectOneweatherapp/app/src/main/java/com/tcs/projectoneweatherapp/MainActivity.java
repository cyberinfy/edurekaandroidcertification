package com.tcs.projectoneweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.tcs.projectoneweatherapp.adapters.ListViewAdapter;
import com.tcs.projectoneweatherapp.datamodel.ListItem;
import com.tcs.projectoneweatherapp.datamodel.WeatherObj;
import com.tcs.projectoneweatherapp.interfaces.JSONPlaceHolderAPI;
import com.tcs.projectoneweatherapp.interfaces.JSONPlaceHolderAPI2;
import com.tcs.projectoneweatherapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String baseURL = "http://api.openweathermap.org/",
            search = "data/2.5/forecast/daily",
            appID = "ebfcac32bda131ed5a160f2757938396",
            imageURLPre = "http://openweathermap.org/img/w/",
            imageURLPost = ".png";

    private String city, temperature, population, imageURL;
    int currentDatePossition=0;
    private String[] spinnerList;
    private ArrayList<ListItem> listItems;
    private WeatherObj weatherObj;
    private ListViewAdapter listViewAdapter;
    private ArrayAdapter arrayAdapter;

    private MainActivityViewModel viewModel;

    private ListView listViewCurrentWeather;
    private LinearLayoutCompat linearLayoutCurrentWeather, linearLayoutCurrentWeatherChild;
    private JSONPlaceHolderAPI jsonPlaceHolderAPI;
    private Spinner spinner;
    private ImageView imageView;
    private TextView textViewPopulation, textViewTemperature;
    private Task<Location> task;
    FusedLocationProviderClient client;
    static int locationRequestCode = 1000;
    double lat, lon;
    private JSONPlaceHolderAPI2 jsonPlaceHolderAPI2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.search_with_city);
        linearLayoutCurrentWeather = (LinearLayoutCompat) findViewById(R.id.linearLayoutCurrentWeather);
        linearLayoutCurrentWeatherChild = (LinearLayoutCompat) findViewById(R.id.linearLayoutCurrentWeatherChild);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            linearLayoutCurrentWeather.setOrientation(LinearLayoutCompat.VERTICAL);
            linearLayoutCurrentWeatherChild.setOrientation(LinearLayoutCompat.HORIZONTAL);
        } else {
            linearLayoutCurrentWeather.setOrientation(LinearLayoutCompat.HORIZONTAL);
            linearLayoutCurrentWeatherChild.setOrientation(LinearLayoutCompat.VERTICAL);
        }
        textViewPopulation = (TextView) findViewById(R.id.textViewPopulation);
        textViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
        imageView = (ImageView) findViewById(R.id.imageViewWeather);
        listViewCurrentWeather = (ListView) findViewById(R.id.listViewCurrentWeather);
        spinner = (Spinner) findViewById(R.id.spinner);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        city = viewModel.getCity();
        currentDatePossition = viewModel.getSpinnerPossition();
        Log.d("pop",currentDatePossition +"");
        setTitle(city);
        weatherObj = viewModel.getWeatherObj();
        if(weatherObj!=null)
            updateUI();
        else {
            client = LocationServices.getFusedLocationProviderClient(this);
            getRequiredPermissions();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        MenuItem searchMenu = menu.findItem(R.id.action_search);
        SearchView searchView =  (SearchView) searchMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                city = searchView.getQuery().toString();
                searchView.onActionViewCollapsed();
                fetchWeatherData(searchView);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void fetchWeatherData(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);
        Call<WeatherObj> call = jsonPlaceHolderAPI.getWeatherData(search, city, 11, appID);
        call.enqueue(new Callback<WeatherObj>() {
            @Override
            public void onResponse(Call<WeatherObj> call, Response<WeatherObj> response) {
                if(!response.isSuccessful()){
                    Log.d("tag",String.valueOf(response.code()));
                    return;
                }
                Log.d("tag2","parsed");
                weatherObj = response.body();
                viewModel.setWeatherObj(weatherObj);
                currentDatePossition = 0;
                updateUI();
            }
            @Override
            public void onFailure(Call<WeatherObj> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.please_check_internet),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchWeatherData2(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderAPI2 = retrofit.create(JSONPlaceHolderAPI2.class);
        Call<WeatherObj> call = jsonPlaceHolderAPI2.getWeatherData(search, lat, lon, 11, appID);
        call.enqueue(new Callback<WeatherObj>() {
            @Override
            public void onResponse(Call<WeatherObj> call, Response<WeatherObj> response) {
                if(!response.isSuccessful()){
                    Log.d("tag",String.valueOf(response.code()));
                    return;
                }
                Log.d("tag2",response.toString());
                weatherObj = response.body();
                viewModel.setWeatherObj(weatherObj);
                updateUI();
            }
            @Override
            public void onFailure(Call<WeatherObj> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.please_check_internet),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUI() {
        spinnerList = weatherObj.getDatesList();
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,spinnerList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(arrayAdapter);
        Log.d("pop",currentDatePossition +"");
        spinner.setSelection(currentDatePossition, true);
        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner.getSelectedView()).setTextColor(getColor(R.color.white));
                ((TextView) spinner.getSelectedView()).setTextSize(16);
                ((TextView) spinner.getSelectedView()).setTypeface(Typeface.DEFAULT_BOLD);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listItems = (ArrayList<ListItem>) weatherObj.getList().get(position).toListItemArray();
                imageURL = imageURLPre+listItems.get(listItems.size()-1).getValue()+imageURLPost;
                Picasso.get().load(imageURL).into(imageView);
                listItems.remove(listItems.size()-1);
                temperature = listItems.get(8).getValue();
                textViewTemperature.setText(temperature);
                listViewAdapter = new ListViewAdapter(listItems, listViewCurrentWeather.getContext());
                listViewCurrentWeather.setAdapter(listViewAdapter);
                currentDatePossition = position;
                viewModel.setSpinnerPossition(currentDatePossition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listItems = (ArrayList<ListItem>) weatherObj.getList().get(currentDatePossition).toListItemArray();
        imageURL = imageURLPre+listItems.get(listItems.size()-1).getValue()+imageURLPost;
        Picasso.get().load(imageURL).into(imageView);
        listItems.remove(listItems.size()-1);
        temperature = listItems.get(8).getValue();
        population = weatherObj.getPopulation();
        textViewPopulation.setText(population);
        textViewTemperature.setText(temperature);
        listViewAdapter = new ListViewAdapter(listItems, listViewCurrentWeather.getContext());
        listViewCurrentWeather.setAdapter(listViewAdapter);
        setTitle(((WeatherObj.City) weatherObj.getCity()).getName());
        viewModel.setCity(city);
    }

    private void getRequiredPermissions() {
        // check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);

        } else {
            getCurrentLocation();
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        task = client.getLastLocation();
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    fetchWeatherData2(listViewCurrentWeather.getRootView());
                    Log.d("pop",location.getLatitude()+", "+location.getLongitude());
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}