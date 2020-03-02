package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myMarks;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.mymarksmodels.MarksResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMarksViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<MarksResponseModel> marksData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();


    public MyMarksViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        apiStudyPlan();
    }

    private void apiStudyPlan(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<MarksResponseModel> allUsers = services.getStudentMarks("7450");
        allUsers.enqueue(new Callback<MarksResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MarksResponseModel> call, @NotNull Response<MarksResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    MarksResponseModel responseModel = new MarksResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    marksData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<MarksResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<MarksResponseModel> getMarksData(){
        return marksData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
