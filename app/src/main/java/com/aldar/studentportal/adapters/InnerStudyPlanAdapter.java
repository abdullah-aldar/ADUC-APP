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
    private String totalCreditHours,completedCreditHours;

    InnerStudyPlanAdapter(List<StudyPlanDataModel> nameList, String totalCH, String completedCH) {
        this.studyPlanDataList = nameList;
        this.totalCreditHours = totalCH;
        this.completedCreditHours = completedCH;
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

        holder.tvCourseCode.setText(studyPlanDataList.get(position).getCourseCode());
        holder.tvCourseName.setText(studyPlanDataList.get(position).getCourseName());
        holder.tvPreRequeste.setText(studyPlanDataList.get(position).getPreReq());
        holder.tvStatus.setText(studyPlanDataList.get(position).getStatus());
        holder.tvGrade.setText(studyPlanDataList.get(position).getMarks());

        holder.tvTotalCreditHour.setText("Total Credit Hours = "+totalCreditHours);
        holder.tvCompeledtCreditHours.setText("Passed Credit Hours = "+completedCreditHours);
    }

    @Override
    public int getItemCount() {
        return studyPlanDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode, tvCourseName, tvPreRequeste, tvGrade, tvStatus;
        TextView tvTotalCreditHour,tvCompeledtCreditHours;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvPreRequeste = itemView.findViewById(R.id.tv_prerequiste);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvCompeledtCreditHours = itemView.findViewById(R.id.tv_total_credit_hour);
            tvTotalCreditHour = itemView.findViewById(R.id.tv_completed_credit_hour);
        }
    }


}
