package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.inbox;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.FragmentComposeEmailBinding;
import com.aldar.studentportal.databinding.InboxFragmentBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;

public class ComposeEmailFragment extends Fragment {
    private FragmentComposeEmailBinding binding;
    private ComposeEmailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_compose_email, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ComposeEmailViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setComposeEmailViewModel(viewModel);


        viewModel.getSemsterScheduleData().observe(getViewLifecycleOwner(), semesterResponseModel -> {
            if (semesterResponseModel != null) {
                viewModel.apiCallCousesData(String.valueOf(semesterResponseModel.getData().get(1).getSemID()));
            }
        });

        getSectionData(viewModel.getcourseScheduleData());


        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }


    private void getSectionData(MutableLiveData<CourseScheduleResponseModel> courseScheduleMutableLiveData) {
        courseScheduleMutableLiveData.observe(getViewLifecycleOwner(), courseScheduleResponseModel -> {
            if (courseScheduleResponseModel.getData().size() > 0) {
                //converting arraylist to string array
                String[] namesArr = new String[courseScheduleResponseModel.getData().size()];
                for (int i = 0; i < courseScheduleResponseModel.getData().size(); i++) {
                    namesArr[i] = String.valueOf(courseScheduleResponseModel.getData().get(i).getSectionCode());
                }

                binding.sectionSpinner.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, namesArr, "Select"));
                binding.sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        viewModel.instructorName.setValue(String.valueOf(courseScheduleResponseModel.getData().get(position).getLecturer()));
                        viewModel.sectionID.setValue(String.valueOf(courseScheduleResponseModel.getData().get(position).getSectionCode()));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }
}
