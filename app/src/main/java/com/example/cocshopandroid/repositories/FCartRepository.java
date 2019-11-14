package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.model.LocationResult;
import com.example.cocshopandroid.model.Order;
import com.example.cocshopandroid.utils.CallBackData;

import java.util.List;

public interface FCartRepository {
    void getLocation(CallBackData<LocationResult> data);

    void getCart(String locationId, String paymentId, List<Order> product, CallBackData<String> data);
}
