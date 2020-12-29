package com.tcs.assignmentone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword, editTextMessage;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), getString(R.string.on_create),Toast.LENGTH_SHORT).show();
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        editTextMessage.setText(getString(R.string.default_message));
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setEnabled(true);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = getString(R.string.username);
                String password = getString(R.string.password);
                if(editTextUsername.getText().toString().equals(username)){
                    if(editTextPassword.getText().toString().equals(password)){
                        buttonLogin.setEnabled(false);
                        editTextMessage.setText(getString(R.string.successful_message));
                    }
                    else{
                        editTextMessage.setText(getString(R.string.password_invalid_message));
                    }
                }
                else{
                    editTextMessage.setText(getString(R.string.username_invalid_message));
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),getString(R.string.on_start),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),getString(R.string.on_resume),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),getString(R.string.on_pause),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),getString(R.string.on_stop),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),getString(R.string.on_restart),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),getString(R.string.on_destroy),Toast.LENGTH_SHORT).show();
    }
    
}