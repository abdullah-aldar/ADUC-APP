package com.aldar.studentportal.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static volatile SharedPreferencesManager preferencesManagerInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPreferencesManager() {
        //Prevent form the reflection api.
        if (preferencesManagerInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static SharedPreferencesManager getInstance(Context context) {
        //Double check locking pattern
        if (preferencesManagerInstance == null) { //Check for the first time
            synchronized (SharedPreferencesManager.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (preferencesManagerInstance == null) preferencesManagerInstance = new SharedPreferencesManager();
                if(sharedPreferences == null) sharedPreferences = context.getApplicationContext().getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

            }
        }

        return preferencesManagerInstance;
    }


    public void setStringValueInEditor(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    public String getStringValue(String key){
       return  sharedPreferences.getString(key,"");
    }

    public void setIntValueInEditor(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value).apply();
    }

    public int getIntValue(String key){
        return  sharedPreferences.getInt(key,0);
    }

    public void setBooleaninEditor(String key, boolean value){
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    public boolean getBooleanValue(String key){
        return  sharedPreferences.getBoolean(key,false);
    }
}
