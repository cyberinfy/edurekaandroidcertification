package com.tcs.assignmentthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword, editTextMessage;
    Button buttonLogin;
    NotificationChannel notificationChannel;
    NotificationManagerCompat notificationManagerCompat;
    NotificationCompat.Builder notificationCompatBuilder;
    int notificationId;
    MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationId = 0;
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        editTextMessage.setText(getString(R.string.default_message));
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if(viewModel.getMessage().length()>0) {
            editTextMessage.setText(viewModel.getMessage());
        }
        editTextUsername.setText(viewModel.getUsername());
        editTextPassword.setText(viewModel.getPassword());
        notificationChannel = new NotificationChannel(getString(R.string.app_name), getString(R.string.channel_name), NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription(getString(R.string.channel_description));
        notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.createNotificationChannel(notificationChannel);
        notificationCompatBuilder = new NotificationCompat.Builder(getApplicationContext(),getString(R.string.app_name));
        notificationCompatBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationCompatBuilder.setContentTitle(getString(R.string.app_name));
        notificationCompatBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewModel.setMessage(editTextMessage.getText().toString());
                viewModel.setUsername(editTextUsername.getText().toString());
                viewModel.setPassword(editTextPassword.getText().toString());
                String username = getString(R.string.username);
                String password = getString(R.string.password);
                if(editTextUsername.getText().toString().equals(username) && editTextPassword.getText().toString().equals(password)){
                    notificationCompatBuilder.setContentText(getString(R.string.notification_successful_message));
                    notificationManagerCompat.notify(notificationId++, notificationCompatBuilder.build());
                    editTextMessage.setText(getString(R.string.notification_successful_message));
                    startAfterLoginActivity(v,"Hello "+username);
                }
                else{
                    notificationCompatBuilder.setContentText(getString(R.string.notification_failed_message));
                    notificationManagerCompat.notify(notificationId++, notificationCompatBuilder.build());
                    editTextMessage.setText(getString(R.string.notification_failed_message));
                }
            }
        });
    }
    public void startAfterLoginActivity(View view,String message) {
        Intent intent = new Intent(MainActivity.this, AfterLoginActivity.class);
        intent.putExtra("username", message);
        startActivity(intent);
    }
}