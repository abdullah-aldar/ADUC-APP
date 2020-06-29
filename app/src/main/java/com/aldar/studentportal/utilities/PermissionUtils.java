package com.aldar.studentportal.utilities;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;

public class PermissionUtils {
    public static void checkPermision(Activity context) {
        ActivityCompat.requestPermissions(context,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }
}
