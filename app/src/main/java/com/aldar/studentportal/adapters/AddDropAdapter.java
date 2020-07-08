package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.databinding.CustomConversionLayoutBinding;
import com.aldar.studentportal.databinding.CustomDropLayoutBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.addAndDropModel.AddDropCoursesModel;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionData;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AddDropAdapter extends RecyclerView.Adapter<AddDropAdapter.MyViewHolder> {
    private Context context;
    private List<? extends AddDropCoursesModel> selectedCoursesModelList;
    private ADUCCrud dictionaryCrud;

    public AddDropAdapter(List<? extends AddDropCoursesModel> SelectedCoursesModelList, Context context) {
        this.context = context;
        this.selectedCoursesModelList = SelectedCoursesModelList;

    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomDropLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_drop_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        AddDropCoursesModel model = selectedCoursesModelList.get(position);
        holder.binding.setRegisterCoursesModel(model);


    }

    @Override
    public int getItemCount() {
        return selectedCoursesModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomDropLayoutBinding binding;

        private MyViewHolder(CustomDropLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}