package com.aldar.studentportal.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.coursesAdviceModels.Time;
import com.aldar.studentportal.models.coursesAdviceModels.Sections;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InnerCourseAdviceAdapter extends RecyclerView.Adapter<InnerCourseAdviceAdapter.ViewHolder> {
    private Context context;
    private ADUCCrud aducCrud;
    private List<Sections> sectionsList;
    private List<Time> timingModelList = new ArrayList<>();
    private String courseCode, courseName, strTiming = "";

    public InnerCourseAdviceAdapter(Context context, String courseCode, String courseName, List<Sections> nameList) {
        this.context = context;
        this.sectionsList = nameList;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_advice_layout, parent, false);
        InnerCourseAdviceAdapter.ViewHolder vh = new InnerCourseAdviceAdapter.ViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        aducCrud = new ADUCCrud(context);

        holder.tvSectionCode.setText(String.valueOf(sectionsList.get(position).getSectionId()));
        holder.tvCourseName.setText(courseName);
        holder.tvSchedule.setText(sectionsList.get(position).getSchedule());
        holder.tvInsName.setText(sectionsList.get(position).getInsName());

        holder.tvAdd.setOnClickListener(v -> {
            timingModelList.clear();
            addCoursesToCard(timingModelList, sectionsList, position);
        });


    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvSectionCode, tvCourseName, tvCreditHours, tvRemark1, tvSchedule, tvInsName, tvAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSectionCode = itemView.findViewById(R.id.tv_sectionCode);
            tvCourseName = itemView.findViewById(R.id.tv_courseName);
            tvCreditHours = itemView.findViewById(R.id.tv_creditHours);
            tvRemark1 = itemView.findViewById(R.id.tv_remark1);
            tvSchedule = itemView.findViewById(R.id.tv_schedule);
            tvInsName = itemView.findViewById(R.id.tv_insName);
            tvAdd = itemView.findViewById(R.id.tv_add);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addCoursesToCard(List<Time> timingModelList, List<Sections> sectionsList, int position) {
        timingModelList.addAll(sectionsList.get(position).getTime());
        for (int i = 0; i < timingModelList.size(); i++) {
            if (i == 0) {
                strTiming += formatTime(timingModelList.get(i).getStartTime());
            } else {
                strTiming += "," + formatTime(timingModelList.get(i).getStartTime());
            }
        }


        aducCrud.addCourseToCart(
                String.valueOf(sectionsList.get(position).getSectionId()),
                String.valueOf(sectionsList.get(position).getSectionCode()),
                String.valueOf(courseCode),
                String.valueOf(courseName),
                String.valueOf(sectionsList.get(position).getSchedule()),
                String.valueOf(sectionsList.get(position).getInsName()),
                strTiming
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String formatTime(String strTiming) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(strTiming, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        return dateTime.format(formatter2);
    }

}
