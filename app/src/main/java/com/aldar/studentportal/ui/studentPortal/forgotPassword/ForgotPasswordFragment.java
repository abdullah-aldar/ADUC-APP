package com.aldar.studentportal.ui.studentPortal.forgotPassword;

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
import com.aldar.studentportal.databinding.FragmentForgotPasswordBinding;
import com.aldar.studentportal.models.forgotPasswordModels.ForgotPasswordResponseModel;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

public class ForgotPasswordFragment extends Fragment {
    private FragmentForgotPasswordBinding binding;
    private ForgotPasswordViewModel viewModel;

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
        viewModel =  new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setForgotViewModel(viewModel);

        getLifecycle().addObserver(viewModel);

        viewModel.getForgotPasswordLiveData().observe(getViewLifecycleOwner(), forgotPasswordResponseModel -> {
           if(forgotPasswordResponseModel != null){
               if(Boolean.parseBoolean(forgotPasswordResponseModel.getSuccess())){

                   SharedPreferencesManager.getInstance(getActivity()).setStringValueInEditor("otp",forgotPasswordResponseModel.getOtp());
                   GeneralUtilities.connectFragmentWithBackWithAnimation(getContext(),new VerifyCodeFragment());

               }
           }
        });


    }
}
