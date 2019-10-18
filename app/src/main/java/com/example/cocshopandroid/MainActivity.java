package com.example.cocshopandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Preference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public void onClickBookDetails(View view) {
        TextView textView = view.findViewById(R.id.productId);
        String productId = textView.getText().toString();
//        FProductRepositoryImp fBookRepository = new FProductRepositoryImp(this);
//        fBookRepository.getProductById(productId);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }
    public void onLogout(View view) {
        preference.removeAccessToken(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        accessToken.setCurrentAccessToken(null);
        startActivity(new Intent(this, LoginActivity.class));
    }

}
