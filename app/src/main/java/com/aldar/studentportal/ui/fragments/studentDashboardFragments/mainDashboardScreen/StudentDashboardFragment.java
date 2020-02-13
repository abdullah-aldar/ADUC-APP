package com.aldar.studentportal.ui.fragments.studentDashboardFragments.mainDashboardScreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.StudentDashboardItemsAdapter;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;

import java.util.ArrayList;
import java.util.List;

public class StudentDashboardFragment extends Fragment {

    private RecyclerView rvDashboardItems;

    private int[] dashboardImages = {
            R.drawable.course_schedule, R.drawable.marks,
            R.drawable.study_plan, R.drawable.advice,
            R.drawable.letter, R.drawable.coins,
            R.drawable.profile, R.drawable.inbox,
            R.drawable.newsletter
    };

    public StudentDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_student_dashboard, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        rvDashboardItems = root.findViewById(R.id.rv_dashboard_item);
        initViews();
        return root;
    }


    private void initViews() {
        rvDashboardItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        StudentDashboardItemsAdapter dashboardItemsAdapter = new StudentDashboardItemsAdapter(getContext(), preparedDashboardItems());
        rvDashboardItems.setAdapter(dashboardItemsAdapter);

    }


    private List<DashboardItemModel> preparedDashboardItems() {
        List<DashboardItemModel> dashboardItemList = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.dashboard_items);

        int count = 0;
        for (String titleName : titles) {
            dashboardItemList.add(new DashboardItemModel(titleName, dashboardImages[count]));
            count++;
        }

        return dashboardItemList;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rvDashboardItems.setAdapter(null);
        rvDashboardItems = null;
    }
}
