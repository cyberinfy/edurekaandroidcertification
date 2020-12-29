package com.tcs.assignmentten;


public interface Contract {

    interface LoginView {

        void onLogin(String message, int colorID);

    }

    interface Presenter {

        void login(LoginCredentials loginCredentials);

    }

}
