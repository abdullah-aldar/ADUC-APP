package com.aldar.studentportal.viewModels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
     MutableLiveData<String> userName = new MutableLiveData<>();
     MutableLiveData<String> password = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}