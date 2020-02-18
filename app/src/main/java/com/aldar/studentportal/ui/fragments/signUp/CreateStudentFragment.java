package com.aldar.studentportal.ui.fragments.signUp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentCreateStudentBinding;
import com.aldar.studentportal.models.registerationModels.CommonApiResponse;
import com.aldar.studentportal.ui.fragments.login.LoginFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;


public class CreateStudentFragment extends Fragment {

    private FragmentCreateStudentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_student,container,false);


        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CreateStudentViewModel createStudentViewModel = new ViewModelProvider(this).get(CreateStudentViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCreateStudentViewModel(createStudentViewModel);

        createStudentViewModel.userName.setValue(GeneralUtilities.getSharedPreferences(getActivity()).getString("student_username",""));

        createStudentViewModel.createUserLiveData().observe(getViewLifecycleOwner(), new Observer<CommonApiResponse>() {
            @Override
            public void onChanged(CommonApiResponse commonApiResponse) {
                if(Boolean.parseBoolean(commonApiResponse.getSuccess())){
                    GeneralUtilities.connectFragmentWithoutBack(getContext(), new LoginFragment());
                }
            }
        });
    }



}
