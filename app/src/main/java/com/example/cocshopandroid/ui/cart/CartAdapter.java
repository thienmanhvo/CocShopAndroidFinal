package com.example.cocshopandroid.ui.cart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocshopandroid.R;
import com.example.cocshopandroid.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemCartHolder> {
    private List<Product> productList;
    private Activity activity;

    public CartAdapter(List<Product> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_cart, parent, false);
        return new ItemCartHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCartHolder holder, int position) {
        int total = 0;
        for (Product product : productList
        ) {
            total = product.getPrice() * product.getQuantity() + total;
        }
        TextView tvTotal = activity.findViewById(R.id.tvTotal);
        tvTotal.setText(total+" d");

        Product product = productList.get(position);
        holder.txtTitle.setText(product.getProductName());
        holder.txtPrice.setText(Integer.toString(product.getPrice()));
        holder.txtQuantity.setText(Integer.toString(product.getQuantity()));
       // this.notifyDataSetChanged(); Thay doi
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ItemCartHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPrice , txtQuantity;

        public ItemCartHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}
