package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.studyplan.StudyPlanDataModel;
import com.aldar.studentportal.models.studyplan.StudyPlanDataModel;

import java.util.ArrayList;
import java.util.List;

public class InnerStudyPlanAdapter extends RecyclerView.Adapter<InnerStudyPlanAdapter.ViewHolder> {
    private List<StudyPlanDataModel> studyPlanDataList;

    InnerStudyPlanAdapter(List<StudyPlanDataModel> nameList) {
        this.studyPlanDataList = nameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_studyplan_layout, parent, false);

        InnerStudyPlanAdapter.ViewHolder vh = new InnerStudyPlanAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.tvSemester.setText(studyPlanDataList.get(position).getTypeDesc());
        holder.tvCourseCode.setText(studyPlanDataList.get(position).getCourseCode());
        holder.tvCourseName.setText(studyPlanDataList.get(position).getCourseName());
        holder.tvPreRequeste.setText(studyPlanDataList.get(position).getPreReq());
        holder.tvStatus.setText(studyPlanDataList.get(position).getStatus());
        holder.tvGrade.setText(studyPlanDataList.get(position).getMarks());
    }

    @Override
    public int getItemCount() {
        return studyPlanDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvCourseCode, tvCourseName, tvPreRequeste, tvGrade, tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSemester = itemView.findViewById(R.id.tv_semester);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvPreRequeste = itemView.findViewById(R.id.tv_prerequiste);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }


}
