package com.tcs.assignmentten;

public interface LoginRepository {

    interface LoginListener {
        void onLogin(String message, int colorID);
    }
    void login(LoginCredentials credentials, LoginListener loginListener);
}
