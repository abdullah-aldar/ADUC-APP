package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.databinding.SelectedCoursesLayoutBinding;
import com.aldar.studentportal.models.selectedCoursesModel.SelectedCoursesModel;

import java.util.List;

public class SelectedCoursesAdapter extends RecyclerView.Adapter<SelectedCoursesAdapter.MyViewHolder> {
    private Context context;
    private List<? extends SelectedCoursesModel> SelectedCoursesModelList;
    private ADUCCrud dictionaryCrud;

    public SelectedCoursesAdapter(List<? extends SelectedCoursesModel> SelectedCoursesModelList, Context context) {
        this.context = context;
        this.SelectedCoursesModelList = SelectedCoursesModelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SelectedCoursesLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.selected_courses_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        dictionaryCrud = new ADUCCrud(context);
        SelectedCoursesModel model = SelectedCoursesModelList.get(position);
        holder.binding.setSelectModel(model);

        holder.binding.tvRemoveCourse.setOnClickListener(v -> {
            dictionaryCrud.deleteCourse(model.getCourseCode());
            SelectedCoursesModelList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return SelectedCoursesModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        SelectedCoursesLayoutBinding binding;

        private MyViewHolder(SelectedCoursesLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
