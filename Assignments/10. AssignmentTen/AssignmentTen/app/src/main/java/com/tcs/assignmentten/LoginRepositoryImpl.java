package com.tcs.assignmentten;

public class LoginRepositoryImpl implements LoginRepository{
    @Override
    public void login(LoginCredentials credentials, LoginListener loginListener) {
        if (credentials.isValid()) {
            loginListener.onLogin("Login Successful",R.color.green);
        } else {
            loginListener.onLogin("Login Failed",R.color.red);
        }

    }
}
