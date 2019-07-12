package com.codecherisher.firebasenotification;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codecherisher.firebasenotification.Adapter.NotificationAdapter;
import com.codecherisher.firebasenotification.model.Notification;
import com.codecherisher.firebasenotification.viewModel.NotificationViewModel;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private NotificationViewModel notificationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //token
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token :" + token);

        //RecycleView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setHasFixedSize(true);
        final NotificationAdapter adapter = new NotificationAdapter();
        recyclerView.setAdapter(adapter);

        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        notificationViewModel.getAllNotifications().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                adapter.setNotifications(notifications);
            }
        });

        //retrieve data and store it in the database
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("notification_title");
            String body = extras.getString("notification_message");
            Notification notification = new Notification(title, body);
            notificationViewModel.insert(notification);
        }


    }


}

