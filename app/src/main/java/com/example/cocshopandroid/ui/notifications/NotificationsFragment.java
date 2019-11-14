package com.example.cocshopandroid.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocshopandroid.NotiDTO;
import com.example.cocshopandroid.R;
import com.example.cocshopandroid.dao.DBManager;

import java.util.List;

public class NotificationsFragment extends Fragment {
    List<NotiDTO> dto;
    NotificationAdapter notiAdapter;
    DBManager db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        db = new DBManager(this.getContext());
        db.inputNoti("Thành Công" , "Đặt hàng success");
        dto = db.getListNoti();
        notiAdapter = new NotificationAdapter(dto, this);
        RecyclerView recyclerViewNoti = view.findViewById(R.id.listNoti);
        recyclerViewNoti.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        recyclerViewNoti.setAdapter(notiAdapter);
        return view;
    }
}