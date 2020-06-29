package com.aldar.studentportal.ui.studentPortal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.databinding.ActivitySelectedCoursesBinding;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;
import com.aldar.studentportal.ui.activities.common.NavigationActivity;
import com.aldar.studentportal.ui.activities.common.faq.FaqViewmodel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectedCoursesActivity extends AppCompatActivity {
    private SelectedCoursesAdapter adapter;
    private ActivitySelectedCoursesBinding binding;
    private SelectedCoursesViewModel viewModel;
    private String strSectionId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_courses);
        viewModel = new ViewModelProvider(this).get(SelectedCoursesViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSelectedCoursesViewModel(viewModel);

        viewModel.getLiveData().observe(this, selectedCoursesModels -> {
            if (selectedCoursesModels != null) {
                binding.btnRegisterCourse.setVisibility(View.VISIBLE);
                binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this));
                adapter = new SelectedCoursesAdapter(selectedCoursesModels, this);
                binding.rvCourseslist.setAdapter(adapter);
                adapter.notifyDataSetChanged();


                if (selectedCoursesModels.size() > 0) {
                    for (int i = 0; i < selectedCoursesModels.size(); i++) {
                        if (i == 0) {
                            strSectionId += selectedCoursesModels.get(i).getSectionId();
                        } else {
                            strSectionId += "," + selectedCoursesModels.get(i).getSectionId();
                        }
                    }
                }
            }

        });


        binding.btnRegisterCourse.setOnClickListener(v -> {
            showDialog("Confirm", "Are you sure to register these courses!");
        });

        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        viewModel.getRegistrationReponseLiveData().observe(this, commonApiResponse -> {
            if (Boolean.parseBoolean(commonApiResponse.getSuccess())) {
                Toast.makeText(this, "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showDialog(String titlte, String message) {
        Dialog dialog = new Dialog(Objects.requireNonNull(SelectedCoursesActivity.this));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.course_advice_dialog);
        TextView tvTitle = dialog.findViewById(R.id.title);
        TextView tvMessage = dialog.findViewById(R.id.message);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        Button btnReview = dialog.findViewById(R.id.btn_review);

        tvTitle.setText(titlte);
        tvMessage.setText(message);

        btnOk.setText("Cancel");
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnReview.setText("Register");
        btnReview.setOnClickListener(v -> {
            dialog.dismiss();
            String studentID = String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
            int semesterId = SharedPreferencesManager.getInstance(getApplicationContext()).getIntValue("semester_id");
            viewModel.apiCallRegisterCourses(studentID, String.valueOf(semesterId), strSectionId);
        });

        dialog.show();
    }
}
