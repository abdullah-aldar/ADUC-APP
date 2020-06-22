package com.aldar.studentportal.ui.fragments.navigations.userEnquiry;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<String> message = new MutableLiveData<>();

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPhone = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> userEnquiryData = new MutableLiveData<>();
    private boolean valid = false;


    public ContactUsViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void onClick() {
        if (validate()) {
            apiCallUserEnquiry();
        }
    }

    private void apiCallUserEnquiry() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.userEnquiry(name.getValue(), email.getValue(),phone.getValue(), message.getValue());

        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().toString());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", ""+e.getMessage());
                    }

                } else {
                    userEnquiryData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<CommonApiResponse> call, @NotNull Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), ""+t.getMessage());
            }
        });
    }

    public MutableLiveData<CommonApiResponse> getEnquiryData() {
        return userEnquiryData;
    }


    private boolean validate() {
        valid = true;

        if (name.getValue() == null || name.getValue().isEmpty()) {
            showToast(getApplication().getApplicationContext(), "Please enter your name");
            valid = false;
        }

        if (message.getValue() == null || message.getValue().isEmpty()) {
            valid = false;
            errorMessage.setValue("Please enter your message");
        }

        if (phone.getValue() == null) {
            valid = false;
            errorPhone.setValue("Phone should not be empty");
        }

        if (email.getValue() == null || email.getValue().isEmpty()) {
            valid = false;
            errorEmail.setValue("Email should not be empty");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches()) {
            valid = false;
            errorEmail.setValue("Enter a valid email");
        }


        return valid;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
