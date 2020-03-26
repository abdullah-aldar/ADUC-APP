package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseAdvice;

import android.content.Intent;
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
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseAdviceAdapter;
import com.aldar.studentportal.adapters.MarksAdapter;
import com.aldar.studentportal.databinding.FragmentMyCourseAdviceBinding;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.ui.studentPortal.activities.SelectedCoursesActivity;


public class MyCourseAdviceFragment extends Fragment {
    private FragmentMyCourseAdviceBinding binding;
    private CourseAdviceAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_course_advice, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CourseAdviceViewModel viewModel = new ViewModelProvider(this).get(CourseAdviceViewModel.class);
        binding.rvCourseAdvice.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setAdviceViewModel(viewModel);

        coursesData(viewModel.getCourseAdviceData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.btnSeeCourses.setOnClickListener(v -> {
        startActivity(new Intent(getActivity(), SelectedCoursesActivity.class));
        });
    }

    private void coursesData(MutableLiveData<CourseAdviceResponseModel> mutableLiveData) {
        mutableLiveData.observe(getViewLifecycleOwner(), marksResponseModel -> {
            if (marksResponseModel != null) {
                adapter = new CourseAdviceAdapter(getActivity(), marksResponseModel.getData());
                binding.rvCourseAdvice.setAdapter(adapter);
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