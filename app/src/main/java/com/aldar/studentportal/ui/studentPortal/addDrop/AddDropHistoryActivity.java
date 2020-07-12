package com.aldar.studentportal.ui.studentPortal.addDrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AddDropAdapter;
import com.aldar.studentportal.databinding.ActivityAddDropHistoryBinding;
import com.aldar.studentportal.models.addAndDropModel.AddDropCoursesModel;
import com.aldar.studentportal.utilities.SharedPreferencesManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class AddDropHistoryActivity extends AppCompatActivity {

    private ActivityAddDropHistoryBinding binding;
    private JsonObject jsonObject = null;

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
           if(jsonObject != null){
            viewModel.apiCallSaveAddDrop(jsonObject);
           }
        });
        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void loadFromLocal(List<AddDropCoursesModel> selectedCoursesModels) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddDropAdapter adapter = new AddDropAdapter(selectedCoursesModels, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        saveStudentData(selectedCoursesModels);
    }

    private void saveStudentData(List<AddDropCoursesModel> addDropCoursesList) {

        String strStudentId = String.valueOf(SharedPreferencesManager.getInstance(getApplication().getApplicationContext()).getIntValue("student_id"));
        String strSemID = String.valueOf(SharedPreferencesManager.getInstance(this).getIntValue("semester_id"));

        //creating post request body for saving add and drop courses
        jsonObject = new JsonObject();
        try {
            JsonArray list = new JsonArray();
            jsonObject.addProperty("StudentId", strStudentId);
            jsonObject.addProperty("SemId", strSemID);

            for (int i = 0; i < addDropCoursesList.size(); i++) {
                JsonObject internalObject = new JsonObject();
                internalObject.addProperty("SectionId", addDropCoursesList.get(i).getSectionId());
                internalObject.addProperty("AddorDrop", addDropCoursesList.get(i).getAddOrDrop());
                internalObject.addProperty("InvoiceCode", addDropCoursesList.get(i).getInvoice());
                list.add(internalObject);
            }
            jsonObject.add("studentAddDropDet", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}