package com.aldar.studentportal.ui.studentPortal.inbox;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.aldar.studentportal.models.inboxModels.StudentInboxResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInboxViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<StudentInboxResponseModel> inboxData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();


    public StudentInboxViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        apiCallStudentInbox();
    }

    private void apiCallStudentInbox(){
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<StudentInboxResponseModel> allUsers = services.getStudentInbox(studentID.getValue());
        allUsers.enqueue(new Callback<StudentInboxResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<StudentInboxResponseModel> call, @NotNull Response<StudentInboxResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    StudentInboxResponseModel responseModel = new StudentInboxResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    inboxData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<StudentInboxResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public MutableLiveData<StudentInboxResponseModel> getStudentInboxData(){
        return inboxData;
    }

    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
