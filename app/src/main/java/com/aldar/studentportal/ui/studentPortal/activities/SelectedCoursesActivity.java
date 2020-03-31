package com.aldar.studentportal.ui.studentPortal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.databinding.ActivitySelectedCoursesBinding;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;
import com.aldar.studentportal.ui.activities.common.faq.FaqViewmodel;

import java.util.ArrayList;
import java.util.List;

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
            if (commonApiResponse != null) {
                Toast.makeText(this, "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
