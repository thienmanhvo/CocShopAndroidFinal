package com.example.cocshopandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocshopandroid.dao.DBManager;
import com.example.cocshopandroid.ui.notifications.NotificationAdapter;
import com.example.cocshopandroid.ui.notifications.NotificationsFragment;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("", msg);
                    }
                });
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

    public void onDeleteNoti(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        TextView txtTitle = viewGroup.findViewById(R.id.txtTitleNoti);
        String title = txtTitle.getText().toString().trim();
        List<NotiDTO> dto;
        DBManager db = new DBManager(this);
        dto = db.getListNoti();
        for (int i = 0; i < dto.size(); i++) {
            if (dto.get(i).getNotiTitle().equalsIgnoreCase(title)) {
                db.deleteNoti(dto.get(i).getNotiTitle());
                dto = db.getListNoti();
//                startActivity(new Intent(this, MainActivity.class));
                NotificationAdapter notiAdapter = new NotificationAdapter(dto, this);
                RecyclerView recyclerViewNoti = findViewById(R.id.listNoti);
                recyclerViewNoti.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL, false));
                recyclerViewNoti.setAdapter(notiAdapter);
            }
        }
    }
}
