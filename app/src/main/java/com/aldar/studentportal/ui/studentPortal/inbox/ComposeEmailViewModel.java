package com.aldar.studentportal.ui.studentPortal.inbox;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComposeEmailViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> sectionID = new MutableLiveData<>();
    public MutableLiveData<String> instructorName = new MutableLiveData<>();
    public MutableLiveData<String> subject = new MutableLiveData<>();
    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();

    private MutableLiveData<SemesterResponseModel> semesterData = new MutableLiveData<>();
    private MutableLiveData<CourseScheduleResponseModel> courseScheduleData = new MutableLiveData<>();


    public ComposeEmailViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        apiGetSemester();
    }

    private void apiGetSemester() {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<SemesterResponseModel> allUsers = services.getSemesterSchedule();
        allUsers.enqueue(new Callback<SemesterResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<SemesterResponseModel> call, @NotNull Response<SemesterResponseModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if(response.body().getSuccess()) {
                    semesterData.setValue(response.body());
                }
                else {
                    showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<SemesterResponseModel> call, Throwable t) {
                showToast(""+t.getMessage());
            }
        });
    }

    public void apiCallCousesData(String semID) {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseScheduleResponseModel> allUsers = services.getCourseSchedule(studentID.getValue(), semID);
        allUsers.enqueue(new Callback<CourseScheduleResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CourseScheduleResponseModel> call, @NotNull Response<CourseScheduleResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (Boolean.parseBoolean(response.body().getSuccess())) {
                    courseScheduleData.setValue(response.body());
                } else {
                    showToast(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CourseScheduleResponseModel> call, Throwable t) {
                showToast(""+t.getMessage());
            }
        });
    }

    public void apiCallSendMessage() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.sendMessage("5167",
                "97", "1674", "subject", "mesage");
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }
                } else {
                    showToast(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                progressBar.setValue(0);
                showToast(""+t.getMessage());
            }
        });
    }


    public MutableLiveData<CourseScheduleResponseModel> getcourseScheduleData() {
        return courseScheduleData;
    }

    public MutableLiveData<SemesterResponseModel> getSemsterScheduleData() {
        return semesterData;
    }

    private void showToast( String message) {
        Toast.makeText(getApplication().getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
    }
}
