package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomCoursesheduleLayoutBinding;
import com.aldar.studentportal.databinding.CustomDashboardLayoutBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleAdapter extends RecyclerView.Adapter<CourseScheduleAdapter.MyViewHolder> {

    private List<? extends CourseScheduleDataModel> mDataList;

    public CourseScheduleAdapter(List<? extends CourseScheduleDataModel> courseScheduleDataModelList) {
        mDataList = courseScheduleDataModelList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomCoursesheduleLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_courseshedule_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     CourseScheduleDataModel model = mDataList.get(position);
     holder.binding.setCouseSheduleModel(model);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomCoursesheduleLayoutBinding binding;

        private MyViewHolder(CustomCoursesheduleLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}