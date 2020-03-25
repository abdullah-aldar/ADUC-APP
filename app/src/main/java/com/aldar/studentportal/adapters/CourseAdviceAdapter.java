package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.coursesAdviceModels.CoursesModel;


import java.util.ArrayList;
import java.util.List;

public class CourseAdviceAdapter extends RecyclerView.Adapter<CourseAdviceAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<CoursesModel> CoursesModelList;
    ArrayList<Integer> counter = new ArrayList<Integer>();

    public CourseAdviceAdapter(FragmentActivity activity, List<CoursesModel> data) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.CoursesModelList = data;

        for (int i = 0; i < data.size(); i++) {
            counter.add(0);
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
        CoursesModel model = CoursesModelList.get(position);
        holder.tvSemester.setText(model.getCourseName());

        InnerCourseAdviceAdapter itemInnerRecyclerView = new InnerCourseAdviceAdapter(CoursesModelList.get(position).getSections());
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.cardView.setOnClickListener(view -> {

            if (counter.get(position) % 2 == 0) {
                holder.cardRecyclerView.setVisibility(View.VISIBLE);
            } else {
                holder.cardRecyclerView.setVisibility(View.GONE);
            }

            counter.set(position, counter.get(position) + 1);


        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return CoursesModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester;
        RecyclerView cardRecyclerView;
        CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            tvSemester = itemView.findViewById(R.id.categoryTitle);
            cardRecyclerView = itemView.findViewById(R.id.rv_innermarks);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}