package com.aldar.studentportal.viewModels;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseScheduleViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<CourseScheduleResponseModel> courseScheduleData = new MutableLiveData<>();
    private boolean valid = false;


    public CourseScheduleViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        apiCallUpdatePassword();
    }

    private void apiCallUpdatePassword(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CourseScheduleResponseModel> allUsers = services.getCourseSchedule();
        allUsers.enqueue(new Callback<CourseScheduleResponseModel>() {
            @Override
            public void onResponse(Call<CourseScheduleResponseModel> call, Response<CourseScheduleResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplication().getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    CourseScheduleResponseModel responseModel = new CourseScheduleResponseModel();
                    courseScheduleData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<CourseScheduleResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                Toast.makeText(getApplication().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<CourseScheduleResponseModel> getcourseScheduleData(){
        return courseScheduleData;
    }

}