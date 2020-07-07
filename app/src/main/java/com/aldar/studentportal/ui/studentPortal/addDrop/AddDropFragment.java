package com.aldar.studentportal.ui.studentPortal.addDrop;

import android.os.Bundle;

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
import android.widget.AdapterView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AddDropAdapter;
import com.aldar.studentportal.adapters.AvailableCourseAdapter;
import com.aldar.studentportal.adapters.CourseAdviceAdapter;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.adapters.GradeConversionAdapter;
import com.aldar.studentportal.adapters.RegisterCoursesAdapter;
import com.aldar.studentportal.databinding.FragmentAddDropBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesResponse;
import com.aldar.studentportal.models.coursesAdviceModels.CourseAdviceResponseModel;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionResponse;
import com.aldar.studentportal.models.semesterScheduleModel.SemesterResponseModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

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
        binding.setLifecycleOwner(this);
        binding.setAddDropViewModel(viewModel);

        binding.tvStudentId.setText(SharedPreferencesManager.getInstance(getActivity()).getStringValue("student_username"));
        binding.tvStudentName.setText(SharedPreferencesManager.getInstance(getActivity()).getStringValue("student_name"));
        binding.tvPrograme.setText(SharedPreferencesManager.getInstance(getActivity()).getStringValue("student_programe"));

        getSemesterData(viewModel.getSemsterData());
        getCourseData(viewModel.getResponseData());

        binding.btnRegisterCourse.setOnClickListener(view -> {
         binding.btnRegisterCourse.setBackgroundResource(R.drawable.gradient);
            binding.btnAvailableCourse.setBackgroundResource(R.drawable.round_background);
            binding.btnAvailableCourse.setTextColor(getResources().getColor(R.color.gray_black));
            binding.btnRegisterCourse.setTextColor(getResources().getColor(R.color.white));
            viewModel.apiCallRegisteredCourseData();
        });

        binding.btnAvailableCourse.setOnClickListener(view -> {
            binding.btnAvailableCourse.setBackgroundResource(R.drawable.gradient);
            binding.btnRegisterCourse.setBackgroundResource(R.drawable.round_background);
            binding.btnAvailableCourse.setTextColor(getResources().getColor(R.color.white));
            binding.btnRegisterCourse.setTextColor(getResources().getColor(R.color.gray_black));
            getAvailableCourseData(viewModel.getAvailableCourseData());
            viewModel.apiCallAvailableCourses();

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
                        viewModel.apiCallRegisteredCourseData();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    private void getCourseData(MutableLiveData<StudentRegisteredCoursesResponse> responseData) {
        responseData.observe(getViewLifecycleOwner(), gradeConversionResponse -> {
            if (gradeConversionResponse.getData() != null) {
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                RegisterCoursesAdapter adapter = new RegisterCoursesAdapter(getActivity(),gradeConversionResponse.getData());
                binding.recyclerView.setAdapter(adapter);
            }
            else {
                binding.recyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void getAvailableCourseData(MutableLiveData<CourseAdviceResponseModel> availableCourseData) {
        availableCourseData.observe(getViewLifecycleOwner(), courseAdviceResponseModel -> {

            if(courseAdviceResponseModel.getData() != null){
//                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                AvailableCourseAdapter adapter = new AvailableCourseAdapter(getActivity(),courseAdviceResponseModel.getData());
                binding.recyclerView.setAdapter(adapter);
            }
            else {
            }
        });
    }

    @Override
    public void setSectionID(String sectionID) {

    }
}