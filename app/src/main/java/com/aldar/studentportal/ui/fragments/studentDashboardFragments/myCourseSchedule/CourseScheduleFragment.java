package com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseSchedule;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.databinding.FragmentCourseScheduleBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;

import java.util.Objects;

public class CourseScheduleFragment extends Fragment {
    private CourseScheduleAdapter adapter;
    private FragmentCourseScheduleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_course_schedule, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CourseScheduleViewModel viewModel =  new ViewModelProvider(this).get(CourseScheduleViewModel.class);
        binding.rvCourseSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(this);
        binding.setCourseScheduleViewModel(viewModel);

        viewModel.getcourseScheduleData().observe(Objects.requireNonNull(getActivity()), new Observer<CourseScheduleResponseModel>() {
            @Override
            public void onChanged(CourseScheduleResponseModel courseScheduleResponseModel) {

                if(courseScheduleResponseModel != null){
                    adapter = new CourseScheduleAdapter(getActivity(),courseScheduleResponseModel.getData());
                    binding.rvCourseSchedule.setAdapter(adapter);
                }

            }
        });
    }
}
