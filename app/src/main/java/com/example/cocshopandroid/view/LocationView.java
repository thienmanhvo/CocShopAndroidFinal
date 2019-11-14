package com.example.cocshopandroid.view;


import com.example.cocshopandroid.model.LocationResult;

public interface LocationView {
    void getLocationSuccess(LocationResult location);

    void getLocationFailed(String s);
}
