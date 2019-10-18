package com.example.cocshopandroid.presenter;

import com.example.cocshopandroid.model.Token;
import com.example.cocshopandroid.repositories.FAccountRepository;
import com.example.cocshopandroid.repositories.FAccountRepositoryImp;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.view.LoginView;

public class LoginPresenter {
    private LoginView loginView;
    private FAccountRepository repon;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        repon = new FAccountRepositoryImp();
    }

    public void login(String username, String password) {

        repon.login(username, password, new CallBackData<String>() {
            @Override
            public void onSuccess(String token) {
                loginView.loginSuccess(token);
            }

            @Override
            public void onFail(String message) {
                loginView.loginFailed(message);
            }
        });
    }
}
