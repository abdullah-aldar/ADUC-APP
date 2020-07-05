package com.aldar.studentportal.ui.studentPortal.gradeConversion;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.gradeConversionModel.GradeConversionResponse;
import com.aldar.studentportal.models.gradeConversionModel.SendRequestResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
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
    public MutableLiveData<Integer> studentID = new MutableLiveData<>();
    public MutableLiveData<String> semesterID = new MutableLiveData<>();
    public MutableLiveData<String> sectionID = new MutableLiveData<>();
    public MutableLiveData<String> reason = new MutableLiveData<>();

    private MutableLiveData<SemesterResponseModel> semesterData = new MutableLiveData<>();
    private MutableLiveData<GradeConversionResponse> courseScheduleData = new MutableLiveData<>();
    private MutableLiveData<SendRequestResponse> studentRequestData = new MutableLiveData<>();

    public GradeConversionViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
        apiCallSemester();

    }

    private void apiCallSemester() {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<SemesterResponseModel> allUsers = services.getSemester();
        allUsers.enqueue(new Callback<SemesterResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<SemesterResponseModel> call, @NotNull Response<SemesterResponseModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    semesterData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<SemesterResponseModel> call, Throwable t) {
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public void apiCallCourseData() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<GradeConversionResponse> allUsers = services.gradeConversionCourses(studentID.getValue(), semesterID.getValue());
        allUsers.enqueue(new Callback<GradeConversionResponse>() {
            @Override
            public void onResponse(@NotNull Call<GradeConversionResponse> call, @NotNull Response<GradeConversionResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (response.body().getSuccess()) {
                    courseScheduleData.setValue(response.body());
                } else {
                    courseScheduleData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GradeConversionResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public void apiCallRequest() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<SendRequestResponse> allUsers = services.sendRequest(studentID.getValue(), semesterID.getValue(), reason.getValue(),sectionID.getValue());
        allUsers.enqueue(new Callback<SendRequestResponse>() {
            @Override
            public void onResponse(@NotNull Call<SendRequestResponse> call, @NotNull Response<SendRequestResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (response.body().getSuccess()) {
                    studentRequestData.setValue(response.body());
                } else {
                    studentRequestData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SendRequestResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<GradeConversionResponse> getResponseData() {
        return courseScheduleData;
    }

    public MutableLiveData<SemesterResponseModel> getSemsterData() {
        return semesterData;
    }

    public MutableLiveData<SendRequestResponse> getStudentRequestData() {
        return studentRequestData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}