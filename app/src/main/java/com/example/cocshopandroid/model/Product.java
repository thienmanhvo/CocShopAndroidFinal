package com.example.cocshopandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("productName")
    private String productName;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("priceSale")
    private int priceSale;
    @SerializedName("price")
    private int price;
    @SerializedName("description")
    private String description;
    @SerializedName("isNew")
    private boolean isNew;
    @SerializedName("isBest")
    private boolean isBest;
    @SerializedName("cateId")
    private String cateId;
    @SerializedName("imagePath")
    private String imagePath;

    public Product() {
    }

    public Product(String id, String productName, int quantity, int price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String id, String productName, int quantity, int priceSale, int price, String description, boolean isNew, boolean isBest, String cateId, String imagePath) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.priceSale = priceSale;
        this.price = price;
        this.description = description;
        this.isNew = isNew;
        this.isBest = isBest;
        this.cateId = cateId;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isBest() {
        return isBest;
    }

    public void setBest(boolean best) {
        isBest = best;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
