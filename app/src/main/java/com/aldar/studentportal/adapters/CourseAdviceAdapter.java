package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.coursesAdviceModels.Time;
import com.aldar.studentportal.models.coursesAdviceModels.CoursesDataModel;


import java.util.ArrayList;
import java.util.List;

public class CourseAdviceAdapter extends RecyclerView.Adapter<CourseAdviceAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CoursesDataModel> coursesDataList;
    ArrayList<Integer> counterList = new ArrayList<Integer>();

    public CourseAdviceAdapter(FragmentActivity activity, List<CoursesDataModel> data) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.coursesDataList = data;

        for (int i = 0; i < data.size(); i++) {
            counterList.add(i);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_marks_layout, parent, false);
        CourseAdviceAdapter.MyViewHolder vh = new CourseAdviceAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CoursesDataModel model = coursesDataList.get(position);
        holder.tvSemester.setText(model.getCourseName());

        InnerCourseAdviceAdapter itemInnerRecyclerView = new InnerCourseAdviceAdapter(
                context,
                coursesDataList,
                model.getCourseCode(),
                model.getCourseName(),
                coursesDataList.get(position).getSections());

        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.cardView.setOnClickListener(view -> {
            if (counterList.get(position) % 2 == 0) {
                holder.cardRecyclerView.setVisibility(View.VISIBLE);
                holder.ivExpand.setImageResource(R.drawable.expand_less);

            } else {
                holder.cardRecyclerView.setVisibility(View.GONE);
                holder.ivExpand.setImageResource(R.drawable.expand_more);
            }

            counterList.set(position, counterList.get(position) + 1);


        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return coursesDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester;
        RecyclerView cardRecyclerView;
        CardView cardView;
        ImageView ivExpand;

        private MyViewHolder(View itemView) {
            super(itemView);
            ivExpand = itemView.findViewById(R.id.iv_expand);
            tvSemester = itemView.findViewById(R.id.categoryTitle);
            cardRecyclerView = itemView.findViewById(R.id.rv_innermarks);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}