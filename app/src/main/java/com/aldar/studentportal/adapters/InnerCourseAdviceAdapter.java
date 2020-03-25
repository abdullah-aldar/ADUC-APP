package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.coursesAdviceModels.CoursesDataModel;

import java.util.List;

public class InnerCourseAdviceAdapter extends RecyclerView.Adapter<InnerCourseAdviceAdapter.ViewHolder> {
    public List<CoursesDataModel> marksDataList;

    public InnerCourseAdviceAdapter(List<CoursesDataModel> nameList) {
        this.marksDataList = nameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_advice_layout, parent, false);


        InnerCourseAdviceAdapter.ViewHolder vh = new InnerCourseAdviceAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvSectionCode.setText(String.valueOf(marksDataList.get(position).getSectionId()));
        holder.tvCourseName.setText(marksDataList.get(position).getCourseName());
        holder.tvCreditHours.setText(String.valueOf(marksDataList.get(position).getCreditHours()));
        holder.tvRemark1.setText(marksDataList.get(position).getRemark1());
        holder.tvSchedule.setText(marksDataList.get(position).getSchedule());
        holder.tvInsName.setText(marksDataList.get(position).getInsName());

    }

    @Override
    public int getItemCount() {
        return marksDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvSectionCode, tvCourseName,tvCreditHours, tvRemark1, tvSchedule, tvInsName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSectionCode = itemView.findViewById(R.id.tv_sectionCode);
            tvCourseName = itemView.findViewById(R.id.tv_courseName);
            tvCreditHours = itemView.findViewById(R.id.tv_creditHours);
            tvRemark1 = itemView.findViewById(R.id.tv_remark1);
            tvSchedule = itemView.findViewById(R.id.tv_schedule);
            tvInsName = itemView.findViewById(R.id.tv_insName);
        }
    }


}
