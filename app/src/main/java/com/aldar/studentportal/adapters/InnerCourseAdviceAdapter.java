package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databases.ADUCCrud;
import com.aldar.studentportal.models.coursesAdviceModels.CoursesDataModel;

import java.util.List;

public class InnerCourseAdviceAdapter extends RecyclerView.Adapter<InnerCourseAdviceAdapter.ViewHolder> {
    private Context context;
    private ADUCCrud aducCrud;
    private List<CoursesDataModel> marksDataList;

    public InnerCourseAdviceAdapter(Context context,List<CoursesDataModel> nameList) {
        this.context = context;
        this.marksDataList = nameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_advice_layout, parent, false);


        InnerCourseAdviceAdapter.ViewHolder vh = new InnerCourseAdviceAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        aducCrud = new ADUCCrud(context);
        holder.tvSectionCode.setText(String.valueOf(marksDataList.get(position).getSectionId()));
        holder.tvCourseName.setText(marksDataList.get(position).getCourseName());
        holder.tvCreditHours.setText(String.valueOf(marksDataList.get(position).getCreditHours()));
        holder.tvRemark1.setText(marksDataList.get(position).getRemark1());
        holder.tvSchedule.setText(marksDataList.get(position).getSchedule());
        holder.tvInsName.setText(marksDataList.get(position).getInsName());

        holder.tvAdd.setOnClickListener(v -> {
          aducCrud.addCourseToCart(
                  String.valueOf(marksDataList.get(position).getSectionId()),
                  String.valueOf(marksDataList.get(position).getSectionCode()),
                  String.valueOf(marksDataList.get(position).getCourseId()),
                  String.valueOf(marksDataList.get(position).getCourseCode()),
                  String.valueOf(marksDataList.get(position).getCourseName()),
                  String.valueOf(marksDataList.get(position).getCreditHours()),
                  String.valueOf(marksDataList.get(position).getSchedule()),
                  String.valueOf(marksDataList.get(position).getInsName())
          );
        });

    }

    @Override
    public int getItemCount() {
        return marksDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvSectionCode, tvCourseName,tvCreditHours, tvRemark1, tvSchedule, tvInsName,tvAdd;

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


}
