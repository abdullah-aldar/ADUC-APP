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

}
