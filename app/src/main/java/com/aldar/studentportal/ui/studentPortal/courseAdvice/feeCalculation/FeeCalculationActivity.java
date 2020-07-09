package com.aldar.studentportal.ui.studentPortal.courseAdvice.feeCalculation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FeeCalCourseAdapter;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.ActivityFeeCalculationBinding;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses.SelectedCoursesViewModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

public class FeeCalculationActivity extends AppCompatActivity {
    private ActivityFeeCalculationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fee_calculation);


        String studentId = String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
        int semesterId = SharedPreferencesManager.getInstance(this).getIntValue("semester_id");


        FeeCalculationViewModel viewModel = new ViewModelProvider(this).get(FeeCalculationViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setFeeCalculationViewModel(viewModel);
        viewModel.apiCallFeeCalculation(studentId, String.valueOf(100));

        viewModel.getFeeCalculationData().observe(this, feeCalResponse -> {
            if (feeCalResponse.getData() != null) {
                binding.tvTutionFee.setText(String.valueOf(feeCalResponse.getData().get(0).getTuitionFees()));
                binding.tvAdminFee.setText(String.valueOf(feeCalResponse.getData().get(0).getAdminFees()));
                binding.tvBooks.setText(String.valueOf(feeCalResponse.getData().get(0).getBookFees()));
                binding.tvVat.setText(String.valueOf(feeCalResponse.getData().get(0).getVat()));
                binding.tvPreviousBalance.setText(String.valueOf(feeCalResponse.getData().get(0).getPreviousBalance()));
                binding.tvNetFee.setText("Net Fees = "+ feeCalResponse.getData().get(0).getNetFees());
            }

            if(feeCalResponse.getData().get(0).getCourses() != null){
                binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
                FeeCalCourseAdapter adapter = new FeeCalCourseAdapter(feeCalResponse.getData().get(0).getCourses());
                binding.rvCourseslist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }
}