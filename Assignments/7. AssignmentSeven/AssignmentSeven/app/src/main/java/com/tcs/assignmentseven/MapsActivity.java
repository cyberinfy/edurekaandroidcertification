package com.tcs.assignmentseven;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.tcs.assignmentseven.datamodel.MapData;
import com.tcs.assignmentseven.interfces.JSONPlaceHolderAPI;

import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    Task<Location> task;
    static int locationRequestCode = 1000;
    double latitude, longitude;
    Spinner spinner;
    Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        spinner = (Spinner) findViewById(R.id.spinnerPlace);
        find = (Button) findViewById(R.id.button);
        String[] places = {"atm","bank","hospital","movie_theater","restaurant"};
        String[] names = {"ATM","Bank","Hospital","Movie Theater","Restaurant"};
        spinner.setAdapter(new ArrayAdapter<String>(MapsActivity.this,R.layout.support_simple_spinner_dropdown_item,names));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        getRequiredPermissions();
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = spinner.getSelectedItemPosition();
                String baseURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/";
                String search = "json";
                String location = latitude + "," + longitude;
                String radius = "5000";
                String type = places[i];
                String sensor = "true";
                String key = getResources().getString(R.string.google_map_key);
                Log.d("tag", baseURL+"\n"+ search+"\n"+ location+"\n"+ radius+"\n"+ type+"\n"+ sensor+"\n"+ key);
                getNearByAreas(baseURL, search, location, radius, type, sensor, key);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitude,longitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        mMap.addMarker(markerOptions);
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
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d("tag", latitude + "," + longitude);
                        mapFragment.getMapAsync(MapsActivity.this::onMapReady);
                    }
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

    public void getNearByAreas(String baseURL,String search,String location,String radius,String type,String sensor,String key){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);
        Call<MapData> call = jsonPlaceHolderAPI.getAreas(search, location, radius, type, sensor,key);
        call.enqueue(new Callback<MapData>() {
            @Override
            public void onResponse(Call<MapData> call, Response<MapData> response) {
                if(!response.isSuccessful()){
                    Log.d("restag",""+response.code()+"\n"+response.toString());
                    return;
                }
                Log.d("restag",response.toString());
                String nearByAreas = response.body().toString();
                Toast.makeText(getApplication(),nearByAreas,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MapData> call, Throwable t) {
                Log.d("restag",t.getMessage());
                Toast.makeText(getApplication(),"May be you are not connected to internet or not provided location permission for this app",Toast.LENGTH_LONG).show();
            }
        });
    }
}