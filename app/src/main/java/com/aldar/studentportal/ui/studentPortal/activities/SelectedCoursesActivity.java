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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_courses);

        SelectedCoursesViewModel viewModel = new ViewModelProvider(this).get(SelectedCoursesViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSelectedCoursesViewModel(viewModel);

        viewModel.getLiveData().observe(this, selectedCoursesModels -> {
            if (selectedCoursesModels != null) {
                binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this));
                adapter = new SelectedCoursesAdapter(selectedCoursesModels, this);
                binding.rvCourseslist.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        binding.btnRegisterCourse.setOnClickListener(v -> {
            viewModel.apiCallRegisterCourses();
        });

        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        viewModel.getRegistrationReponseLiveData().observe(this, commonApiResponse -> {
            if (Boolean.parseBoolean(commonApiResponse.getSuccess())) {
                showDialog(commonApiResponse.getMessage());
            }
        });

    }


    private void showDialog(String message){
        Dialog dialog = new Dialog(Objects.requireNonNull(SelectedCoursesActivity.this));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.course_advice_dialog);
        TextView tvTitle = dialog.findViewById(R.id.title);
        TextView tvMessage = dialog.findViewById(R.id.message);
        Button btnOk = dialog.findViewById(R.id.btn_ok);

        tvTitle.setText("Courses Registered Successfully");
        tvMessage.setText(message);

        btnOk.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
