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
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentCheckUsernameBinding;
import com.aldar.studentportal.models.registerationModels.RegisterResponseModel;
import com.aldar.studentportal.utilities.GeneralUtilities;


public class CheckUsernameFragment extends Fragment {
    private CheckUsernameViewModel signupViewModel;
    private FragmentCheckUsernameBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_check_username,container,false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        signupViewModel = new ViewModelProvider(this).get(CheckUsernameViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setSignupViewModel(signupViewModel);

        signupViewModel.getSignUpData().observe(getViewLifecycleOwner(), new Observer<RegisterResponseModel>() {
            @Override
            public void onChanged(RegisterResponseModel registerResponseModel) {
                if(Boolean.parseBoolean(registerResponseModel.getSuccess())){

                    if(!signupViewModel.getCheck().getValue()){
                        GeneralUtilities.connectFragmentWithBack(getActivity(),
                                new VerifyUserFragment()).setArguments(passBundleData(registerResponseModel.getOtp()));
                        signupViewModel.getCheck().setValue(true);
                    }

                }
            }
        });

    }


    private Bundle passBundleData(String code){
        Bundle bundle = new Bundle();
        bundle.putString("verification_code",code);
        return bundle;
    }


}
