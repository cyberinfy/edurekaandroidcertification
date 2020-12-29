package com.tcs.assignmentthree;

import androidx.lifecycle.ViewModel;
import android.util.Log;


public class MainActivityViewModel extends ViewModel {
    String message = "";
    String username = "";
    String password = "";

    public String getMessage() {
        Log.d("MainActivityViewModel","getMessage Invoked");
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        Log.d("MainActivityViewModel","getUsername Invoked");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        Log.d("MainActivityViewModel","getPassword Invoked");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
