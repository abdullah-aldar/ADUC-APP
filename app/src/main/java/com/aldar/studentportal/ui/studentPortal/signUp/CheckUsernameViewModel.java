package com.aldar.studentportal.ui.studentPortal.signUp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.aldar.studentportal.models.registerationModels.RegisterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckUsernameViewModel extends AndroidViewModel implements LifecycleObserver {
    private MutableLiveData<Boolean> check = new MutableLiveData<>();
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<RegisterResponseModel> signUpLiveData = new MutableLiveData<>();


    public CheckUsernameViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick(View view) {
        if(validate()){
            apiCallRegisterUser();
        }
    }

    private void apiCallRegisterUser(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<RegisterResponseModel> allUsers = services.registerUser(username.getValue());
        allUsers.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToat(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    showToat(getApplication().getApplicationContext(),response.body().getMessage());
                    check.setValue(false);
                    RegisterResponseModel model = new RegisterResponseModel();
                    model.setSuccess(response.body().getSuccess());
                    model.setMessage(response.body().getMessage());
                    model.setStatus(response.body().getStatus());
                    model.setOtp(response.body().getOtp());

                    signUpLiveData.setValue(model);
                    SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).setStringValueInEditor("student_username",username.getValue());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToat(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<RegisterResponseModel> getSignUpData(){
        return signUpLiveData;
    }



    private boolean validate() {
        boolean valid = true;

        if (username.getValue() == null || username.getValue().isEmpty()) {
            valid = false;
            errorMessage.setValue("Please enter your username");
        }

        return valid;
    }

    public MutableLiveData<Boolean> getCheck(){
        return check;
    }


    private void showToat(Context context,String message){
        Toast.makeText(context, ""+message,Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy(){
        Log.i("Base","Execute this method when Activity is destroyed");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause(){
        Log.i("Base","Execute this method when Activity is pause");
        signUpLiveData.setValue(null);
    }
}
