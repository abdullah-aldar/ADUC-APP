package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.library;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aldar.studentportal.models.libraryModels.DigitalLibraryResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DigitalLibraryViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<DigitalLibraryResponseModel> libraryData = new MutableLiveData<>();

    public DigitalLibraryViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        apiCalDigitalLibrary();
    }

    public void apiCalDigitalLibrary() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<DigitalLibraryResponseModel> allUsers = services.getDigitalLibrary();
        allUsers.enqueue(new Callback<DigitalLibraryResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DigitalLibraryResponseModel> call, @NotNull Response<DigitalLibraryResponseModel> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {
                    libraryData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DigitalLibraryResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(t.getMessage());
            }
        });
    }

    public MutableLiveData<DigitalLibraryResponseModel> getDigitalLibraryData() {
        return libraryData;
    }

    private void showToast(String message) {
        Toast.makeText(getApplication(), "" + message, Toast.LENGTH_SHORT).show();
    }
}
