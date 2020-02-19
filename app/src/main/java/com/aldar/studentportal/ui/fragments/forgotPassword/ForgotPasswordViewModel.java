package com.aldar.studentportal.ui.fragments.forgotPassword;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.aldar.studentportal.models.forgotPasswordModels.ForgotPasswordResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends AndroidViewModel implements LifecycleObserver {

    private MutableLiveData<Boolean> check = new MutableLiveData<>();
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> errorMessageUsername = new MutableLiveData<>();
    private MutableLiveData<ForgotPasswordResponseModel> forgotPasswordLiveData = new MutableLiveData<>();

    public ForgotPasswordViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick(View view) {
        if(validate()){
            apiCallForgotPasswordPassword();
        }
    }


    private void apiCallForgotPasswordPassword(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<ForgotPasswordResponseModel> allUsers = services.forgotPassword(username.getValue());
        allUsers.enqueue(new Callback<ForgotPasswordResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ForgotPasswordResponseModel> call, @NotNull Response<ForgotPasswordResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    check.setValue(false);
                    ForgotPasswordResponseModel model = new ForgotPasswordResponseModel();
                    model.setMessage(response.body().getMessage());
                    model.setSuccess(response.body().getSuccess());
                    model.setStatus(response.body().getStatus());
                    model.setOtp(response.body().getOtp());
                    forgotPasswordLiveData.setValue(model);
                    showToast(getApplication().getApplicationContext(),response.body().getMessage());


                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<ForgotPasswordResponseModel> getForgotPasswordLiveData(){
        return forgotPasswordLiveData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    private boolean validate() {
         boolean valid = true;

        if (username.getValue() == null || username.getValue().isEmpty()) {
            valid = false;
            errorMessageUsername.setValue("Please enter your username");
        }


        return valid;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy(){
        Log.i("Base","Execute this method when Activity is destroyed");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause(){
        Log.i("Base","Execute this method when Activity is pause");
        forgotPasswordLiveData.setValue(null);
    }

}
