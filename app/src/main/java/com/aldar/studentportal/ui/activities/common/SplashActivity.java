package com.aldar.studentportal.ui.activities.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.activities.common.faq.FaqActivity;
import com.aldar.studentportal.ui.commonScreens.navigations.home.HomeFragment;
import com.aldar.studentportal.worker.MyWorker;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new InnerClass(SplashActivity.this).loadNext();

    }

    private static class InnerClass {
        private final WeakReference<Activity> weakReference;

        private InnerClass(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        private void loadNext() {
            Activity context = weakReference.get();
            if (context != null) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        context.startActivity(new Intent(context, NavigationActivity.class));
                        context.finish();
                    }
                }, 2000);

            }
        }
    }
}
