package com.aldar.studentportal.ui.activities.common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.aldar.studentportal.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new InnerClass(SplashActivity.this).loadNext();
    }

    private static class InnerClass  {
        private final WeakReference<Activity> weakReference;

        public InnerClass(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        private void loadNext() {
            Activity context = weakReference.get();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (context != null) {
                        context.startActivity(new Intent(weakReference.get(), NavigationActivity.class));
                        context.finish();
                    }
                }
            }, 2000);
        }
    }

}
