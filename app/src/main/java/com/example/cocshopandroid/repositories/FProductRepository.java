package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.model.ProductResult;
import com.example.cocshopandroid.utils.CallBackData;


public interface FProductRepository {
    void getProduct(CallBackData<ProductResult> data);

    void getProductById(String id, CallBackData<Product> data);

    void getProduct2(int pageSize, int pageIndex, CallBackData<ProductResult> data);

    void search(String id, CallBackData<ProductResult> data);
}
