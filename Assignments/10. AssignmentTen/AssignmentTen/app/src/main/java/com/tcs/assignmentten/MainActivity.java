package com.tcs.assignmentten;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements Contract.LoginView{
    private LoginPresenter presenter;
    ConstraintLayout constraintLayout;
    TextView textViewResult;
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(new LoginRepositoryImpl(), this);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        editTextUsername = (EditText) findViewById(R.id.editTextTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        buttonLogin = (Button) findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                LoginCredentials credentials = new LoginCredentials(username,password);
                presenter.login(credentials);
            }
        });
    }

    @Override
    public void onLogin(String message, int colorID) {
        textViewResult.setText(message);
        constraintLayout.setBackgroundResource(colorID);
    }
}