package com.aldar.studentportal.ui.studentPortal.gradeConversion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.adapters.GradeConversionAdapter;
import com.aldar.studentportal.databinding.GradeConversionFragmentBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;

public class GradeConversionFragment extends Fragment implements SectionIDInterface {


    private GradeConversionFragmentBinding binding;
    private GradeConversionViewModel viewModel;
    int check = 0;
    private String strSectionIDs;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.grade_conversion_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GradeConversionViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setGradeViewModel(viewModel);

        getSemesterData(viewModel.getSemsterData());
        getCourseData(viewModel.getResponseData());

        binding.btnNext.setOnClickListener(v -> laodFragment(new ConversionReasonFragment()));
        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
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

                        if(++check > 1) {

                        }

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
                binding.rvCourses.setVisibility(View.VISIBLE);
                binding.rvCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
                GradeConversionAdapter adapter = new GradeConversionAdapter(gradeConversionResponse.getData(), this);
                binding.rvCourses.setAdapter(adapter);
            }
            else {
                binding.rvCourses.setVisibility(View.GONE);
            }
        });
    }

    private void laodFragment(ConversionReasonFragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("sectionID", strSectionIDs);
        bundle.putString("semesterID", viewModel.semesterID.getValue());
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void setSectionID(String sectionID) {
       strSectionIDs = ""+sectionID;
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}