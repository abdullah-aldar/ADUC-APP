package com.aldar.studentportal.ui.studentPortal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.mainDashboardScreen.StudentDashboardFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class StudentPortalMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal_main);


        if(savedInstanceState == null){
            GeneralUtilities.connectFragmentWithoutBack(this,new StudentDashboardFragment());
        }
    }


}
