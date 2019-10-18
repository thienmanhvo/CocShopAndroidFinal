package com.example.cocshopandroid.repositories;

import android.content.Context;

import com.example.cocshopandroid.model.Product;
import com.example.cocshopandroid.model.ProductResult;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.utils.ClientApi;
import com.example.cocshopandroid.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FProductRepositoryImp implements FProductRepository {
    static String token;

    public FProductRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN", null);
    }

    public FProductRepositoryImp() {
    }

    @Override
    public void getProduct(final CallBackData<ProductResult> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fProductService().getProduct(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<ProductResult>>() {
                        }.getType();
                        ResponseData<ProductResult> responseData = new Gson().fromJson(result, type);
                        ProductResult list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getProductById(String id, final CallBackData<Product> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fProductService().getProductById(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Product>>() {
                        }.getType();
                        ResponseData<Product> responseData = new Gson().fromJson(result, type);
                        Product product = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(product);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getProduct2(int pageSize, int pageIndex, final CallBackData<ProductResult> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fProductService().getProduct2(token,pageSize,pageIndex);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<ProductResult>>() {
                        }.getType();
                        ResponseData<ProductResult> responseData = new Gson().fromJson(result, type);
                        ProductResult list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void search(String id, final CallBackData<ProductResult> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fProductService().search(id,token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<ProductResult>>() {
                        }.getType();
                        ResponseData<ProductResult> responseData = new Gson().fromJson(result, type);
                        ProductResult list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}