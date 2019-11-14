package com.example.cocshopandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cocshopandroid.model.Order;
import com.example.cocshopandroid.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    DBManager dbManager;

    public CartDAO(Context context) {
        dbManager = new DBManager(context);
    }

    public void create(Product product) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBManager.CART_ID, product.getId());
        values.put(DBManager.CART_NAME, product.getProductName());
        values.put(DBManager.CART_QUANTITY, product.getQuantity());
        values.put(DBManager.CART_PRICE, product.getPrice());
        db.insert(DBManager.CART_TABLE_NAME, null, values);
        db.close();
    }

    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DBManager.CART_TABLE_NAME;
        SQLiteDatabase db = dbManager.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getString(0));
                product.setProductName(cursor.getString(1));
                product.setQuantity(cursor.getInt(2));
                product.setPrice(cursor.getInt(3));
                products.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    public List<Order> readCart() {
        List<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DBManager.CART_TABLE_NAME;
        SQLiteDatabase db = dbManager.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setId(cursor.getString(0));
                order.setQuantity(cursor.getInt(2));
                order.setPrice(cursor.getInt(3));
                orders.add(order);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orders;
    }

    public void delete(String id) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        db.delete(DBManager.CART_TABLE_NAME, DBManager.CART_ID + "=?", new String[]{id});
        db.close();
    }

    public int update(Product product) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBManager.CART_ID, product.getId());
        values.put(DBManager.CART_NAME, product.getProductName());
        values.put(DBManager.CART_QUANTITY, product.getQuantity());
        values.put(DBManager.CART_PRICE, product.getPrice());
        return db.update(DBManager.CART_TABLE_NAME, values, DBManager.CART_ID + "= ? ", new String[]{String.valueOf(product.getId())});
    }

    public Product readById(String id) {
        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.query(DBManager.CART_TABLE_NAME, new String[]{DBManager.CART_ID, DBManager.CART_NAME,
                        DBManager.CART_QUANTITY, DBManager.CART_PRICE}, DBManager.CART_ID + "= ? ", new String[]{id},
                null, null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Product product = new Product(cursor.getString(0), cursor.getString(1), cursor.getInt(2),
                cursor.getInt(3)
        );
        cursor.close();
        db.close();
        return product;
    }
}
