package com.aldar.studentportal.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.aldar.studentportal.R;

import com.aldar.studentportal.ui.activities.activtiesForFragments.LoginSignUpActivity;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import com.aldar.studentportal.worker.MyWorker;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            if (true) {
//                // For long-running tasks (10 seconds or more) use WorkManager.
//                String title = remoteMessage.getNotification().getTitle();
//                String message = remoteMessage.getNotification().getBody();
//                scheduleJob(title,message);
//            } else {
//                // Handle message within 10 seconds
//                showNotification("hello","how are you ?");
//            }
//
//        }

            if (remoteMessage.getNotification() != null) {
                String title = remoteMessage.getNotification().getTitle();
                String message = remoteMessage.getNotification().getBody();
                showNotification(title,message);
            }

    }

    private void scheduleJob(String title,String message) {

        Data data = new Data.Builder().putString("title",title).putString("message",message).build();

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setInputData(data)
                .build();
        WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        SharedPreferencesManager.getInstance(getApplicationContext()).setStringValueInEditor("fcm_token",token);
    }

    private void showNotification(String title,String message){

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Default", "Default channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(getApplicationContext(), LoginSignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext()     , 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "Default")
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        manager.notify(0, builder.build());


        int countMessage=SharedPreferencesManager.getInstance(getApplicationContext()).getIntValue("notification_count");
        countMessage++;
        SharedPreferencesManager.getInstance(getApplicationContext()).setIntValueInEditor("notification_count",countMessage);

    }

}


