package com.example.cocshopandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cocshopandroid.presenter.RegisterPresenter;
import com.example.cocshopandroid.view.RegisterView;
import com.kaopiz.kprogresshud.KProgressHUD;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText userName;
    private EditText userEmail;
    private EditText userPass;
    private EditText userPassConfirm;
    private EditText useFullname;
    private RegisterPresenter mRegisterPresenter;
    private RadioButton check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText) findViewById(R.id.username1);
        userPass = (EditText) findViewById(R.id.password1);
        userPassConfirm = (EditText) findViewById(R.id.confirm);
        userEmail = (EditText) findViewById(R.id.email);
        useFullname = (EditText) findViewById(R.id.fullname);
        check = (RadioButton) findViewById(R.id.radioButton);
        mRegisterPresenter = new RegisterPresenter(this);
    }

    public void onRegisterDone(View view) {
        String username = userName.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        String passConfirm = userPassConfirm.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String fullname = useFullname.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(pass).matches()) {
            Toast.makeText(this, "Email không được trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Tài khoản không được để trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(this, "Tên không được để trống", Toast.LENGTH_LONG).show();
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_LONG).show();
        } else if (!passConfirm.equals(pass)) {
            Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_LONG).show();
        } else if (pass.length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
        } else if (!check.isChecked()) {
            Toast.makeText(this, "Bạn phải chấp nhận điều khoản của chúng tôi", Toast.LENGTH_LONG).show();
        } else {
            mRegisterPresenter.register(username, pass, email, fullname);

        }
    }

    public void onExit(View view) {
        finish();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
        final KProgressHUD kProgressHUD = KProgressHUDManager.showProgessBar(this, "Thành công");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kProgressHUD.dismiss();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        }, 1000);// = 1 seconds

    }

    @Override
    public void registerFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
