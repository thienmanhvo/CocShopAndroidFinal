package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FCartService {
    @GET(ConfigApi.Api.LOCATION)
    Call<ResponseBody> getLocation(@Header("Authorization") String token);

    @POST(ConfigApi.Api.CART)
    Call<ResponseBody> cart(@Header("Authorization") String token, @Body RequestBody body);
}
