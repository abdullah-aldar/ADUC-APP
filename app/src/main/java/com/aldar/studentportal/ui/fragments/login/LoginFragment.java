package com.aldar.studentportal.ui.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentLoginBinding;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.ui.activities.StudentPortalMainActivity;
import com.aldar.studentportal.ui.fragments.forgotPassword.ForgotPasswordFragment;
import com.aldar.studentportal.ui.fragments.signUp.CheckUsernameFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private String strFcmToken;
    FragmentLoginBinding binding;

    public static String base64Image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        if(strFcmToken == null || strFcmToken.isEmpty()){
            getFcmToken();
        }

        loginViewModel.getLoginResponseData().observe(getActivity(), new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(LoginResponseModel loginResponseModel) {

                if(loginResponseModel.getStatus().equals("200")){
                    GeneralUtilities.putIntValueInEditor(getContext(),"student_id",loginResponseModel.getData().getStudentID());
                    GeneralUtilities.putStringValueInEditor(getContext(),"student_username",loginResponseModel.getData().getGivenStudentId());
                    GeneralUtilities.putStringValueInEditor(getContext(),"student_name",loginResponseModel.getData().getStudentName());
                    GeneralUtilities.putStringValueInEditor(getContext(),"student_advisor",loginResponseModel.getData().getAdvisor());
                    GeneralUtilities.putStringValueInEditor(getContext(),"student_programe",loginResponseModel.getData().getProgram());
                    GeneralUtilities.putStringValueInEditor(getContext(),"concentration",loginResponseModel.getData().getConcentration());
                    GeneralUtilities.putBooleanValueInEditor(getContext(),"isLogin",true);

                    base64Image = loginResponseModel.getData().getPhoto();
                    startActivity(new Intent(getContext(), StudentPortalMainActivity.class));
                }
                Toast.makeText(getContext(), ""+loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtilities.connectFragmentWithBack(getContext(),new CheckUsernameFragment());
            }
        });

        binding.tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtilities.connectFragmentWithBack(getContext(),new ForgotPasswordFragment());
            }
        });
    }

    private void getFcmToken(){
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
                        Log.d("token",token);
                        //Toast.makeText(LoginSignUpActivity.this, ""+token, Toast.LENGTH_LONG).show();
                        GeneralUtilities.putStringValueInEditor(getActivity(), "fcm_token", token);

                    }
                });
    }


}
