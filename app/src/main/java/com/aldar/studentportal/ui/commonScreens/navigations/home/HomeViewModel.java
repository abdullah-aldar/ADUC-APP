package com.aldar.studentportal.ui.commonScreens.navigations.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.newDataModels.NewsResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<CommonApiResponse> contactMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<NewsResponseModel> newsLiveData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        apiCallNews();
    }

    public void sendContactToServer(String deviceID,String name,String phone) {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.sendContactsToServer(deviceID, name,phone);
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                  contactMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                Log.d("contact_error", "" + t.getMessage());
            }
        });
    }

    public void apiCallNews() {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<NewsResponseModel> allUsers = services.getNews();
        allUsers.enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<NewsResponseModel> call, @NotNull Response<NewsResponseModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    newsLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Log.d("contact_error", "" + t.getMessage());
            }
        });
    }


    public MutableLiveData<CommonApiResponse> getContact() {
        return contactMutableLiveData;
    }

    public MutableLiveData<NewsResponseModel> getNews() {
        return newsLiveData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

}

