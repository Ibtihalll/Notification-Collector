package com.codecherisher.firebasenotification.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.codecherisher.firebasenotification.model.Notification;

import java.util.List;

@Dao
public interface NotificationDao {

    @Insert
    void insert(Notification notification);

    @Query("SELECT * FROM notification_table ORDER BY id Desc")
    LiveData<List<Notification>> getAllNotifications();
}
