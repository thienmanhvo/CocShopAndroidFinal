package com.example.cocshopandroid.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocshopandroid.KProgressHUDManager;
import com.example.cocshopandroid.MainActivity;
import com.example.cocshopandroid.R;
import com.example.cocshopandroid.dao.CartDAO;
import com.example.cocshopandroid.model.LocationResult;
import com.example.cocshopandroid.model.Locations;
import com.example.cocshopandroid.model.Order;
import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.presenter.LocationPresenter;
import com.example.cocshopandroid.presenter.PaymentPresenter;
import com.example.cocshopandroid.repositories.FCartRepository;
import com.example.cocshopandroid.repositories.FCartRepositoryImp;
import com.example.cocshopandroid.view.LocationView;
import com.example.cocshopandroid.view.PaymentView;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

public class ConfirmPaymenActivity extends AppCompatActivity implements LocationView, PaymentView {
    LocationPresenter mLocationPresenter;
    PaymentPresenter paymentPresenter;
    FCartRepository fCartRepository;
    static String arr[] = new String[2];
    List<Product> products;
    List<Locations> listLocation;
    List<Order> listCart;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_paymen);
        fCartRepository = new FCartRepositoryImp(this);
        mLocationPresenter = new LocationPresenter(this);
        paymentPresenter = new PaymentPresenter(this);
        mLocationPresenter.getLocation();
        CartDAO dao = new CartDAO(this);
        products = dao.readAll();
        listCart = dao.readCart();
//        selection = (TextView) findViewById(R.id.selection);

        //Lấy đối tượng Spinner ra

        //Gán Data source (arr) vào Adapter


    }

    @Override
    public void getLocationSuccess(LocationResult location) {
        listLocation = location.getResults();
        for (int i = 0; i < listLocation.size(); i++) {
            arr[i] = listLocation.get(i).getLocationName();

        }
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());

    }

    @Override
    public void getLocationFailed(String s) {

    }

    @Override
    public void getCartSuccess(String message) {
        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
        final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
                Intent intent = new Intent(ConfirmPaymenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1000);// = 1 seconds

    }

    @Override
    public void getCartFail(String s) {

    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
//            selection.setText(arr[arg2]);
            for (int i = 0; i < listLocation.size(); i++) {
                if (listLocation.get(i).getLocationName().equalsIgnoreCase(arr[arg2])) {
                    id = listLocation.get(i).getId();
                }
            }
        }


        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
//            selection.setText("");
        }
    }

    public void onPaymentCart(View view) {
        paymentPresenter.getCart(id, null, listCart);

    }
}
