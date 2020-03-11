package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.announcment;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<CourseScheduleResponseModel> courseScheduleData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();

    public AnnouncementViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        apiAnnouncement();
    }

    private void apiAnnouncement(){
//        progressBar.setValue(0);
//        APIService services = RetroClass.getApiClient().create(APIService.class);
//        Call<CourseScheduleResponseModel> allUsers = services.getCourseSchedule("BMC1412383","97");
//        allUsers.enqueue(new Callback<CourseScheduleResponseModel>() {
//            @Override
//            public void onResponse(@NotNull Call<CourseScheduleResponseModel> call, @NotNull Response<CourseScheduleResponseModel> response) {
//                progressBar.setValue(8);
//                if (response.body() == null) {
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
//                    } catch (Exception e) {
//                        Log.d("", e.getMessage());
//                    }
//
//                } else  {
//                    CourseScheduleResponseModel responseModel = new CourseScheduleResponseModel();
//                    responseModel.setMessage(response.body().getMessage());
//                    responseModel.setSuccess(response.body().getSuccess());
//                    responseModel.setData(response.body().getData());
//                    courseScheduleData.setValue(responseModel);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CourseScheduleResponseModel> call, Throwable t) {
//                progressBar.setValue(8);
//                showToast(getApplication().getApplicationContext(),t.getMessage());
//            }
//        });
    }

    public MutableLiveData<CourseScheduleResponseModel> getAnnouncementData(){
        return courseScheduleData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
