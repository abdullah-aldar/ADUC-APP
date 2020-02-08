package com.aldar.studentportal.ui.fragments.studentDashboardFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.viewModels.CourseScheduleViewModel;

public class CourseScheduleFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseScheduleAdapter adapter;
    private CourseScheduleViewModel viewModel;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course_schedule, container, false);
        recyclerView = root.findViewById(R.id.rv_course_schedule);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CourseScheduleViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getcourseScheduleData().observe(getActivity(), new Observer<CourseScheduleResponseModel>() {
            @Override
            public void onChanged(CourseScheduleResponseModel courseScheduleResponseModel) {
//                adapter = new CourseScheduleAdapter(getActivity(),courseScheduleResponseModel);
//                recyclerView.setAdapter(adapter);
            }
        });
    }
}
