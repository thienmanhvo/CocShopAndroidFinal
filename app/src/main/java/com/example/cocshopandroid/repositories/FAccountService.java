package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FAccountService {
    @POST(ConfigApi.Api.LOGIN)
    Call<ResponseBody> login(@Body RequestBody body);

    @POST(ConfigApi.Api.REGISTER)
    Call<ResponseBody> register(@Body RequestBody body);

    @GET(ConfigApi.Api.GET_PROFILE)
    Call<ResponseBody> getProfile(@Header("Authorization") String token);
}
