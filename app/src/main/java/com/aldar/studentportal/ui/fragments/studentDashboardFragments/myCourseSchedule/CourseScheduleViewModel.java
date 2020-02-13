package com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseSchedule;

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

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseScheduleViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<CourseScheduleResponseModel> courseScheduleData = new MutableLiveData<>();

    public CourseScheduleViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        apiCallUpdatePassword();
    }

    private void apiCallUpdatePassword(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseScheduleResponseModel> allUsers = services.getCourseSchedule("bba20161059","96");
        allUsers.enqueue(new Callback<CourseScheduleResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CourseScheduleResponseModel> call, @NotNull Response<CourseScheduleResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    CourseScheduleResponseModel responseModel = new CourseScheduleResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    courseScheduleData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<CourseScheduleResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<CourseScheduleResponseModel> getcourseScheduleData(){
        return courseScheduleData;
    }

    private void showToast(Context context,String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

}