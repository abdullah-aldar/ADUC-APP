package com.aldar.studentportal.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesData;

import java.util.List;

public class InnerCourseRegisterAdapter extends RecyclerView.Adapter<InnerCourseRegisterAdapter.ViewHolder> {
    private ADUCCrud aducCrud;
    private List<StudentRegisteredCoursesData> coursesDataList;
    private Context context;

    public InnerCourseRegisterAdapter(Context context, List<StudentRegisteredCoursesData> coursesDataList) {
        this.context = context;
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
        aducCrud = new ADUCCrud(context);

        String courseCode = coursesDataList.get(position).getCourseCode();
        String courseName = coursesDataList.get(position).getCourseName();
        String sectionId = String.valueOf(coursesDataList.get(position).getSectionId());
        String sections = coursesDataList.get(position).getSectionCode();
        String creditHour = String.valueOf(coursesDataList.get(position).getCh());
        String invoice = coursesDataList.get(position).getInvoiceCode();

        holder.tvCourseCode.setText(courseCode);
        holder.tvCourseName.setText(courseName);
        holder.tvSection.setText(sections);

        holder.tvAdd.setOnClickListener(v -> {
            String sectionCode = coursesDataList.get(position).getSectionCode();
            aducCrud.saveAddDrop(courseCode, courseName,sectionId, sections, creditHour,invoice, "Drop");
        });


    }

    @Override
    public int getItemCount() {
        return coursesDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseCode, tvCourseName, tvSection, tvAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tv_courseCode);
            tvCourseName = itemView.findViewById(R.id.tv_courseName);
            tvSection = itemView.findViewById(R.id.tv_section);
            tvAdd = itemView.findViewById(R.id.tv_add);
        }
    }
}
