package com.aldar.studentportal.ui.studentPortal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;

import java.util.ArrayList;
import java.util.List;

public class SelectedCoursesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SelectedCoursesAdapter adapter;
    SelectedCoursesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_courses);
        recyclerView = findViewById(R.id.rv_courseslist);

        viewModel = new ViewModelProvider(this).get(SelectedCoursesViewModel.class);

        viewModel.getLiveData().observe(this, selectedCoursesModels -> {
            if (selectedCoursesModels != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new SelectedCoursesAdapter(selectedCoursesModels, this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }


}
