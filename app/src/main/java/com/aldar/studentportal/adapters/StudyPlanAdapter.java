package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;

import java.util.List;

public class StudyPlanAdapter extends RecyclerView.Adapter<StudyPlanAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CourseScheduleDataModel> CourseScheduleDataModelList;

    public StudyPlanAdapter(Context context, List<CourseScheduleDataModel> CourseScheduleDataModelList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.CourseScheduleDataModelList = CourseScheduleDataModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_studyplan_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     CourseScheduleDataModel model = CourseScheduleDataModelList.get(position);

     holder.tvCourseCode.setText(model.getCourseCode());
     holder.tvCourseName.setText(model.getCourseName());
     holder.tvPreRequeste.setText(model.getCourseCode());




    }

    @Override
    public int getItemCount() {
        return CourseScheduleDataModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode,tvCourseName,tvPreRequeste,tvGrade,tvStatus;
        private MyViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvPreRequeste = itemView.findViewById(R.id.tv_prerequiste);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvStatus = itemView.findViewById(R.id.tv_status);

        }
    }
}