package com.tcs.assignmentfive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        MainPreferenceFragment preferencesFragment = new MainPreferenceFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.preferences,preferencesFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        Intent intentSetPref = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intentSetPref, 0);
    }
}