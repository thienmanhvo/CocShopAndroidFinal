package com.example.cocshopandroid.repositories;

import android.content.Context;

import com.example.cocshopandroid.model.LocationResult;
import com.example.cocshopandroid.model.Order;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.utils.ClientApi;
import com.example.cocshopandroid.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FCartRepositoryImp implements FCartRepository {
    static String token;

    public FCartRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN", null);
    }

    public FCartRepositoryImp() {
    }

    @Override
    public void getLocation(final CallBackData<LocationResult> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fCartService().getLocation(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<LocationResult>>() {
                        }.getType();
                        ResponseData<LocationResult> responseData = new Gson().fromJson(result, type);
                        LocationResult list = responseData.getData();
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
    public void getCart(String locationId, String paymentId, List<Order> products, final CallBackData<String> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("locationId", locationId);
            jsonObject.put("paymentId", paymentId);
            for (Order item : products) {
                jsonObject1.put("id", item.getId());
                jsonObject1.put("quantity", item.getQuantity());
                jsonObject1.put("price", item.getPrice());
                jsonArray.put(jsonObject1);
            }
            jsonObject.put("products", jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().cart(token, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        data.onSuccess("thành công");
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
