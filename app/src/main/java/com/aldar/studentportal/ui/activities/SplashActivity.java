package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.aldar.studentportal.R;
import com.google.android.gms.tasks.Task;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
   Handler handler = new Handler();
   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.iv_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
                finish();
            }
        },2000);




    }
    @Override
    protected void onDestroy() {
        imageView = null;
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
