package com.aldar.studentportal.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesData;

import java.util.List;

public class InnerCourseRegisterAdapter extends RecyclerView.Adapter<InnerCourseRegisterAdapter.ViewHolder> {

    private List<StudentRegisteredCoursesData> coursesDataList;

    public InnerCourseRegisterAdapter(List<StudentRegisteredCoursesData> coursesDataList) {
        this.coursesDataList = coursesDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_registered_layout, parent, false);
        InnerCourseRegisterAdapter.ViewHolder vh = new InnerCourseRegisterAdapter.ViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvCourseCode.setText(coursesDataList.get(position).getCourseCode());
        holder.tvCourseName.setText(coursesDataList.get(position).getCourseName());
        holder.tvSection.setText(coursesDataList.get(position).getSectionCode());

        holder.tvAdd.setOnClickListener(v -> {
           // checkTimingandData(position, sectionsList, sectionsList.get(position).getTime());
        });


    }

    @Override
    public int getItemCount() {
        return coursesDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode, tvCourseName,  tvSection, tvAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_courseCode);
            tvCourseName = itemView.findViewById(R.id.tv_courseName);
            tvSection = itemView.findViewById(R.id.tv_section);
            tvAdd = itemView.findViewById(R.id.tv_add);
        }
    }
}
