package com.aldar.studentportal.ui.studentPortal.addDrop;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.addAndDropModel.AddDropCoursesModel;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.remote.APIService;
import com.aldar.studentportal.remote.RetroClass;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDropHistoryViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> progressBar = new MutableLiveData<>();
    private MutableLiveData<List<AddDropCoursesModel>> itemsDataLiveData = new MutableLiveData<>();
    private MutableLiveData<CommonApiResponse> saveCourseAdviseData = new MutableLiveData<>();

    public AddDropHistoryViewModel(@NonNull Application application) {
        super(application);
        progressBar.setValue(8);
        showAddDrop();
    }

    private void showAddDrop() {
        ADUCCrud aducCrud = new ADUCCrud(getApplication().getApplicationContext());
        ArrayList<AddDropCoursesModel> arrayListData = new ArrayList<>();
        Cursor cursor = aducCrud.getAddOrDrop();

        while (cursor.moveToNext()) {
            String sectionId = cursor.getString(1).trim();
            String sectionCode = cursor.getString(2).trim();
            String courseCode = cursor.getString(3).trim();
            String courseName = cursor.getString(4).trim();
            String schedule = cursor.getString(5).trim();

            AddDropCoursesModel model = new AddDropCoursesModel(
                    sectionId,
                    sectionCode,
                    courseCode,
                    courseName,
                    schedule
            );
            arrayListData.add(model);

        }
        itemsDataLiveData.setValue(arrayListData);
    }

    public void apiCallSaveAddDrop(String studentID, String semesterID, String strSectionId) {
        progressBar.setValue(0);
        APIService services;
        services = RetroClass.getApiClient().create(APIService.class);
        Call<CommonApiResponse> allUsers = services.saveCourseAdvice(studentID, semesterID, strSectionId);
        allUsers.enqueue(new Callback<CommonApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<CommonApiResponse> call, @NotNull Response<CommonApiResponse> response) {
                progressBar.setValue(8);
                if (response.body() == null) {
                    showServerErrorMessage(response);
                } else {
                    saveCourseAdviseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CommonApiResponse> call, Throwable t) {
                progressBar.setValue(8);
                showToast(getApplication().getApplicationContext(), t.getMessage());
            }
        });
    }

    MutableLiveData<List<AddDropCoursesModel>> getAdvisedCourses() {
        return itemsDataLiveData;
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
