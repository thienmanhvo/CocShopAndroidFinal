package com.example.cocshopandroid.repositories;

import com.example.cocshopandroid.utils.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FProductService {
    @GET(ConfigApi.Api.PRODUCT)
    Call<ResponseBody> getProduct(@Header("Authorization") String token);

    @GET(ConfigApi.Api.GET_PRODUCT_ID)
    Call<ResponseBody> getProductById(@Header("Authorization") String token, @Path("id") String id);

    @GET(ConfigApi.Api.PRODUCT2)
    Call<ResponseBody> getProduct2(@Header("Authorization") String token, @Query("PageSize") int pageSize, @Query("PageIndex") int pageIndex);

    @GET(ConfigApi.Api.SEARCH)
    Call<ResponseBody> search(@Query("Filter[productName(e)]") String id ,@Header("Authorization") String token );
}

