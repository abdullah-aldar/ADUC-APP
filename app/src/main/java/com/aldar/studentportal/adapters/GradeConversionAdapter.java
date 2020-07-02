package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomConversionLayoutBinding;
import com.aldar.studentportal.databinding.CustomCoursesheduleLayoutBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GradeConversionAdapter extends RecyclerView.Adapter<GradeConversionAdapter.MyViewHolder> {

    private List<? extends CourseScheduleDataModel> mDataList;

    public GradeConversionAdapter(List<? extends CourseScheduleDataModel> courseScheduleDataModelList) {
        mDataList = courseScheduleDataModelList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomConversionLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_conversion_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CourseScheduleDataModel model = mDataList.get(position);
        holder.binding.setConversionModel(model);

        String[] strings = model.getSectionCode().split("-");
        //holder.binding.tvSection.setText(strings[1]);

        holder.binding.checkSection.setOnCheckedChangeListener((compoundButton, b) -> {

        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomConversionLayoutBinding binding;

        private MyViewHolder(CustomConversionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}