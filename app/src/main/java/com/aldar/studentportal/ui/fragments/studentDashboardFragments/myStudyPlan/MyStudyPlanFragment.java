package com.aldar.studentportal.ui.fragments.studentDashboardFragments.myStudyPlan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.adapters.StudyPlanAdapter;
import com.aldar.studentportal.databinding.FragmentMyStudyPlanBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseSchedule.CourseScheduleViewModel;


public class MyStudyPlanFragment extends Fragment {
    private FragmentMyStudyPlanBinding binding;
    private StudyPlanAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_my_study_plan, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CourseScheduleViewModel viewModel =  new ViewModelProvider(this).get(CourseScheduleViewModel.class);
        binding.rvStudyPlan.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudyPlanViewModel(viewModel);

        studyPlanData(viewModel.getcourseScheduleData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void studyPlanData(MutableLiveData<CourseScheduleResponseModel> mutableLiveData){
        mutableLiveData.observe(getViewLifecycleOwner(),courseScheduleResponseModel -> {
            if(courseScheduleResponseModel != null){
                adapter = new StudyPlanAdapter(getActivity(), courseScheduleResponseModel.getData());
                binding.rvStudyPlan.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }
}
