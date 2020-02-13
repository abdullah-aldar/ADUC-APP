package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.aldar.studentportal.R;
import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {
    private final LeakyHandler leakyHandler = new LeakyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((AppCompatActivity) this).getSupportActionBar().hide();


        leakyHandler.postDelayed(leakyRunnable,2500);




    }

    private static class LeakyHandler extends Handler {


        private WeakReference<SplashActivity> weakReference;
        public LeakyHandler(SplashActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = weakReference.get();
            if (activity != null) {
            }
        }
    }

    private final Runnable leakyRunnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
        }
    };

}
