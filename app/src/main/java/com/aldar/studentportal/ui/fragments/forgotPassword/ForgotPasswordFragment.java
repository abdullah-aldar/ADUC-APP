package com.aldar.studentportal.ui.fragments.forgotPassword;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentForgotPasswordBinding;
import com.aldar.studentportal.models.forgotPasswordModels.ForgotPasswordResponseModel;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseSchedule.CourseScheduleViewModel;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class ForgotPasswordFragment extends Fragment {
    private FragmentForgotPasswordBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_forgot_password,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ForgotPasswordViewModel viewModel =  new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setForgotViewModel(viewModel);


        viewModel.getForgotPasswordLiveData().observe(getViewLifecycleOwner(), new Observer<ForgotPasswordResponseModel>() {
            @Override
            public void onChanged(ForgotPasswordResponseModel forgotPasswordResponseModel) {
                if(Boolean.parseBoolean(forgotPasswordResponseModel.getSuccess())){
                    GeneralUtilities.putStringValueInEditor(getContext(),"otp",forgotPasswordResponseModel.getOtp());
                    GeneralUtilities.connectFragmentWithoutBack(getContext(),new VerifyCodeFragment());

                }
            }
        });
    }
}
