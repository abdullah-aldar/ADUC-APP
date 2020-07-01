package com.aldar.studentportal.ui.studentPortal.library;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.libraryModels.LibraryResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    public MutableLiveData<String> searchBookBy = new MutableLiveData<>();
    public MutableLiveData<String> bookDescription = new MutableLiveData<>();
    private MutableLiveData<LibraryResponseModel> libraryData = new MutableLiveData<>();

    public LibraryViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
    }

    public void getLibraryBooks() {
        progressBar.setValue(0);
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<LibraryResponseModel> allUsers = services.getLibraryBooks(searchBookBy.getValue(), bookDescription.getValue());
        allUsers.enqueue(new Callback<LibraryResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<LibraryResponseModel> call, @NotNull Response<LibraryResponseModel> response) {
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
            public void onFailure(Call<LibraryResponseModel> call, Throwable t) {
                progressBar.setValue(8);
                showToast(t.getMessage());
            }
        });
    }

    public MutableLiveData<LibraryResponseModel> getLibraryData() {
        return libraryData;
    }

    private void showToast(String message) {
        Toast.makeText(getApplication(), "" + message, Toast.LENGTH_SHORT).show();
    }
}
