package com.example.cocshopandroid.presenter;

import com.example.cocshopandroid.model.Order;
import com.example.cocshopandroid.repositories.FCartRepository;
import com.example.cocshopandroid.repositories.FCartRepositoryImp;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.view.PaymentView;

import java.util.List;

public class PaymentPresenter {
    private PaymentView paymentView;
    private FCartRepository repon;

    public PaymentPresenter(PaymentView paymentView) {
        this.paymentView = paymentView;
        repon = new FCartRepositoryImp();
    }

    public void getCart(String locationId, String paymentId, List<Order> product) {

        repon.getCart(locationId, paymentId, product, new CallBackData<String>() {
            @Override
            public void onSuccess(String message) {
                paymentView.getCartSuccess(message);
            }

            @Override
            public void onFail(String message) {
                paymentView.getCartFail(message);
            }
        });
    }
}
