package com.example.cocshopandroid.view;

import com.example.cocshopandroid.model.Product;

public interface ProductView {
    void getSuccess(Product product);
    void getFailed(String s);
}
