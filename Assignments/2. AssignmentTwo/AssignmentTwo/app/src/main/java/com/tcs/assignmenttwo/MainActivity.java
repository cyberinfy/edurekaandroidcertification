package com.tcs.assignmenttwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 0;
    ConstraintLayout constraintLayout;
    RadioButton radioButtonRed, radioButtonGreen, radioButtonBlue;
    Button buttonGoogle, buttonYahoo;
    WebView webView;
    SmsReceiver smsReceiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        radioButtonRed = (RadioButton) findViewById(R.id.radioButtonRed);
        radioButtonGreen = (RadioButton) findViewById(R.id.radioButtonGreen);
        radioButtonBlue = (RadioButton) findViewById(R.id.radioButtonBlue);
        buttonGoogle = (Button) findViewById(R.id.buttonGoogle);
        buttonYahoo = (Button) findViewById(R.id.buttonYahoo);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        radioButtonRed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                radioButtonGreen.setChecked(false);
                radioButtonBlue.setChecked(false);
                constraintLayout.setBackgroundResource(R.color.red);
            }
        });
        radioButtonGreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                radioButtonRed.setChecked(false);
                radioButtonBlue.setChecked(false);
                constraintLayout.setBackgroundResource(R.color.green);
            }
        });
        radioButtonBlue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                radioButtonGreen.setChecked(false);
                radioButtonRed.setChecked(false);
                constraintLayout.setBackgroundResource(R.color.blue);
            }
        });
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(getString(R.string.google_url));
            }
        });
        buttonYahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(getString(R.string.yahoo_url));
            }
        });

        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.RECEIVE_SMS};
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
        if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED) {
            String[] permissions = {Manifest.permission.READ_SMS};
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }

        smsReceiver = new SmsReceiver();
        intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        intentFilter.setPriority(1000);
        registerReceiver(smsReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }
}