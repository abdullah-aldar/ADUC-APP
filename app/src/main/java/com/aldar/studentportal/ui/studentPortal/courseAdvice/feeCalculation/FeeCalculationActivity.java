package com.aldar.studentportal.ui.studentPortal.courseAdvice.feeCalculation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FeeCalCourseAdapter;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.ActivityFeeCalculationBinding;
import com.aldar.studentportal.ui.activities.common.LearnMoreActivity;
import com.aldar.studentportal.ui.activities.common.fee.OnlinePaymentActivity;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses.SelectedCoursesViewModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import java.lang.ref.WeakReference;

public class FeeCalculationActivity extends AppCompatActivity {
    private ActivityFeeCalculationBinding binding;
    private String strNetAmount;

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
                strNetAmount = String.valueOf(feeCalResponse.getData().get(0).getNetFees());
                binding.tvNetFee.setText("Net Fees = " + strNetAmount);

                if(feeCalResponse.getData().get(0).getSuffix().equals("Dr")){
                 binding.btnPayOnline.setVisibility(View.VISIBLE);
                }
                else {
                    binding.btnPayOnline.setVisibility(View.GONE);
                }


            }

            if (feeCalResponse.getData().get(0).getCourses() != null) {
                binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                FeeCalCourseAdapter adapter = new FeeCalCourseAdapter(feeCalResponse.getData().get(0).getCourses());
                binding.rvCourseslist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        binding.btnPayOnline.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("", strNetAmount);
            new InnerClass(FeeCalculationActivity.this).redirectToMore();
        });

        binding.ivBack.setOnClickListener(view1 -> {
            onBackPressed();
        });
    }

    public static class InnerClass {

        private final WeakReference<Activity> weakReference;

        public InnerClass(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        private void redirectToMore() {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.startActivity(new Intent(activity, OnlinePaymentActivity.class));
            }
        }
    }
}