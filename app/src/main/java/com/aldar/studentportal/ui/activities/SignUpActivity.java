package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.ActivitySignUpBinding;
import com.aldar.studentportal.viewModels.SignupViewModel;

public class SignUpActivity extends AppCompatActivity {
    private SignupViewModel signupViewModel;
    private ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        signupViewModel = ViewModelProviders.of(this).get(SignupViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSignupViewModel(signupViewModel);

        binding.tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}
