package com.aldar.studentportal.ui.studentPortal.addDrop;

import android.os.Bundle;

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
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AddDropAdapter;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.adapters.GradeConversionAdapter;
import com.aldar.studentportal.databinding.FragmentAddDropBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;

public class AddDropFragment extends Fragment implements SectionIDInterface {

    private AddDropViewModel viewModel;
    private FragmentAddDropBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_drop, container, false);

        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddDropViewModel.class);
        getSemesterData(viewModel.getSemsterData());
        getCourseData(viewModel.getResponseData());

        binding.btnRegisterCourse.setOnClickListener(view -> {

        });

        binding.btnDropCourse.setOnClickListener(view -> {

        });

        binding.ivBack.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });
    }

    private void getSemesterData(MutableLiveData<SemesterResponseModel> semsterData) {
        semsterData.observe(getViewLifecycleOwner(), semesterResponseModel -> {
            if (semesterResponseModel.getData().size() > 0) {
                //converting arraylist to string array
                String[] semesterArray = new String[semesterResponseModel.getData().size()];
                for (int i = 0; i < semesterResponseModel.getData().size(); i++) {
                    semesterArray[i] = String.valueOf(semesterResponseModel.getData().get(i).getSemName());
                }


                binding.spinnerSemester.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, semesterArray, "Select"));
                binding.spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        viewModel.semesterID.setValue(String.valueOf(semesterResponseModel.getData().get(position).getSemID()));
                        viewModel.apiCallCourseData();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    private void getCourseData(MutableLiveData<GradeConversionResponse> responseData) {
        responseData.observe(getViewLifecycleOwner(), gradeConversionResponse -> {
            if (gradeConversionResponse.getData() != null) {
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                AddDropAdapter adapter = new AddDropAdapter(gradeConversionResponse.getData(), this);
                binding.recyclerView.setAdapter(adapter);
            }
            else {
                binding.recyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setSectionID(String sectionID) {

    }
}