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
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleResponseModel;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleAdapter extends RecyclerView.Adapter<CourseScheduleAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CourseScheduleResponseModel> CourseScheduleResponseModelList;

    public CourseScheduleAdapter(Context context, List<CourseScheduleResponseModel> CourseScheduleResponseModelList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.CourseScheduleResponseModelList = CourseScheduleResponseModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_courseshedule_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     CourseScheduleResponseModel model = CourseScheduleResponseModelList.get(position);

    }

    @Override
    public int getItemCount() {
        return CourseScheduleResponseModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode,tvCourseName,tvClass,tvLecturerName,tvSection,tvMidExamDate,tvFinalExamDate,tvRoom,tvDayTime;
        private MyViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
        }
    }
}