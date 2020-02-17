package com.aldar.studentportal.ui.activities.containerActivities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.fragments.login.LoginFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class LoginSignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((AppCompatActivity)this).getSupportActionBar().hide();


        GeneralUtilities.connectFragmentWithoutBack(LoginSignUpActivity.this,new LoginFragment());

    }

}
