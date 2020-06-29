package com.aldar.studentportal.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils {
    private static final String TAG = "Download Task";
    private Context context;
    private String downloadUrl, downloadFileName = "boucher.pdf";
    private ProgressDialog progressDialog;

    public FileUtils(Context context, String downloadUrl) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        new DownloadingTask().execute();
    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Downloading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }


        @Override
        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            try {
                if (outputFile != null) {
                    Toast.makeText(context, "Downloaded Successfully! Please check ADUC Folder in your device storage", Toast.LENGTH_SHORT).show();
                } else {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                        }

                    }, 3000);
                    Log.e(TAG, "Download Failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                //Change button text if exception occurs

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection
                //If Connection response is not OK then show Logs

                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }

                apkStorage = new File(
                        Environment.getExternalStorageDirectory() + "/"
                                + "ADUC");

                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }
                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File
                //Create New File if not present

                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }
                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location
                InputStream is = c.getInputStream();//Get InputStream for connection
                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();
            } catch (Exception e) {
                //Read exception if something went wrong

                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }
            return null;
        }
    }

    public class CheckForSDCard {
        //Check If SD Card is present or not method
        public boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return true;
        }
    }
}
