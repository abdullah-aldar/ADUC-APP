package com.aldar.studentportal.ui.studentPortal.courseAdvice.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.ActivityAdvisedCartBinding;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.advising.CourseAdviceViewModel;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses.SavedCoursesActivity;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

public class AdvisedCartActivity extends AppCompatActivity {
    private CourseAdviceViewModel viewModel;
    private String strSectionId = "";
    private ActivityAdvisedCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_advised_cart);

        viewModel = new ViewModelProvider(this).get(CourseAdviceViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCartCoursesViewModel(viewModel);
        viewModel.showCart();

        viewModel.getAdvisedCartData().observe(this, advisedCourseDataModels -> {
            binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this));
            SelectedCoursesAdapter adapter = new SelectedCoursesAdapter(advisedCourseDataModels, this,"Local");
            binding.rvCourseslist.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            for (int i = 0; i < advisedCourseDataModels.size(); i++) {
                if (i == 0) {
                    strSectionId += advisedCourseDataModels.get(i).getSectionId();
                } else {
                    strSectionId += "," + advisedCourseDataModels.get(i).getSectionId();
                }
            }
        });

        viewModel.saveAdvisedCoursesData().observe(this,commonApiResponse -> {
            if(commonApiResponse != null){
                Toast.makeText(this, ""+commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSeeCourses.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("semesterID",100);
            startActivity(new Intent(this, SavedCoursesActivity.class).putExtras(bundle));
        });

        binding.tvSave.setOnClickListener(view1 -> {
            showDialog();
        });
        binding.ivBack.setOnClickListener(view1 -> {
            onBackPressed();
        });
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.course_advice_dialog);
        TextView tvTitle = dialog.findViewById(R.id.title);
        TextView tvMessage = dialog.findViewById(R.id.message);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        Button btnReview = dialog.findViewById(R.id.btn_review);

        tvTitle.setText(getResources().getText(R.string.confirm));
        tvMessage.setText("Are you sure to register these courses!");

        btnOk.setText(getResources().getText(R.string.cancel));
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnReview.setText(getResources().getText(R.string.save));
        btnReview.setOnClickListener(v -> {
            dialog.dismiss();
            String studentID = String.valueOf(SharedPreferencesManager.getInstance(this).getIntValue("student_id"));
            int semesterId = SharedPreferencesManager.getInstance(this).getIntValue("semester_id");
            if (validate()) {
                viewModel.apiCallSaveAdvisedCourses(studentID, String.valueOf(semesterId), strSectionId);
            }
        });
        dialog.show();
    }

    private boolean validate() {
        boolean valid;

        if (strSectionId.isEmpty()) {
            valid = false;
            Toast.makeText(this, "you have no course selected", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }
        return valid;
    }
}