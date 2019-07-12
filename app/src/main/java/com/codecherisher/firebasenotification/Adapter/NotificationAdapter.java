package com.codecherisher.firebasenotification.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codecherisher.firebasenotification.R;
import com.codecherisher.firebasenotification.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    List<Notification> notifications = new ArrayList<>();

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        Notification currentNotification = notifications.get(position);
        holder.textViewNotification.setText(currentNotification.getTextMessage());
        holder.textViewTitle.setText(currentNotification.getTitle());

    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }


    public void add(Notification notification) {
        notifications.add(notification);
        notifyDataSetChanged();
    }

    class NotificationHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewNotification;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewNotification = itemView.findViewById(R.id.notification);
        }
    }
}
