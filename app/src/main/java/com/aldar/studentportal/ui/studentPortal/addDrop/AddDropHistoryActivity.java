package com.aldar.studentportal.ui.studentPortal.addDrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AddDropAdapter;
import com.aldar.studentportal.databinding.ActivityAddDropHistoryBinding;
import com.aldar.studentportal.models.addAndDropModel.AddDropCoursesModel;

import java.util.List;

public class AddDropHistoryActivity extends AppCompatActivity {
    private AddDropAdapter adapter;
    private ActivityAddDropHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_drop_history);


        AddDropHistoryViewModel viewModel = new ViewModelProvider(this).get(AddDropHistoryViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setAdddropViewModel(viewModel);

        viewModel.getAdvisedCourses().observe(this, addDropCoursesModels -> {
            if (addDropCoursesModels != null) {
                loadFromLocal(addDropCoursesModels);
            }
        });

        binding.tvSave.setOnClickListener(view -> {
            saveStudentData();
        });
        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void loadFromLocal(List<AddDropCoursesModel> selectedCoursesModels) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddDropAdapter(selectedCoursesModels, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void saveStudentData() {

    }

}