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
import com.aldar.studentportal.databinding.CustomFaqLayoutBinding;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;
import com.aldar.studentportal.models.faqModels.FAQModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {

    private List<? extends FAQModel> mDataList;
    private ArrayList<String> alIndexPosition = new ArrayList<>();

    public FAQAdapter(List<? extends FAQModel> courseScheduleDataModelList) {
        mDataList = courseScheduleDataModelList;
    }

    @NotNull
    @Override
    public FAQAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomFaqLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_faq_layout,
                        parent, false);
        return new FAQAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final FAQAdapter.MyViewHolder holder, final int position) {
        FAQModel model = mDataList.get(position);
        holder.binding.setFaqModel(model);

        holder.binding.questionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alIndexPosition.contains(String.valueOf(position))) {
                    holder.binding.ivArrow.setImageResource(R.drawable.down);
                    holder.binding.tvFaqAnswer.setVisibility(View.GONE);
                    alIndexPosition.remove(String.valueOf(position));
                } else {
                    alIndexPosition.add(String.valueOf(position));
                    holder.binding.ivArrow.setImageResource(R.drawable.up);
                    holder.binding.tvFaqAnswer.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomFaqLayoutBinding binding;

        private MyViewHolder(CustomFaqLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}