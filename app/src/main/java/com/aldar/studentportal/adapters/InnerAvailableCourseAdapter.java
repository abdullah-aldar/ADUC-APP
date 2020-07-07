package com.aldar.studentportal.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.coursesAdviceModels.CoursesDataModel;
import com.aldar.studentportal.models.coursesAdviceModels.Sections;
import com.aldar.studentportal.models.coursesAdviceModels.Time;
import com.aldar.studentportal.ui.studentPortal.courseAdvice.SelectedCoursesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InnerAvailableCourseAdapter extends RecyclerView.Adapter<InnerAvailableCourseAdapter.ViewHolder> {
    private Context context;
    private ADUCCrud aducCrud;
    private List<CoursesDataModel> coursesDataList;
    private List<Sections> sectionsList;
    private List<Time> timingModelList = new ArrayList<>();
    private String courseCode, courseName, strTiming = "";

    public InnerAvailableCourseAdapter(Context context, List<CoursesDataModel> coursesDataList, String courseCode, String courseName, List<Sections> nameList) {
        this.context = context;
        this.coursesDataList = coursesDataList;
        this.sectionsList = nameList;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_advice_layout, parent, false);
        InnerAvailableCourseAdapter.ViewHolder vh = new InnerAvailableCourseAdapter.ViewHolder(v);
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

        });


    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSectionCode, tvCourseName, tvCreditHours, tvRemark1, tvSchedule, tvInsName, tvAdd;

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
    private void checkTimingandData(int position, List<Sections> sectionsList, List<Time> timeList) {


        if (!aducCrud.checkTiming(courseCode, String.valueOf(sectionsList.get(position).getSectionId()), timeList)) {
            addCoursesToCard(sectionsList, position);
            //showDialog("Success", "Your course has been added successfull");
        } else {
            showDialog("Conflict", "You have already a course registered  in the same time slot");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addCoursesToCard(List<Sections> sectionsList, int position) {

        aducCrud.addCourseToCart(
                String.valueOf(sectionsList.get(position).getSectionId()),
                String.valueOf(sectionsList.get(position).getSectionCode()),
                String.valueOf(courseCode),
                String.valueOf(courseName),
                String.valueOf(sectionsList.get(position).getSchedule()),
                String.valueOf(sectionsList.get(position).getInsName())
        );
    }

    private void showDialog(String titlte, String message) {
        Dialog dialog = new Dialog(Objects.requireNonNull(context));
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.course_advice_dialog);
        TextView tvTitle = dialog.findViewById(R.id.title);
        TextView tvMessage = dialog.findViewById(R.id.message);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        Button btnReview = dialog.findViewById(R.id.btn_review);

        tvTitle.setText(titlte);
        tvMessage.setText(message);

        btnOk.setOnClickListener(v -> dialog.dismiss());

        btnReview.setOnClickListener(v -> {
            dialog.dismiss();
            context.startActivity(new Intent(context, SelectedCoursesActivity.class));
        });

        dialog.show();
    }
}
