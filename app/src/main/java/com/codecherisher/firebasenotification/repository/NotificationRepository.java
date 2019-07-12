package com.codecherisher.firebasenotification.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.codecherisher.firebasenotification.dao.NotificationDao;
import com.codecherisher.firebasenotification.database.NotificationDatabase;
import com.codecherisher.firebasenotification.model.Notification;

import java.util.List;

public class NotificationRepository {

    private NotificationDao notificationDao;
    private LiveData<List<Notification>> allNotifications;

    public NotificationRepository(Application application) {
        NotificationDatabase database = NotificationDatabase.getInstance(application);
        notificationDao = database.notificationDao();
        allNotifications = notificationDao.getAllNotifications();
    }

    public void insert(Notification notification) {
        new InsertNotificationAsyncTask(notificationDao).execute(notification);
    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    private static class InsertNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {

        NotificationDao notificationDao;

        private InsertNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDao.insert(notifications[0]);
            return null;
        }
    }
}
