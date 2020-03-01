package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseSchedule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.databinding.FragmentCourseScheduleBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;

public class CourseScheduleFragment extends Fragment{
    private FragmentCourseScheduleBinding binding;
    private  CourseScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_course_schedule, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CourseScheduleViewModel viewModel =  new ViewModelProvider(this).get(CourseScheduleViewModel.class);
        binding.rvCourseSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCourseScheduleViewModel(viewModel);

        getCourseData(viewModel.getcourseScheduleData());

        binding.ivBack.setOnClickListener(v -> {
        getActivity().onBackPressed();
        });
    }


    private void getCourseData(MutableLiveData<CourseScheduleResponseModel> mutableLiveData){
        mutableLiveData.observe(getViewLifecycleOwner(), courseScheduleResponseModel -> {
            if(courseScheduleResponseModel != null){
                adapter = new CourseScheduleAdapter(courseScheduleResponseModel.getData());
                binding.rvCourseSchedule.setAdapter(adapter);
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
