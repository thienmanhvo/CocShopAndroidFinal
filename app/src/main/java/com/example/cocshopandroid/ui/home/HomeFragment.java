package com.example.cocshopandroid.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cocshopandroid.R;
import com.example.cocshopandroid.model.ProductResult;
import com.example.cocshopandroid.repositories.FProductRepositoryImp;
import com.example.cocshopandroid.ui.cart.CartActivity;
import com.example.cocshopandroid.utils.CallBackData;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AutoCompleteTextView txtSearch;
    List<String> listOfBookName = new ArrayList<>();
    ArrayAdapter<String> searchAdapter;
    FProductRepositoryImp repon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        repon = new FProductRepositoryImp(getActivity());
        initView();
        txtSearch = view.findViewById(R.id.txtSearch);
        searchAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, listOfBookName);
        txtSearch.setAdapter(searchAdapter);
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchEnter(txtSearch.getText().toString());
                }
                return false;
            }
        });


        ImageButton imgbCart = view.findViewById(R.id.imgbCart);
        imgbCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

    }
    private void initView() {
        repon.getProduct(new CallBackData<ProductResult>() {
            @Override
            public void onSuccess(ProductResult product) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Sản phẩm", product));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
        repon.getProduct2(10,2,new CallBackData<ProductResult>() {
            @Override
            public void onSuccess(ProductResult product2) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.homeContainer, new CategoryFragment("Sản phẩm bán chạy", product2));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
    private void searchEnter(String search) {
        repon.search(search, new CallBackData<ProductResult>() {
            @Override
            public void onSuccess(ProductResult productResult) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.search_result, new CategoryFragment("Kết quả", productResult));
                transaction.commit();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}