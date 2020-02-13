package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleAdapter extends RecyclerView.Adapter<CourseScheduleAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CourseScheduleDataModel> CourseScheduleDataModelList;

    public CourseScheduleAdapter(Context context, List<CourseScheduleDataModel> CourseScheduleDataModelList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.CourseScheduleDataModelList = CourseScheduleDataModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_courseshedule_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     CourseScheduleDataModel model = CourseScheduleDataModelList.get(position);

     holder.tvCourseCode.setText(model.getCourseCode());
     holder.tvCourseName.setText(model.getCourseName());
     holder.tvSection.setText(model.getSectionCode());
     holder.tvLecturerName.setText(model.getLecturer());
     holder.tvRoom.setText(model.getRoom());
     holder.tvMidExamDate.setText(model.getMidTermExamDate());
     holder.tvFinalExamDate.setText(model.getFinalExamDate());


    }

    @Override
    public int getItemCount() {
        return CourseScheduleDataModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode,tvCourseName,tvClass,tvLecturerName,tvSection,tvMidExamDate,tvFinalExamDate,tvRoom,tvDayTime;
        private MyViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvSection = itemView.findViewById(R.id.tv_section);
            tvLecturerName = itemView.findViewById(R.id.tv_lecture);
            tvRoom = itemView.findViewById(R.id.tv_room);
            tvMidExamDate = itemView.findViewById(R.id.tv_midterm_date);
            tvFinalExamDate = itemView.findViewById(R.id.tv_finalterm_date);
        }
    }
}