package com.aldar.studentportal.ui.activities.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.ActivityFeeBinding;

public class FeeActivity extends AppCompatActivity {
    ActivityFeeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_fee);

        binding.ivBack.setOnClickListener(v->{
            onBackPressed();
        });
    }
}
