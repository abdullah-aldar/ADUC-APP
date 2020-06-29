package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.notifications;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.announcementModel.AnnouncementReponseModel;
import com.aldar.studentportal.models.libraryModels.DigitalLibraryResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();
    private MutableLiveData<AnnouncementReponseModel> notificationData = new MutableLiveData<>();

    public NotificationViewModel(@NonNull Application application) {
        super(application);
        apiCallNotification();
    }


    private void apiCallNotification() {
        progressBar.setValue(0);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<AnnouncementReponseModel> allUsers = services.getAnnoucement(studentID.getValue());
        allUsers.enqueue(new Callback<AnnouncementReponseModel>() {
            @Override
            public void onResponse(@NotNull Call<AnnouncementReponseModel> call, @NotNull Response<AnnouncementReponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    notificationData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AnnouncementReponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<AnnouncementReponseModel> getNotificationData() {
        return notificationData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}