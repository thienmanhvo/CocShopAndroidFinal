package com.example.cocshopandroid.presenter;

import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.repositories.FProductRepository;
import com.example.cocshopandroid.repositories.FProductRepositoryImp;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.view.ProductView;


public class ProductPresenter {
    private ProductView productView;
    private FProductRepository repon;

    public ProductPresenter(ProductView productView) {
        this.productView = productView;
        repon = new FProductRepositoryImp();
    }

    public void getProductById(String id) {
        repon.getProductById(id, new CallBackData<Product>() {
            @Override
            public void onSuccess(Product product) {
                productView.getSuccess(product);
            }

            @Override
            public void onFail(String message) {
                productView.getFailed(message);
            }
        });
    }
}
