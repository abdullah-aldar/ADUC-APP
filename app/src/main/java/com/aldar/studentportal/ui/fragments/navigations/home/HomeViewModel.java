package com.aldar.studentportal.ui.fragments.navigations.home;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.models.contactsModel.ContactDataModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private ArrayList<String> contactList = new ArrayList<>();
    private MutableLiveData<ArrayList<String>> contactMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> studentID = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        studentID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        sendContactToServer();
    }


    private void sendContactToServer() {
        APIService services = RetroClass.getApiClient().create(APIService.class);
        Call<ContactDataModel> allUsers = services.sendContactsToServer(studentID.getValue(), contactList);
        allUsers.enqueue(new Callback<ContactDataModel>() {
            @Override
            public void onResponse(@NotNull Call<ContactDataModel> call, @NotNull Response<ContactDataModel> response) {
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        showToast(getApplication().getApplicationContext(), jObjError.getString("message"));
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }

                } else {

                }
            }
            @Override
            public void onFailure(Call<ContactDataModel> call, Throwable t) {
                Log.d("contact_error", "" + t.getMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<String>> getContact() {
        return contactMutableLiveData;
    }

    private void showToast(Context context, String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

}

