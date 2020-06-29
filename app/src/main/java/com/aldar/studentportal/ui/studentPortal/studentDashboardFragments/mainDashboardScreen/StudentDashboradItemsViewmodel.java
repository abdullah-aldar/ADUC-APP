package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.mainDashboardScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class StudentDashboradItemsViewmodel extends AndroidViewModel {
    private int[] dashboardImages = {
            R.drawable.course, R.drawable.marks,
            R.drawable.plan, R.drawable.advice,
            R.drawable.letter, R.drawable.coins,
            R.drawable.profile, R.drawable.inbox,
            R.drawable.classroom,R.drawable.library
    };


    public MutableLiveData<String> studentName = new MutableLiveData<>();
    public MutableLiveData<String> studentGivenID = new MutableLiveData<>();
    public MutableLiveData<String> advisor = new MutableLiveData<>();
    public MutableLiveData<String> course = new MutableLiveData<>();
    public MutableLiveData<String> major = new MutableLiveData<>();

    private MutableLiveData<List<DashboardItemModel>> mutableLiveData = new MutableLiveData<>();

    public StudentDashboradItemsViewmodel(@NonNull Application application) {
        super(application);
        initViewInDasboard();
    }


    private void initViewInDasboard(){
        studentGivenID.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_username"));
        studentName.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_name"));
        advisor.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_advisor"));
        course.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("student_programe"));
        major.setValue(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getStringValue("concentration"));
        mutableLiveData.setValue(preparedDashboardItems());
    }


    public MutableLiveData<List<DashboardItemModel>> getItemData(){
        return mutableLiveData;
    }

    private List<DashboardItemModel> preparedDashboardItems() {
        List<DashboardItemModel> dashboardItemList = new ArrayList<>();
        String[] titles = getApplication().getApplicationContext().getResources().getStringArray(R.array.dashboard_items);

        int count = 0;
        for (String titleName : titles) {
            dashboardItemList.add(new DashboardItemModel(count,titleName, dashboardImages[count]));
            count++;
        }

        return dashboardItemList;

    }
}
