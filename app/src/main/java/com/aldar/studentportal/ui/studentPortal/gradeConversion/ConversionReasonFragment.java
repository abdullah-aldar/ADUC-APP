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

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentConversionReasonBinding;
import com.aldar.studentportal.databinding.GradeConversionFragmentBinding;

public class ConversionReasonFragment extends Fragment {
    private GradeConversionViewModel viewModel;
    private FragmentConversionReasonBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_conversion_reason,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GradeConversionViewModel.class);

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
    }


}