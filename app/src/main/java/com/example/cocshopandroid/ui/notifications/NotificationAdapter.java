package com.example.cocshopandroid.ui.notifications;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocshopandroid.NotiDTO;
import com.example.cocshopandroid.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ItemNotiHolder> {
    List<NotiDTO> dto;
    private Fragment fragment;
    private Activity activity;

    public NotificationAdapter(List<NotiDTO> notiList, Fragment fragment) {
        this.dto = notiList;
        this.fragment = fragment;
    }

    public NotificationAdapter(List<NotiDTO> notiList, Activity activity) {
        this.dto = notiList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NotificationAdapter.ItemNotiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_noti, parent, false);
        return new NotificationAdapter.ItemNotiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ItemNotiHolder holder, int position) {
        NotiDTO noti = dto.get(position);
        String notifi = noti.getNotiTitle();
        String body = noti.getNotiBody();
        holder.txtTitle.setText(notifi);
        holder.txtNotifi.setText(body);
    }

    @Override
    public int getItemCount() {
        return dto.size();
    }

    public class ItemNotiHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtNotifi;

        public ItemNotiHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitleNoti);
            txtNotifi = itemView.findViewById(R.id.txtNotification);
        }
    }
}
