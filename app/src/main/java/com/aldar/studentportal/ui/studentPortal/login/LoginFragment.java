package com.aldar.studentportal.ui.studentPortal.login;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentLoginBinding;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.ui.studentPortal.forgotPassword.ForgotPasswordFragment;
import com.aldar.studentportal.ui.studentPortal.signUp.CheckUsernameFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.mainDashboardScreen.StudentDashboardFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.ByteArrayOutputStream;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private String strFcmToken;
    FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        getFcmToken();
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        if (strFcmToken == null || strFcmToken.isEmpty()) {
            getFcmToken();
        }

        loginViewModel.getLoginResponseData().observe(getViewLifecycleOwner(), new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(LoginResponseModel loginResponseModel) {

                if (loginResponseModel.getStatus().equals("200")) {
                    SharedPreferencesManager.getInstance(getContext()).setIntValueInEditor("student_id", loginResponseModel.getData().getStudentID());
                    SharedPreferencesManager.getInstance(getContext()).setStringValueInEditor("student_username", loginResponseModel.getData().getGivenStudentId());
                    SharedPreferencesManager.getInstance(getContext()).setStringValueInEditor("student_name", loginResponseModel.getData().getStudentName());
                    SharedPreferencesManager.getInstance(getContext()).setStringValueInEditor("student_advisor", loginResponseModel.getData().getAdvisor());
                    SharedPreferencesManager.getInstance(getContext()).setStringValueInEditor("student_programe", loginResponseModel.getData().getProgram());
                    SharedPreferencesManager.getInstance(getContext()).setStringValueInEditor("concentration", loginResponseModel.getData().getConcentration());
                    SharedPreferencesManager.getInstance(getContext()).setBooleaninEditor("isLogin", true);

                    GeneralUtilities.connectFragmentWithoutBack(getContext(), new StudentDashboardFragment());
                }
                Toast.makeText(getContext(), "" + loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtilities.connectFragmentWithBack(getContext(), new CheckUsernameFragment());
            }
        });

        binding.tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtilities.connectFragmentWithBack(getContext(), new ForgotPasswordFragment());
            }
        });
    }

    private void getFcmToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Error", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        SharedPreferencesManager.getInstance(getActivity()).setStringValueInEditor("fcm_token", token);
                    }
                });
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
