package com.aldar.studentportal.ui.activities.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.worker.MyWorker;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {
   Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            finish();
        },2000);


    }
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
