package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.ui.fragments.DashboardFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;

public class StudentPortalMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal_main);

        GeneralUtilities.connectFragmentWithoutBack(this,new DashboardFragment());
    }
}
