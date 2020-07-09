package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.databinding.CustomCoursesFeeLayoutBinding;
import com.aldar.studentportal.models.feeCalculation.CoursesFeeModel;

import java.util.List;

public class FeeCalCourseAdapter extends RecyclerView.Adapter<FeeCalCourseAdapter.MyViewHolder> {
    private List<? extends CoursesFeeModel> SelectedCoursesModelList;
    private ADUCCrud aducCrud;


    public FeeCalCourseAdapter(List<CoursesFeeModel> SelectedCoursesModelList) {
        this.SelectedCoursesModelList = SelectedCoursesModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCoursesFeeLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_courses_fee_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CoursesFeeModel model = SelectedCoursesModelList.get(position);
        holder.binding.setCoursesFeeModel(model);
        
    }

    @Override
    public int getItemCount() {
        return SelectedCoursesModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomCoursesFeeLayoutBinding binding;

        private MyViewHolder(CustomCoursesFeeLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
