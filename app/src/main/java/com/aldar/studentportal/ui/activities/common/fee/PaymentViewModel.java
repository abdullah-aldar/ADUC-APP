package com.aldar.studentportal.ui.activities.common.fee;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.chequesModels.ChequeResponseModel;
import com.aldar.studentportal.models.studentInfoModels.StudentResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();
    public MutableLiveData<String> studentName = new MutableLiveData<>();
    public MutableLiveData<String> studentProgram = new MutableLiveData<>();
    public MutableLiveData<String> studentConcentration = new MutableLiveData<>();
    public MutableLiveData<String> currentBalance = new MutableLiveData<>();
    public MutableLiveData<String> paymentMethod = new MutableLiveData<>();
    public MutableLiveData<String> amount = new MutableLiveData<>();
    public MutableLiveData<String> note = new MutableLiveData<>();

    private MutableLiveData<ChequeResponseModel> studentCheques = new MutableLiveData<>();

    public PaymentViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }


    public void apiCalStudentInfo() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<StudentResponseModel> allUsers = services.getStudentInfo(studentID.getValue());
        allUsers.enqueue(new Callback<StudentResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<StudentResponseModel> call, @NotNull Response<StudentResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else if (response.body().getSuccess()) {
                    studentName.setValue(response.body().getData().getStudentName());
                    studentProgram.setValue(response.body().getData().getProgram());
                    studentConcentration.setValue(response.body().getData().getConcentration());
                    currentBalance.setValue(String.valueOf(response.body().getData().getBalance()));

                } else {
                    showToast(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<StudentResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(t.getMessage());
            }
        });
    }

    public void apiCalGetCheques() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<ChequeResponseModel> allUsers = services.getCheques("CEE20183174");
        allUsers.enqueue(new Callback<ChequeResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ChequeResponseModel> call, @NotNull Response<ChequeResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    studentCheques.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChequeResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(t.getMessage());
            }
        });
    }

    public MutableLiveData<ChequeResponseModel> getCheques() {
        return studentCheques;
    }

    private void showToast(String message) {
        Toast.makeText(getApplication(), "" + message, Toast.LENGTH_SHORT).show();
    }
}
