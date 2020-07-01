package com.aldar.studentportal.ui.studentPortal.myCourseAdvice;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAdviceViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<SemesterResponseModel> semesterData = new MutableLiveData<>();
    public MutableLiveData<Integer> semesterID = new MutableLiveData<>();
    private MutableLiveData<CourseAdviceResponseModel> marksData = new MutableLiveData<>();
    private MutableLiveData<String> studentID = new MutableLiveData<>();


    public CourseAdviceViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        apiGetSemester();
    }

    private void apiGetSemester(){
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<SemesterResponseModel> allUsers = services.getSemesterSchedule();
        allUsers.enqueue(new Callback<SemesterResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<SemesterResponseModel> call, @NotNull Response<SemesterResponseModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    SemesterResponseModel responseModel = new SemesterResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    semesterData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<SemesterResponseModel> call, Throwable t) {
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public void apiCallCourseAdvice() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseAdviceResponseModel> allUsers = services.getCourseAdvice(7259, semesterID.getValue());
        allUsers.enqueue(new Callback<CourseAdviceResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CourseAdviceResponseModel> call, @NotNull Response<CourseAdviceResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);
                } else {
                    marksData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<CourseAdviceResponseModel> call, @NotNull Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<CourseAdviceResponseModel> getCourseAdviceData() {
        return marksData;
    }

    public MutableLiveData<SemesterResponseModel> getSemsterScheduleData(){
        return semesterData;
    }


    private void showServerErrorMessage(Response response) {
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
        } catch (Exception e) {
            Log.d("", Objects.requireNonNull(e.getMessage()));
        }
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
