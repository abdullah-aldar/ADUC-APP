package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.courseScheduleModels.CourseScheduleDataModel;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CourseScheduleDataModel> CourseScheduleDataModelList;

    public AnnouncementAdapter(Context context, List<CourseScheduleDataModel> CourseScheduleDataModelList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.CourseScheduleDataModelList = CourseScheduleDataModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_announcement_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CourseScheduleDataModel model = CourseScheduleDataModelList.get(position);
        holder.tvAnnoucement.setText(model.getCourseName());


    }

    @Override
    public int getItemCount() {
        return CourseScheduleDataModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnnoucement;

        private MyViewHolder(View itemView) {
            super(itemView);
            tvAnnoucement = itemView.findViewById(R.id.tv_announcement);

        }
    }
}