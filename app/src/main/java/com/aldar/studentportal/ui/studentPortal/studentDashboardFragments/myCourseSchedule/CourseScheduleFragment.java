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
import android.widget.AdapterView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.FragmentCourseScheduleBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;

import javax.inject.Inject;

public class CourseScheduleFragment extends Fragment {
    private FragmentCourseScheduleBinding binding;
    private CourseScheduleAdapter adapter;
    private CourseScheduleViewModel viewModel;

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

        viewModel = new ViewModelProvider(this).get(CourseScheduleViewModel.class);
        binding.rvCourseSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCourseScheduleViewModel(viewModel);

        getSemesterData(viewModel.getSemsterScheduleData());
        getCourseData(viewModel.getcourseScheduleData());

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
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

                binding.scheduleSpinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, namesArr, "Select"));
                binding.scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        viewModel.semesterID.setValue(String.valueOf(semesterResponseModel.getData().get(position).getSemID()));
                        viewModel.apiCallCouseSchedule();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }


    private void getCourseData(MutableLiveData<CourseScheduleResponseModel> mutableLiveData) {
        mutableLiveData.observe(getViewLifecycleOwner(), courseScheduleResponseModel -> {
            if (courseScheduleResponseModel.getData() != null) {
                adapter = new CourseScheduleAdapter(courseScheduleResponseModel.getData());
                binding.rvCourseSchedule.setAdapter(adapter);
                binding.tvNoData.setVisibility(View.GONE);
            } else {
                binding.rvCourseSchedule.setAdapter(null);
                binding.tvNoData.setVisibility(View.VISIBLE);
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
