package com.example.cocshopandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocshopandroid.dao.CartDAO;
import com.example.cocshopandroid.dao.DBManager;
import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.presenter.ProductPresenter;
import com.example.cocshopandroid.view.ProductView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductDetailActivity extends AppCompatActivity implements ProductView {
    private ProductPresenter productPresent;

    TextView productName1;
    TextView price1;
    TextView productId1;
    TextView quantity1;
    TextView priceSale1;
    TextView description1;
    ImageView imageProduct1;
    String productId;
    private Preference preference;
    static String token;
    static Product productCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        token = preference.getAccessToken(this);
        productName1 = findViewById(R.id.productName);
        price1 = findViewById(R.id.price);
        productId1 = findViewById(R.id.productId);
        quantity1 = findViewById(R.id.quantity);
        priceSale1 = findViewById(R.id.priceSale);
        description1 = findViewById(R.id.description);
        imageProduct1 = findViewById(R.id.imageProduct);
        Intent intent = this.getIntent();
        productId = intent.getStringExtra("productId");

        productPresent = new ProductPresenter(this);
        productPresent.getProductById(productId);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Them product hien tai vao List<Product> trong sqlite
                CartDAO dao = new CartDAO(getBaseContext());
                boolean check = false;
                List<Product> productAll = dao.readAll();
                for (int i = 0; i < productAll.size(); i++) {
                    if (productAll.get(i).getId().contains(productId)) {
                        check = true;
                    }
                }
                if (!check) {
                    productCreate.setQuantity(1);
                    dao.create(productCreate);
                } else {
                    Product productHave = dao.readById(productId);
                    int quantity = productHave.getQuantity();
                    quantity++;
                    productCreate.setQuantity(quantity);
                    dao.update(productCreate);
                }
                Toast.makeText(ProductDetailActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void getSuccess(Product product) {
        productName1.setText(product.getProductName());
        price1.setText(String.valueOf(product.getPrice()) + " đ");
        productId1.setText(product.getId());
        quantity1.setText(String.valueOf(product.getQuantity()));
        priceSale1.setText(String.valueOf(product.getPriceSale()));
        description1.setText(product.getDescription());
        Picasso.get().load(product.getImagePath()).into(imageProduct1);
        productCreate = product;
    }

    @Override
    public void getFailed(String s) {
        Toast.makeText(ProductDetailActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
