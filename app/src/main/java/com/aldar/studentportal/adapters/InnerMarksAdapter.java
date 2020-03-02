package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.mymarksmodels.MarksDataModel;

import java.util.ArrayList;
import java.util.List;

public class InnerMarksAdapter extends RecyclerView.Adapter<InnerMarksAdapter.ViewHolder> {
    public List<MarksDataModel> studyPlanDataList = new ArrayList<MarksDataModel>();

    public InnerMarksAdapter(List<MarksDataModel> nameList) {
        this.studyPlanDataList = nameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_studyplan_layout, parent, false);


        InnerMarksAdapter.ViewHolder vh = new InnerMarksAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.tvCourseCode.setText(studyPlanDataList.get(0).getCourseCode());
        holder.tvCourseName.setText(studyPlanDataList.get(0).getCourseName());
        holder.tvPreRequeste.setText(studyPlanDataList.get(0).getCourseCode());
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
