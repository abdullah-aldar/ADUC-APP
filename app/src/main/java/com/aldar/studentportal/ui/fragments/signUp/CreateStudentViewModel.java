package com.aldar.studentportal.ui.fragments.signUp;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStudentViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();
    public MutableLiveData<String> fcmToken = new MutableLiveData<>();
    public MutableLiveData<String> errorUsername = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> createUserLiveData = new MutableLiveData<>();

    public CreateStudentViewModel(@NonNull Application application) {
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
        Call<CommonApiResponse> allUsers = services.createUser(userName.getValue(),userPassword.getValue(),fcmToken.getValue());
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(Call<CommonApiResponse> call, Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplication().getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    Toast.makeText(getApplication().getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    CommonApiResponse model = new CommonApiResponse();
                    model.setMessage(response.body().getMessage());
                    model.setStatus(response.body().getStatus());
                    model.setSuccess(response.body().getSuccess());

                    createUserLiveData.setValue(model);
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                progressBar.setValue(8);
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<CommonApiResponse> createUserLiveData(){
        return createUserLiveData;
    }


    private boolean validate() {
        boolean valid = true;

        if (userName.getValue() == null || userName.getValue().isEmpty()) {
            valid = false;
            errorUsername.setValue("Please enter your username");
        }

        if (userPassword.getValue() == null || userPassword.getValue().isEmpty()) {
            valid = false;
            errorPassword.setValue("Please set your password");
        }

        return valid;
    }
}
