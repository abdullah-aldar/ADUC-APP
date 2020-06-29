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
    public List<MarksDataModel> marksDataList;

    public InnerMarksAdapter(List<MarksDataModel> nameList) {
        this.marksDataList = nameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_student_marks_layout, parent, false);


        InnerMarksAdapter.ViewHolder vh = new InnerMarksAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvSemester.setText(marksDataList.get(position).getSemester());
        holder.tvCourseCode.setText(marksDataList.get(position).getCourseCode());
        holder.tvCourseName.setText(marksDataList.get(position).getCourseName());
        holder.tvCourseWork.setText(marksDataList.get(position).getCourseWork());
        holder.tvFinal.setText(marksDataList.get(position).getFinalExam());
        holder.tvGrade.setText(marksDataList.get(position).getLetterGrade());
        holder.tvTotal.setText(String.valueOf(marksDataList.get(position).getTotalPercentage()));

    }

    @Override
    public int getItemCount() {
        return marksDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvCourseCode, tvCourseName,tvFinal, tvCourseWork, tvGrade, tvTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSemester = itemView.findViewById(R.id.tv_semester);
            tvCourseCode = itemView.findViewById(R.id.tv_course_code);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCourseWork = itemView.findViewById(R.id.tv_course_work);
            tvFinal = itemView.findViewById(R.id.tv_final_exam);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvTotal = itemView.findViewById(R.id.tv_total);
        }
    }


}
