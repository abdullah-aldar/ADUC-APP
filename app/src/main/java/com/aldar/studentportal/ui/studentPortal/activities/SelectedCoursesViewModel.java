package com.aldar.studentportal.ui.studentPortal.activities;

import android.app.Application;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;

import java.util.ArrayList;
import java.util.List;

public class SelectedCoursesViewModel extends AndroidViewModel{
    private ADUCCrud dictionaryCrud;
    private MutableLiveData<List<SelectedCoursesModel>> itemsDataLiveData = new MutableLiveData<>();

    public SelectedCoursesViewModel(@NonNull Application application) {
        super(application);
        showCart();
    }


    public void showCart() {
        dictionaryCrud = new ADUCCrud(getApplication().getApplicationContext());
        ArrayList<SelectedCoursesModel> arrayListData = new ArrayList<>();
        Cursor cursor = dictionaryCrud.getAllCourses();

        while (cursor.moveToNext()) {
            String sectionId = cursor.getString(1).trim();
            String sectionCode = cursor.getString(2).trim();
            String courseId = cursor.getString(3).trim();
            String courseCode = cursor.getString(4).trim();
            String courseName = cursor.getString(5).trim();
            String creditHours = cursor.getString(6).trim();
            String schedule = cursor.getString(7).trim();
            String insName = cursor.getString(8).trim();



            SelectedCoursesModel model = new SelectedCoursesModel(
                    sectionId,
                    sectionCode,
                    courseId,
                    courseCode,
                    courseName,
                    creditHours,
                    schedule,
                    insName
            );
            arrayListData.add(model);
            itemsDataLiveData.setValue(arrayListData);
        }

    }

    public MutableLiveData<List<SelectedCoursesModel>> getLiveData(){
        return itemsDataLiveData;
    }

}
