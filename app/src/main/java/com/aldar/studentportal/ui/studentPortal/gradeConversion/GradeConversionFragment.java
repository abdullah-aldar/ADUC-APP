package com.aldar.studentportal.ui.studentPortal.gradeConversion;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CourseScheduleAdapter;
import com.aldar.studentportal.adapters.GradeConversionAdapter;
import com.aldar.studentportal.databinding.GradeConversionFragmentBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;
import com.aldar.studentportal.ui.studentPortal.library.DigitalLibraryFragment;

public class GradeConversionFragment extends Fragment {


    private GradeConversionFragmentBinding binding;
    private GradeConversionViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.grade_conversion_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GradeConversionViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setGradeViewModel(viewModel);


        getCourseData(viewModel.getResponseData());

        binding.btnNext.setOnClickListener(v->  laodFragment());
        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
        binding.btnCancel.setOnClickListener(v -> getActivity().onBackPressed());
    }

    private void getCourseData(MutableLiveData<CourseScheduleResponseModel> responseData) {
        responseData.observe(getViewLifecycleOwner(), coursesData -> {
            if (coursesData.getData() != null) {
                binding.rvCourses.setLayoutManager(new LinearLayoutManager(getActivity()));
                GradeConversionAdapter adapter = new GradeConversionAdapter(coursesData.getData());
                binding.rvCourses.setAdapter(adapter);
            }
        });
    }

    private void laodFragment() {
        getActivity().getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.fragment_container,new ConversionReasonFragment())
                .addToBackStack("")
                .commit();
    }
}