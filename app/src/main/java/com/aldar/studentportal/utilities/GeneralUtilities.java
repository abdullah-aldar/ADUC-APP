package com.aldar.studentportal.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aldar.studentportal.R;


public class GeneralUtilities {

    public static Fragment connectFragmentWithoutBack(Context context, Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }

    public static Fragment connectFragmentWithoutBackWithAnimation(Context context, Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out );
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }


    public static Fragment connectFragmentWithBack(Context context, Fragment fragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
        return fragment;
    }

    public static Fragment connectFragmentWithBackWithAnimation(Context context, Fragment fragment){
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out ).addToBackStack("BACK");
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }

}
