package com.aldar.studentportal.ui.studentPortal.courseAdvice.feeCalculation;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.feeCalculation.FeeCalResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeCalculationViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<FeeCalResponse> feeCalculationData = new MutableLiveData<>();


    public FeeCalculationViewModel(@NonNull Application application) {
        super(application);
    }

    public void apiCallFeeCalculation(String studentID, String semesterID) {
        progressBar.setValue(0);
        APIService services;
        services = RetroClass.getApiClient().create(APIService.class);
        Call<FeeCalResponse> allUsers = services.getFeeCalculation(studentID, semesterID);
        allUsers.enqueue(new Callback<FeeCalResponse>() {
            @Override
            public void onResponse(@NotNull Call<FeeCalResponse> call, @NotNull Response<FeeCalResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);

                } else {
                    feeCalculationData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FeeCalResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    MutableLiveData<FeeCalResponse> getFeeCalculationData() {
        return feeCalculationData;
    }

    private void showServerErrorMessage(Response response) {
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
        } catch (Exception e) {
            Log.d("", e.getMessage());
        }
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }
}
