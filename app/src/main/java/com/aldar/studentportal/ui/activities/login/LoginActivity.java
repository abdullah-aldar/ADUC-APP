package com.aldar.studentportal.ui.activities.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.ActivityLoginBinding;
import com.aldar.studentportal.models.loginModels.LoginResponseModel;
import com.aldar.studentportal.ui.activities.signup.SignUpActivity;
import com.aldar.studentportal.ui.activities.StudentPortalMainActivity;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private String strFcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((AppCompatActivity)this).getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
       // loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        if(strFcmToken == null || strFcmToken.isEmpty()){
            getFcmToken();
        }

        loginViewModel.getLoginResponseData().observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(LoginResponseModel loginResponseModel) {
                Toast.makeText(LoginActivity.this, ""+loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, StudentPortalMainActivity.class));
            }
        });


        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
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
                        //Toast.makeText(LoginActivity.this, ""+token, Toast.LENGTH_LONG).show();
                        GeneralUtilities.putStringValueInEditor(LoginActivity.this, "fcm_token", token);

                    }
                });
    }

}
