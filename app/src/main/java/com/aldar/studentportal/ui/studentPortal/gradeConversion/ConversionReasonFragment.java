package com.aldar.studentportal.ui.studentPortal.gradeConversion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentConversionReasonBinding;
import com.aldar.studentportal.databinding.GradeConversionFragmentBinding;

public class ConversionReasonFragment extends Fragment {
    private GradeConversionViewModel viewModel;
    private FragmentConversionReasonBinding binding;
    private String strSectionID,strSemesterID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_conversion_reason,container,false);

        strSectionID = getArguments().getString("sectionID");
        strSemesterID = getArguments().getString("semesterID");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GradeConversionViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setGradeViewModel(viewModel);

        viewModel.sectionID.setValue(strSectionID);
        viewModel.semesterID.setValue(strSemesterID);

        viewModel.getStudentRequestData().observe(getViewLifecycleOwner(),sendRequestResponse -> {
            if(sendRequestResponse.getData() != null){
                Toast.makeText(getActivity(), ""+sendRequestResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnSave.setOnClickListener(v -> {
         viewModel.apiCallRequest();
        });

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
        binding.btnCancel.setOnClickListener(v->getActivity().onBackPressed());
    }


}