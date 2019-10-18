package com.example.cocshopandroid.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cocshopandroid.R;
import com.example.cocshopandroid.dao.CartDAO;
import com.example.cocshopandroid.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    CartAdapter cartAdapter;
    String id;
    TextView txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        txtTotal = findViewById(R.id.tvTotal);

        //Goi sqlite lay ra List<Product>
        List<Product> products;
        CartDAO dao = new CartDAO(this);
        products = dao.readAll();
        cartAdapter = new CartAdapter(products, this);
        RecyclerView recyclerViewCart = findViewById(R.id.listCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewCart.setAdapter(cartAdapter);
    }

    public void onRemoveItem(View view) {
        int total = 0;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        TextView txtQuantity = viewGroup.findViewById(R.id.txtQuantity);
        TextView txtTitle = viewGroup.findViewById(R.id.txtTitle);
        String title = txtTitle.getText().toString().trim();
        int quantity = Integer.parseInt(txtQuantity.getText().toString());
        if (quantity > 1) {
            quantity--;
        }

        List<Product> products;
        CartDAO dao = new CartDAO(this);
        products = dao.readAll();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(title)) {
                id = products.get(i).getId();
                break;
            }
        }

        Product product = dao.readById(id);
        product.setQuantity(quantity);
        dao.update(product);
        products = dao.readAll();
        for (Product productFor : products
        ) {
            total = productFor.getPrice() * productFor.getQuantity() + total;
        }
        txtQuantity.setText(Integer.toString(quantity));
        txtTotal.setText(total+" d");
    }
    public void onAddingItem(View view) {
        int total = 0;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        TextView txtQuantity = viewGroup.findViewById(R.id.txtQuantity);
        TextView txtTitle = viewGroup.findViewById(R.id.txtTitle);
        String title = txtTitle.getText().toString().trim();
        int quantity = Integer.parseInt(txtQuantity.getText().toString());
        if (quantity < 10) {
            quantity++;
        }

        List<Product> products;
        CartDAO dao = new CartDAO(this);
        products = dao.readAll();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(title)) {
                id = products.get(i).getId();
                break;
            }
        }

        Product product = dao.readById(id);
        product.setQuantity(quantity);
        dao.update(product);
        products = dao.readAll();
        for (Product productFor : products
        ) {
            total = productFor.getPrice() * productFor.getQuantity() + total;
        }
        txtQuantity.setText(Integer.toString(quantity));
        txtTotal.setText(total+" d");
    }
    public void onDeleteBook(View view){
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        TextView txtTitle = viewGroup.findViewById(R.id.txtTitle);
        String title = txtTitle.getText().toString().trim();

        List<Product> products;
        CartDAO dao = new CartDAO(this);
        products = dao.readAll();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(title)) {
                id = products.get(i).getId();
                break;
            }
        }
        dao.delete(id);
        products = dao.readAll();
        cartAdapter = new CartAdapter(products, this);
        RecyclerView recyclerViewCart = findViewById(R.id.listCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewCart.setAdapter(cartAdapter);
        if (products.size() == 0) {
            txtTotal.setText(0+" d");
        }
    }
}
