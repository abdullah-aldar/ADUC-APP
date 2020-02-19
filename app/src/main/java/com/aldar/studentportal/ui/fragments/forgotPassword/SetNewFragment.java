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
import com.aldar.studentportal.databinding.FragmentSetNewBinding;
import com.aldar.studentportal.models.forgotPasswordModels.UpdatePasswordResponseModel;
import com.aldar.studentportal.ui.fragments.login.LoginFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class SetNewFragment extends Fragment {
    private FragmentSetNewBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_set_new,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ChangePasswordViewModel viewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setChangeViewModel(viewModel);


        viewModel.getUpdatePasswordLiveData().observe(getViewLifecycleOwner(), new Observer<UpdatePasswordResponseModel>() {
            @Override
            public void onChanged(UpdatePasswordResponseModel updatePasswordResponseModel) {
                if(Boolean.parseBoolean(updatePasswordResponseModel.getSuccess())){
                    GeneralUtilities.connectFragmentWithBack(getActivity(),new LoginFragment());
                }
            }
        });

    }
}
