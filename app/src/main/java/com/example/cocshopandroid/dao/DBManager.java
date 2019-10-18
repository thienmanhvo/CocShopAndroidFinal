package com.example.cocshopandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    public static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cartdb";

    public static final String  CART_TABLE_NAME ="tblcart";
    public static final String  CART_ID = "id";
    public static final String  CART_NAME = "productName";
    public static final String  CART_PRICE = "price";
    public static final String CART_QUANTITY = "quantity";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + CART_TABLE_NAME + "("
                +CART_ID + " TEXT PRIMARY KEY, "
                +CART_NAME + " TEXT,"
                +CART_QUANTITY + " TEXT,"
                +CART_PRICE +" TEXT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE_NAME);
        onCreate(db);
    }
}
