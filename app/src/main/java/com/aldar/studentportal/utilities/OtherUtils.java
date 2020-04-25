package com.aldar.studentportal.utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class OtherUtils {

    public static void googleClassRoom(Context context) {
        try {
            Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage("com.google.android.apps.classroom");

            if (intent != null) {
                context.startActivity(intent);
            } else {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.classroom"));
                context.startActivity(goToMarket);
            }

        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
