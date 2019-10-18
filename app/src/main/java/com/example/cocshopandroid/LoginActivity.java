package com.example.cocshopandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cocshopandroid.presenter.LoginPresenter;
import com.example.cocshopandroid.presenter.RegisterPresenter;
import com.example.cocshopandroid.view.LoginView;
import com.example.cocshopandroid.view.RegisterView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginView, RegisterView {
    private EditText username;
    private EditText password;
    private String accessToken;
    private LoginPresenter mLoginPresenter;
    private Preference preference;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private String idFB = null;
    private String nameFB = null;
    private String emailFB = null;
    private RegisterPresenter mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        mLoginPresenter = new LoginPresenter(this);
        mRegister = new RegisterPresenter(this);
        preference = new Preference();
        String check = preference.getAccessToken(this);
        if (check != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);


        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    idFB = object.getString("id");
                                    nameFB = object.getString("name");
                                    if (object.has("email")) {
                                        emailFB = object.getString("email");
                                    }
                                    Log.v("nameFB", nameFB);
                                    Log.v("idFB", idFB);
                                    Log.v("emailFB", emailFB);
                                    mRegister.register(idFB, "123456", emailFB, nameFB);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });

    }

    public void onClickHomePage(View view) {
        final String user = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "Tài khoản không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else {
            mLoginPresenter.login(user, pass);
        }
    }

    public void onRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void loginSuccess(String token) {
        try {
            JSONObject tokenObj = new JSONObject(token);
            accessToken = tokenObj.getJSONObject("data").getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("accessToken", accessToken);
        preference.setAccessToken(this, "Bearer " + accessToken);
        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
        final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }, 1000);// = 1 seconds

    }

    @Override
    public void loginFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerSuccess() {
        mLoginPresenter.login(idFB, "123456");
    }

    @Override
    public void registerFailed(String s) {
        mLoginPresenter.login(idFB, "123456");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
