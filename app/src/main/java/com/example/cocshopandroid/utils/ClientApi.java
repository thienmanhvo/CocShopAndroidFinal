package com.example.cocshopandroid.utils;

import com.example.cocshopandroid.repositories.FAccountService;
import com.example.cocshopandroid.repositories.FCartService;
import com.example.cocshopandroid.repositories.FProductService;

public class ClientApi extends BaseApi {
    public FAccountService fAccountService() {
        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
    }
    public FProductService fProductService() {
        return this.getService(FProductService.class, ConfigApi.BASE_URL);
    }
    public FCartService fCartService() {
        return this.getService(FCartService.class, ConfigApi.BASE_URL);
    }
}

