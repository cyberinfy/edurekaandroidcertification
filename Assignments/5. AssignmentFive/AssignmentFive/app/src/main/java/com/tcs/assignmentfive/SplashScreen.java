package com.tcs.assignmentfive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;


public class SplashScreen extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private ConstraintLayout splash;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash = (ConstraintLayout) findViewById(R.id.splash);
        textView = (TextView) findViewById(R.id.textView);
        getDataFromSP();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    protected void getDataFromSP() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefList = sharedPreferences.getString("PREF_LIST", "no selection");
        String prefUsername = sharedPreferences.getString("PREF_USERNAME", "User");
        if(prefList!="no selection"){
            splash.setBackgroundColor(Color.parseColor(prefList));
        }
        textView.setText(textView.getText().toString()+" "+prefUsername);
    }
}