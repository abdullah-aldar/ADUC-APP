package com.aldar.studentportal.ui.studentPortal.forgotPassword;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.forgotPasswordModels.UpdatePasswordResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel extends AndroidViewModel {
    private boolean valid = false;
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> newPassword = new MutableLiveData<>();
    public MutableLiveData<String> newConfirmPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorMessagePassword = new MutableLiveData<>();
    private MutableLiveData<UpdatePasswordResponseModel> updatePasswordLiveData = new MutableLiveData<>();


    public ChangePasswordViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick(View view) {
        if(validate()){
            apiCallUpdatePassword();
        }
    }


    private void apiCallUpdatePassword(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<UpdatePasswordResponseModel> allUsers = services.updatePassword("BMC1412383","123456");
        allUsers.enqueue(new Callback<UpdatePasswordResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<UpdatePasswordResponseModel> call, @NotNull Response<UpdatePasswordResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    UpdatePasswordResponseModel model = new UpdatePasswordResponseModel();
                    model.setMessage(response.body().getMessage());
                    model.setSuccess(response.body().getSuccess());
                    model.setStatus(response.body().getStatus());
                    updatePasswordLiveData.setValue(model);
                    showToast(getApplication().getApplicationContext(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UpdatePasswordResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<UpdatePasswordResponseModel> getUpdatePasswordLiveData(){
        return updatePasswordLiveData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    private boolean validate() {
        valid = true;


        return valid;
    }

}
