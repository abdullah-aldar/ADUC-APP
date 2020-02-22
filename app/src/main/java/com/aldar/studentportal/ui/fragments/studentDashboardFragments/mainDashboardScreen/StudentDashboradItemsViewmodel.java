package com.aldar.studentportal.ui.fragments.studentDashboardFragments.mainDashboardScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.utilities.GeneralUtilities;

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
    public MutableLiveData<String> studentID = new MutableLiveData<>();
    public MutableLiveData<String> advisor = new MutableLiveData<>();
    public MutableLiveData<String> course = new MutableLiveData<>();
    public MutableLiveData<String> major = new MutableLiveData<>();

    private MutableLiveData<List<DashboardItemModel>> mutableLiveData = new MutableLiveData<>();

    public StudentDashboradItemsViewmodel(@NonNull Application application) {
        super(application);
        initDasboardData();
    }


    private void initDasboardData(){
        studentID.setValue(GeneralUtilities.getSharedPreferences(getApplication().getApplicationContext()).getString("student_name",""));
        studentName.setValue(GeneralUtilities.getSharedPreferences(getApplication().getApplicationContext()).getString("student_username",""));
        advisor.setValue(GeneralUtilities.getSharedPreferences(getApplication().getApplicationContext()).getString("student_advisor",""));
        course.setValue(GeneralUtilities.getSharedPreferences(getApplication().getApplicationContext()).getString("student_programe",""));
        major.setValue(GeneralUtilities.getSharedPreferences(getApplication().getApplicationContext()).getString("concentration",""));
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
            dashboardItemList.add(new DashboardItemModel(titleName, dashboardImages[count]));
            count++;
        }

        return dashboardItemList;

    }
}
