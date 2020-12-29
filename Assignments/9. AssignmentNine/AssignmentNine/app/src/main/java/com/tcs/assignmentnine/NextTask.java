package com.tcs.assignmentnine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class NextTask extends AppCompatActivity {
    TextView textViewMessage;
    Button buttonAndroid;
    Button buttoniOS;
    Button buttonWindows;
    private String languageToLoad;
    private Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_task);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        buttonAndroid = (Button) findViewById(R.id.buttonAndroid);
        buttoniOS = (Button) findViewById(R.id.buttoniOS);
        buttonWindows = (Button) findViewById(R.id.buttonWindows);
        buttonAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMessage.setText(getString(R.string.android));
            }
        });
        buttoniOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMessage.setText(getString(R.string.ios));
            }
        });
        buttonWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMessage.setText(getString(R.string.windows));
            }
        });
    }
}