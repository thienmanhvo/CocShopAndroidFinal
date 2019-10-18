package com.example.cocshopandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cocshopandroid.NotiDTO;

import java.util.ArrayList;
import java.util.List;

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
        sqlQuery = "CREATE TABLE \"noti_table\" (\n" +
                "\t\"notiID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"notiTitle\"\tINTEGER NOT NULL,\n" +
                "\t\"notiBody\"\tINTEGER NOT NULL\n" +
                ")";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE_NAME);
        onCreate(db);
    }

    public boolean inputNoti(String notiTitle, String notiBody) {
        ContentValues values = new ContentValues();
        values.put("notiTitle", notiTitle);
        values.put("notiBody", notiBody);
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = db.insert("noti_table", null, values) > 0;
        db.close();
        return result;
    }

    public List<NotiDTO> getListNoti() {
        List<NotiDTO> list = new ArrayList<>();
        String query = "SELECT notiTitle, notiBody FROM noti_table";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                NotiDTO dto = new NotiDTO();
                dto.setNotiTitle(cursor.getString(0));
                dto.setNotiBody(cursor.getString(1));
                list.add(dto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
