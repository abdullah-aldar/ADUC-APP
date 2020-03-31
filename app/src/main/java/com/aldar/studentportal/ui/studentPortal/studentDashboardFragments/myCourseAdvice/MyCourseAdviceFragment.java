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
import android.widget.AdapterView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseAdviceAdapter;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.adapters.MarksAdapter;
import com.aldar.studentportal.databinding.FragmentMyCourseAdviceBinding;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.ui.studentPortal.activities.SelectedCoursesActivity;


public class MyCourseAdviceFragment extends Fragment {
    private FragmentMyCourseAdviceBinding binding;
    private CourseAdviceAdapter adapter;
    private CourseAdviceViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(CourseAdviceViewModel.class);
        binding.rvCourseAdvice.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setAdviceViewModel(viewModel);

        getSemesterData(viewModel.getSemsterScheduleData());
        coursesData(viewModel.getCourseAdviceData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        binding.btnSeeCourses.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SelectedCoursesActivity.class));
        });
    }

    private void coursesData(MutableLiveData<CourseAdviceResponseModel> mutableLiveData) {
        mutableLiveData.observe(getViewLifecycleOwner(), adviceResponseModel -> {
            if (adviceResponseModel != null) {
                adapter = new CourseAdviceAdapter(getActivity(), adviceResponseModel.getData());
                binding.rvCourseAdvice.setAdapter(adapter);
            }
        });
    }

    private void getSemesterData(MutableLiveData<SemesterResponseModel> semsterScheduleData) {
        semsterScheduleData.observe(getViewLifecycleOwner(), semesterResponseModel -> {

            if (semesterResponseModel.getData().size() > 0) {
                //converting arraylist to string array
                String[] namesArr = new String[semesterResponseModel.getData().size()];
                for (int i = 0; i < semesterResponseModel.getData().size(); i++) {
                    namesArr[i] = String.valueOf(semesterResponseModel.getData().get(i).getSemName());
                }

                binding.semsterSpinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, namesArr, "Select"));
                binding.semsterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        viewModel.semesterID.setValue(semesterResponseModel.getData().get(position).getSemID());
                        viewModel.apiCallCourseAdvice();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
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