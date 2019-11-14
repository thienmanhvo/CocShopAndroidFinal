package com.example.cocshopandroid.utils;

public class ConfigApi {

    public static final String BASE_URL = "https://cocshopwebapi20190925023900.azurewebsites.net/api/";

    public interface Api {
        String LOGIN = "Auth/Login";
        String REGISTER = "Auth/Register";
        String PRODUCT = "Products";
        String GET_PRODUCT_ID = "Products/{id}";
        String GET_PROFILE = "Auth/Profile";
        String PRODUCT2="Products";
        String SEARCH = "Products";
        String LOCATION="Locations";
        String CART = "Order";
    }
}
