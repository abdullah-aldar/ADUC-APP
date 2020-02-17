package com.aldar.studentportal.ui.fragments.signUp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentSignUpBinding;
import com.aldar.studentportal.ui.fragments.login.LoginViewModel;


public class SignUpFragment extends Fragment {
    private SignupViewModel signupViewModel;
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSignupViewModel(signupViewModel);
    }
}
