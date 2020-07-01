package com.aldar.studentportal.ui.studentPortal.myProfile;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.studentProfileModel.ProfileDataModel;
import com.aldar.studentportal.models.studentProfileModel.ProfileResponseModel;
import com.aldar.studentportal.models.updateProfileModel.UpdateProfileModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<List<ProfileDataModel>> marksData = new MutableLiveData<>();
    private MutableLiveData<String> studentID = new MutableLiveData<>();
    private MutableLiveData<UpdateProfileModel> updateProfileData = new MutableLiveData<>();
    boolean OtpCheck = false;


    public StudentProfileViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id")));
        apiCallProfile();
    }

    private void apiCallProfile() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<ProfileResponseModel> allUsers = services.getStudentProfile(studentID.getValue());
        allUsers.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ProfileResponseModel> call, @NotNull Response<ProfileResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    ProfileResponseModel responseModel = new ProfileResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    marksData.setValue(responseModel.getData());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public boolean apiCallUpdateProfile(String typeText, boolean otpVerify) {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<UpdateProfileModel> allUsers = services.updateProfile(Integer.parseInt(studentID.getValue()), "Mobile", typeText, otpVerify);
        allUsers.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<UpdateProfileModel> call, @NotNull Response<UpdateProfileModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (response.body().getSuccess()) {
                    OtpCheck = true;
                    SharedPreferencesManager.getInstance(getApplication()).setStringValueInEditor("otp", response.body().getData());
                    updateProfileData.setValue(response.body());
                } else {
                    OtpCheck = false;
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
        return OtpCheck;
    }

    public MutableLiveData<List<ProfileDataModel>> getProfileData() {
        return marksData;
    }

    public MutableLiveData<UpdateProfileModel> getUpdateProfileData() {
        return updateProfileData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
