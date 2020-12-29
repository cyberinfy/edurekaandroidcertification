package com.tcs.projectoneweatherapp.viewmodel;

import android.widget.ArrayAdapter;

import androidx.lifecycle.ViewModel;

import com.tcs.projectoneweatherapp.datamodel.ListItem;
import com.tcs.projectoneweatherapp.datamodel.WeatherObj;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private String city;
    private int spinnerPossition;
    private WeatherObj weatherObj;

    public WeatherObj getWeatherObj() {
        return weatherObj;
    }

    public void setWeatherObj(WeatherObj weatherObj) {
        this.weatherObj = weatherObj;
    }

    public int getSpinnerPossition() {
        return spinnerPossition;
    }

    public void setSpinnerPossition(int spinnerPossition) {
        this.spinnerPossition = spinnerPossition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
