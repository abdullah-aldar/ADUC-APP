package com.aldar.studentportal.ui.studentPortal.courseAdvice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.ActivitySelectedCoursesBinding;
import com.aldar.studentportal.models.selectedCoursesModel.AdvisedCourseDataModel;
import com.aldar.studentportal.models.selectedCoursesModel.AdvisedCourseResponseModel;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import java.util.List;

public class SelectedCoursesActivity extends AppCompatActivity {
    private SelectedCoursesAdapter adapter;
    private ActivitySelectedCoursesBinding binding;
    private SelectedCoursesViewModel viewModel;
    private String strSectionId = "", studentId;
    private int semesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_courses);

        studentId = String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            semesterId = bundle.getInt("semesterID");
        }


        viewModel = new ViewModelProvider(this).get(SelectedCoursesViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSelectedCoursesViewModel(viewModel);
        viewModel.apiCallGetAdvisedCourses(studentId, String.valueOf(semesterId));

        viewModel.getAdvisedServerData().observe(this, advisedCourseResponseModel -> {

            if (advisedCourseResponseModel.getData().get(0).getCourses().size() > 0) {
                loadFromServer(advisedCourseResponseModel.getData().get(0).getCourses());
            }
        });

        viewModel.getSaveAdviseResponse().observe(this, commonApiResponse -> {
            Toast.makeText(this, "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
        });


        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void loadFromServer(List<AdvisedCourseDataModel> selectedCoursesModels) {
        binding.rvCourseslist.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectedCoursesAdapter(selectedCoursesModels, this,"Server");
        binding.rvCourseslist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
