package com.example.cocshopandroid.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocshopandroid.R;
import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.model.ProductResult;
import com.squareup.picasso.Picasso;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder> {
    ProductResult productResult;

    public ProductRecyclerAdapter(ProductResult productResult) {
        this.productResult = productResult;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.MyViewHolder holder, int position) {
        Product product = productResult.getResults().get(position);
        holder.productPrice.setText(convertPriceToFormatString(product.getPrice()));
        holder.productTitle.setText(productResult.getResults().get(position).getProductName() + "");
        holder.productId.setText(productResult.getResults().get(position).getId() + "");
        Picasso.get().load(product.getImagePath()).into(holder.productImg);
    }

    @Override
    public int getItemCount() {
        return productResult.getResults().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImg;
        private TextView productTitle, productPrice, productId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.imageProduct);
            productTitle = itemView.findViewById(R.id.txtProductTitle);
            productPrice = itemView.findViewById(R.id.txtProductPrice);
            productId = itemView.findViewById(R.id.productId);
        }
    }

    private String convertPriceToFormatString(int price) {
        String raw = price + "";
        String result = raw.substring(0, raw.length() - 3) + "." + raw.substring(raw.length() - 3, raw.length()) + "đ";
        return result;
    }
}
