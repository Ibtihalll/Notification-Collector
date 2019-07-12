package com.codecherisher.firebasenotification.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.codecherisher.firebasenotification.dao.NotificationDao;
import com.codecherisher.firebasenotification.model.Notification;

@Database(entities = Notification.class, version = 1, exportSchema = false)
public abstract class NotificationDatabase extends RoomDatabase {

    private static NotificationDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized NotificationDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotificationDatabase.class, "notification_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract NotificationDao notificationDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NotificationDao notificationDao;

        private PopulateDbAsyncTask(NotificationDatabase db) {
            notificationDao = db.notificationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDao.insert(new Notification("title 1", "Notif 1"));
            notificationDao.insert(new Notification("title 2", "Notif 2"));
            notificationDao.insert(new Notification("title 3", "Notif 3"));
            return null;
        }
    }
}
