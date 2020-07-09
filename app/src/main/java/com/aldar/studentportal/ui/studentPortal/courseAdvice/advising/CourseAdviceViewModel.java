package com.aldar.studentportal.ui.studentPortal.courseAdvice.advising;

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
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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

    private MutableLiveData<List<AdvisedCourseDataModel>> adviceLocalData = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> saveCourseAdviseData = new MutableLiveData<>();


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
                    semesterData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SemesterResponseModel> call, Throwable t) {
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public void apiCallCourseAdvice() {
        if(progressBar != null){
            progressBar.setValue(0);
        }
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseAdviceResponseModel> allUsers = services.getCourseAdvice(Integer.parseInt(studentID.getValue()), 100);
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

    public void showCart() {
        ADUCCrud dictionaryCrud = new ADUCCrud(getApplication().getApplicationContext());
        List<AdvisedCourseDataModel> arrayListData = new ArrayList<>();
        Cursor cursor = dictionaryCrud.getAllCourses();

        while (cursor.moveToNext()) {
            String sectionId = cursor.getString(1).trim();
            String sectionCode = cursor.getString(2).trim();
            String courseCode = cursor.getString(3).trim();
            String courseName = cursor.getString(4).trim();
            String schedule = cursor.getString(5).trim();
            String insName = cursor.getString(6).trim();

            AdvisedCourseDataModel model = new AdvisedCourseDataModel();
            model.setSectionId(Integer.parseInt(sectionId));
            model.setSectionCode(sectionCode);
            model.setCourseCode(courseCode);
            model.setCourseName(courseName);
            model.setSchedule(schedule);

            arrayListData.add(model);
        }
        adviceLocalData.setValue(arrayListData);
    }

    public void apiCallSaveAdvisedCourses(String studentID, String semesterID, String strSectionId) {
        progressBar.setValue(0);
        APIService services;
        services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.saveCourseAdvice(studentID, semesterID, strSectionId);
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);
                } else {
                    saveCourseAdviseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
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

    public MutableLiveData<List<AdvisedCourseDataModel>> getAdvisedCartData() {
        return adviceLocalData;
    }

    public MutableLiveData<CommonApiResponse> saveAdvisedCoursesData() {
        return saveCourseAdviseData;
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
