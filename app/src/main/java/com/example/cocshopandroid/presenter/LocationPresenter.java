package com.example.cocshopandroid.presenter;

import com.example.cocshopandroid.model.LocationResult;
import com.example.cocshopandroid.repositories.FCartRepository;
import com.example.cocshopandroid.repositories.FCartRepositoryImp;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.view.LocationView;

public class LocationPresenter {
    private LocationView locationView;
    private FCartRepository repon;

    public LocationPresenter(LocationView locationView) {
        this.locationView = locationView;
        repon = new FCartRepositoryImp();
    }

    public void getLocation() {
        repon.getLocation(new CallBackData<LocationResult>() {
            @Override
            public void onSuccess(LocationResult locationResult) {
                locationView.getLocationSuccess(locationResult);
            }

            @Override
            public void onFail(String message) {
                locationView.getLocationFailed(message);
            }
        });
    }
}
