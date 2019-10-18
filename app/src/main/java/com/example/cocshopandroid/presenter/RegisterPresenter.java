package com.example.cocshopandroid.presenter;

import com.example.cocshopandroid.repositories.FAccountRepository;
import com.example.cocshopandroid.repositories.FAccountRepositoryImp;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.view.RegisterView;

public class RegisterPresenter {
    private RegisterView registerView;
    private FAccountRepository repon;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        repon = new FAccountRepositoryImp();
    }

    public void register(String username, String password, String email, String fullname) {
        repon.register(username, password, email, fullname, new CallBackData<String>() {
            @Override
            public void onSuccess(String token) {
                registerView.registerSuccess();
            }

            @Override
            public void onFail(String message) {
                registerView.registerFailed(message);
            }
        });
    }
}
