package com.aldar.studentportal.ui.studentPortal.letterRequest;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.aldar.studentportal.models.letterModels.LetterRequestResponseModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LetterRequestViewModel extends AndroidViewModel {
    public MutableLiveData<String> note = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> requestResponseData = new MutableLiveData<>();
    private MutableLiveData<LetterRequestResponseModel> allLetterData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();

    public LetterRequestViewModel(@NonNull Application application) {
        super(application);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        apiCallLetterType();
    }

    private void apiCallLetterType(){
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<LetterRequestResponseModel> allUsers = services.getLetterTypes();
        allUsers.enqueue(new Callback<LetterRequestResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<LetterRequestResponseModel> call, @NotNull Response<LetterRequestResponseModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else  {
                    LetterRequestResponseModel responseModel = new LetterRequestResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    allLetterData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<LetterRequestResponseModel> call, Throwable t) {
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }

    public void apiCallRequestLetter(String letterTo,String letterID){
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.letterRequest(letterID,studentID.getValue(),letterTo,note.getValue());
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(),jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                }
                else {
                    CommonApiResponse responseModel = new CommonApiResponse();
                    responseModel.setStatus(response.body().getStatus());
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    requestResponseData.setValue(responseModel);

                    showToast(getApplication().getApplicationContext(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                showToast(getApplication().getApplicationContext(),t.getMessage());
            }
        });
    }
    public MutableLiveData<LetterRequestResponseModel> getAllLetterResponseData(){
        return allLetterData;
    }

    public MutableLiveData<CommonApiResponse> getRequestResponseData(){
        return requestResponseData;
    }


    private void showToast(Context context, String message){
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

}