package com.aldar.studentportal.ui.studentPortal.addDrop;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesResponse;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDropViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<Integer> studentID = new MutableLiveData<>();
    public MutableLiveData<String> semesterID = new MutableLiveData<>();
    public MutableLiveData<String> sectionID = new MutableLiveData<>();
    public MutableLiveData<String> reason = new MutableLiveData<>();

    private MutableLiveData<SemesterResponseModel> semesterData = new MutableLiveData<>();
    private MutableLiveData<StudentRegisteredCoursesResponse> registerCoursesData = new MutableLiveData<>();
    private MutableLiveData<CourseAdviceResponseModel> availableCoursesData = new MutableLiveData<>();

    public AddDropViewModel(@NonNull Application application) {
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

    public void apiCallRegisteredCourseData() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<StudentRegisteredCoursesResponse> allUsers = services.studentCoursesData(studentID.getValue(), semesterID.getValue());
        allUsers.enqueue(new Callback<StudentRegisteredCoursesResponse>() {
            @Override
            public void onResponse(@NotNull Call<StudentRegisteredCoursesResponse> call, @NotNull Response<StudentRegisteredCoursesResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (response.body().getSuccess()) {
                    registerCoursesData.setValue(response.body());
                } else {
                    registerCoursesData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StudentRegisteredCoursesResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public void apiCallAvailableCourses() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseAdviceResponseModel> allUsers = services.getCourseAdvice(studentID.getValue(), 100);
        allUsers.enqueue(new Callback<CourseAdviceResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CourseAdviceResponseModel> call, @NotNull Response<CourseAdviceResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    availableCoursesData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<CourseAdviceResponseModel> call, @NotNull Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<StudentRegisteredCoursesResponse> getResponseData() {
        return registerCoursesData;
    }

    public MutableLiveData<CourseAdviceResponseModel> getAvailableCourseData() {
        return availableCoursesData;
    }

    public MutableLiveData<SemesterResponseModel> getSemsterData() {
        return semesterData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}