package com.codecherisher.firebasenotification.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codecherisher.firebasenotification.model.Notification;
import com.codecherisher.firebasenotification.repository.NotificationRepository;

import java.util.List;


public class NotificationViewModel extends AndroidViewModel {

    private NotificationRepository notificationRepository;
    private LiveData<List<Notification>> allNotifications;


    public NotificationViewModel(@NonNull Application application) {
        super(application);
        notificationRepository = new NotificationRepository(application);
        allNotifications = notificationRepository.getAllNotifications();
    }

    public void insert(Notification notification) {
        notificationRepository.insert(notification);
    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }
}
