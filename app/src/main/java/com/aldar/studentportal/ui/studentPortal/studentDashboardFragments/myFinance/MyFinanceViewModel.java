package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myFinance;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.interfaces.CallBackImp;
import com.aldar.studentportal.models.financeModel.FinanceResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFinanceViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<FinanceResponseModel> marksData = new MutableLiveData<>();
    private MutableLiveData<String> studentID = new MutableLiveData<>();


    public MyFinanceViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        apiCallFinance();
    }

    private void apiCallFinance() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<FinanceResponseModel> allUsers = services.getStudentFinance("7450");
        allUsers.enqueue(new Callback<FinanceResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<FinanceResponseModel> call, @NotNull Response<FinanceResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);

                } else if (response.body().getData() == null) {
                    showServerErrorMessage(response);
                } else {
                    FinanceResponseModel responseModel = new FinanceResponseModel();
                    responseModel.setMessage(response.body().getMessage());
                    responseModel.setSuccess(response.body().getSuccess());
                    responseModel.setData(response.body().getData());
                    marksData.setValue(responseModel);
                }
            }

            @Override
            public void onFailure(Call<FinanceResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    public MutableLiveData<FinanceResponseModel> getFinanceData() {
        return marksData;
    }



    private void showServerErrorMessage(Response response){
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
