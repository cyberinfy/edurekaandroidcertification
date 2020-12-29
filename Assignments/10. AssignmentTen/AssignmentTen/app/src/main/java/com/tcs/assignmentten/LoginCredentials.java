package com.tcs.assignmentten;

import android.text.TextUtils;

public class LoginCredentials {

    private String username, password;

    private final String validUsername = "edureka", validPassword = "edureka123";

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(username)) {
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            return false;
        }

        if(!validUsername.equals(this.getUsername()) || !validPassword.equals(this.password)){
            return false;
        }

        return true;
    }
}