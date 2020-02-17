package com.aldar.studentportal.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aldar.studentportal.R;


public class GeneralUtilities {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static Fragment connectFragmentWithoutBack(Context context, Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }


    public static Fragment connectFragmentWithBack(Context context, Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
        return fragment;
    }

    public static SharedPreferences.Editor putBooleanValueInEditor(Context context, String key, boolean value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
        return editor;
    }

    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
        return editor;
    }

    public static SharedPreferences.Editor putIntValueInEditor(Context context, String key, int value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putInt(key, value).apply();
        return editor;
    }

    public static SharedPreferences.Editor removeValueFromEditor(Context context,String key){
        sharedPreferences = getSharedPreferences(context);
        sharedPreferences = getSharedPreferences(context);
        editor.remove(key).apply();
        return editor;
    }



    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("ADUC", 0);
    }

}
