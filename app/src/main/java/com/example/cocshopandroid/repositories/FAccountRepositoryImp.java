package com.example.cocshopandroid.repositories;

import android.content.Context;

import com.example.cocshopandroid.model.Token;
import com.example.cocshopandroid.model.User;
import com.example.cocshopandroid.utils.CallBackData;
import com.example.cocshopandroid.utils.ClientApi;
import com.example.cocshopandroid.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAccountRepositoryImp implements FAccountRepository {
    String token;

    public FAccountRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN", null);
    }

    public FAccountRepositoryImp() {
    }
    @Override
    public void login(String username, String password, final CallBackData<String> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fAccountService().login(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        if (result != null) {
                            data.onSuccess(result);
                        } else {
                            data.onFail("Đăng nhập không thành công");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail("Tài khoản hoặc Mật khẩu không chính xác");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void register(String username, String password, String email, String fullname, final CallBackData<String> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            jsonObject.put("fullname", fullname);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fAccountService().register(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        if (result != null) {
                            data.onSuccess(result);
                        } else {
                            data.onFail("Đăng ký không thành công");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    data.onFail("Tài khoản đã tồn tại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    @Override
    public void getProfile(final CallBackData<User> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fAccountService().getProfile(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<User>>() {
                        }.getType();
                        ResponseData<User> responseData = new Gson().fromJson(result, type);
                        User user = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(user);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Cần đăng nhập lại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
