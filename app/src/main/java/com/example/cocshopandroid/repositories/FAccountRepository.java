package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.model.Token;
import com.example.cocshopandroid.model.User;
import com.example.cocshopandroid.utils.CallBackData;

public interface FAccountRepository {
    void login(String username, String password, CallBackData<String> data);

    void register(String username,String password, String email, String fullname, CallBackData<String> data);

    void getProfile(CallBackData<User> data);
}
