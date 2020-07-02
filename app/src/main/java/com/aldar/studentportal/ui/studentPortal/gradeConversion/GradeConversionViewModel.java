package com.aldar.studentportal.ui.studentPortal.gradeConversion;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.aldar.studentportal.models.announcementModel.AnnouncementReponseModel;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GradeConversionViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();
    private MutableLiveData<CourseScheduleResponseModel> courseScheduleData = new MutableLiveData<>();

    public GradeConversionViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        apiCallCouseSchedule();
    }

    public void apiCallCouseSchedule() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseScheduleResponseModel> allUsers = services.getCourseSchedule(studentID.getValue(), "98");
        allUsers.enqueue(new Callback<CourseScheduleResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CourseScheduleResponseModel> call, @NotNull Response<CourseScheduleResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (Boolean.parseBoolean(response.body().getSuccess())) {
                    courseScheduleData.setValue(response.body());
                } else {
                    courseScheduleData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CourseScheduleResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<CourseScheduleResponseModel> getResponseData() {
        return courseScheduleData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}