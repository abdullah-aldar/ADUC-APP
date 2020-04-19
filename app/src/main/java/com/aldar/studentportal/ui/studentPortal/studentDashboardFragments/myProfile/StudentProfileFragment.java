package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myProfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentStudentProfileBinding;
import com.aldar.studentportal.models.studentProfileModel.ProfileDataModel;
import com.aldar.studentportal.models.studentProfileModel.ProfileResponseModel;

import java.util.List;


public class StudentProfileFragment extends Fragment {
    private FragmentStudentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_profile,container,false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StudentProfileViewModel viewModel = new ViewModelProvider(this).get(StudentProfileViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setStudenProfileViewModel(viewModel);

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
    }
}
