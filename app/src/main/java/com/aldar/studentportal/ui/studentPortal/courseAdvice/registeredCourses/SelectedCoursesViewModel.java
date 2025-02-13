package com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.models.selectedCoursesModel.AdvisedCourseDataModel;
import com.aldar.studentportal.models.selectedCoursesModel.AdvisedCourseResponseModel;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedCoursesViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();

    private MutableLiveData<AdvisedCourseResponseModel> adviceServerData = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> saveCourseAdviseData = new MutableLiveData<>();

    public SelectedCoursesViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void apiCallGetAdvisedCourses(String studentID, String semesterID) {
        progressBar.setValue(0);
        APIService services;
        services = RetroClass.getApiClient().create(APIService.class);
        Call<AdvisedCourseResponseModel> allUsers = services.getAdvisedCourses(studentID, semesterID);
        allUsers.enqueue(new Callback<AdvisedCourseResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<AdvisedCourseResponseModel> call, @NotNull Response<AdvisedCourseResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);

                } else {
                    adviceServerData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AdvisedCourseResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    MutableLiveData<AdvisedCourseResponseModel> getAdvisedServerData() {
        return adviceServerData;
    }

    MutableLiveData<CommonApiResponse> getSaveAdviseResponse() {
        return saveCourseAdviseData;
    }

    private void showServerErrorMessage(Response response) {
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
        } catch (Exception e) {
            Log.d("", e.getMessage());
        }
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
