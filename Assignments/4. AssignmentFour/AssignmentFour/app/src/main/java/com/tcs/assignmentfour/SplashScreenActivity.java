package com.tcs.assignmentfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tcs.assignmentfour.customview.PercentageInCircleView;
import com.tcs.assignmentfour.datamodel.PictureDataModel;
import com.tcs.assignmentfour.interfaces.JSONPlaceHolderAPI;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {
    final long period = 100;
    static List<PictureDataModel> pictures;
    Toast toast;
    Timer timer;
    final String baseURL = "https://raw.githubusercontent.com/", searchPath="cyberinfy/pictures/master/pictures.json";
    PercentageInCircleView percentageInCircleView;
    TextView splashMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashMessage = (TextView) findViewById(R.id.splashMessage);
        percentageInCircleView = (PercentageInCircleView) findViewById(R.id.percentageInCircle);
        timer = new Timer();
        createTimer();
    }

    private void createTimer() {
        synchronized (this){
            //Run some background task if needed
            getPictures();
            try {
                wait(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timer.schedule(new TimerTask() {
            int i=0;
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            percentageInCircleView.setText(String.valueOf(i));
                        }
                    });
                    i += 4;
                }
            }
        }, 0, period);
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    private void startMainActivity() {
        percentageInCircleView.setText(String.valueOf(period));
        timer.cancel();
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        // close this activity
        finish();
    }

    public void getPictures(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);
        Call<List<PictureDataModel>> call = jsonPlaceHolderAPI.getPictures(searchPath);
        call.enqueue(new Callback<List<PictureDataModel>>() {
            @Override
            public void onResponse(Call<List<PictureDataModel>> call, Response<List<PictureDataModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplication(),response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d("Splash","parsed");
                pictures = response.body();
                startMainActivity();
            }

            @Override
            public void onFailure(Call<List<PictureDataModel>> call, Throwable t) {
                timer.cancel();
                percentageInCircleView.setText("0");
                for(;;){
                    if(isConnected()){
                        timer = new Timer();
                        createTimer();
                        break;
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}