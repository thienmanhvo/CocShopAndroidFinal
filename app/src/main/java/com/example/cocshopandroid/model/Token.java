package com.example.cocshopandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable {
    @SerializedName("access_token")
    private  String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
