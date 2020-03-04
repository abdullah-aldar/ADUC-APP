package com.aldar.studentportal.ui.studentPortal.login;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> fcmToken = new MutableLiveData<>();
    public MutableLiveData<String> errorMessageUsername = new MutableLiveData<>();
    public MutableLiveData<String> errorMessagePassword = new MutableLiveData<>();
    private MutableLiveData<LoginResponseModel> loginResponseData = new MutableLiveData<>();
    private boolean valid = false;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick(View view) {
        if (validate()) {
            fcmToken.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("fcm_token"));
            apiCallUpdatePassword();
        }
    }

    private void apiCallUpdatePassword() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<LoginResponseModel> allUsers = services.userLogin(username.getValue(), password.getValue(), fcmToken.getValue());
        allUsers.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplication().getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    LoginResponseModel responseModel = new LoginResponseModel();
                    responseModel.setStatus(response.body().getStatus());
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    loginResponseData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<LoginResponseModel> getLoginResponseData() {
        return loginResponseData;
    }


    private boolean validate() {
        valid = true;

        if (username.getValue() == null || username.getValue().isEmpty()) {
            valid = false;
            errorMessageUsername.setValue("Please enter your username");
        }


        if (password.getValue() == null || password.getValue().isEmpty()) {
            valid = false;
            errorMessagePassword.setValue("Please confirm your password");
        }

        return valid;
    }

}