package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myStudyPlan;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.studyplan.StudyPlanResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStudyPlanViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<StudyPlanResponseModel> courseScheduleData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();


    public MyStudyPlanViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        apiStudyPlan();
    }

    private void apiStudyPlan(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<StudyPlanResponseModel> allUsers = services.getStudyPlan(studentID.getValue());
        allUsers.enqueue(new Callback<StudyPlanResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<StudyPlanResponseModel> call, @NotNull Response<StudyPlanResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    StudyPlanResponseModel responseModel = new StudyPlanResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    courseScheduleData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<StudyPlanResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<StudyPlanResponseModel> getStudyPlanData(){
        return courseScheduleData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
