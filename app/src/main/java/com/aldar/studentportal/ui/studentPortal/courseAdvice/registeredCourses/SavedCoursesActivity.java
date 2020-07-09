package com.aldar.studentportal.ui.studentPortal.courseAdvice.registeredCourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.SelectedCoursesAdapter;
import com.aldar.studentportal.databinding.ActivitySavedCoursesBinding;
import com.aldar.studentportal.models.selectedCoursesModel.AdvisedCourseDataModel;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.feeCalculation.FeeCalculationActivity;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

import java.util.List;

public class SavedCoursesActivity extends AppCompatActivity {
    private SelectedCoursesAdapter adapter;
    private ActivitySavedCoursesBinding binding;
    private SelectedCoursesViewModel viewModel;
    private String studentId;
    private int semesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_saved_courses);

        studentId = String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
        semesterId = SharedPreferencesManager.getInstance(this).getIntValue("semester_id");


        viewModel = new ViewModelProvider(this).get(SelectedCoursesViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSelectedCoursesViewModel(viewModel);
        viewModel.apiCallGetAdvisedCourses(studentId, String.valueOf(100));

        viewModel.getAdvisedServerData().observe(this, advisedCourseResponseModel -> {
            if (advisedCourseResponseModel.getData().get(0).getCourses().size() > 0) {
                loadFromServer(advisedCourseResponseModel.getData().get(0).getCourses());
            }
        });

        viewModel.getSaveAdviseResponse().observe(this, commonApiResponse -> {
            Toast.makeText(this, "" + commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
        });

        binding.btnFeeCalculation.setOnClickListener(view -> {
            startActivity(new Intent(this, FeeCalculationActivity.class));
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
