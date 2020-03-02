package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.interfaces.StudyPlanInterface;
import com.aldar.studentportal.models.mymarksmodels.MarksDataModel;
import com.aldar.studentportal.models.mymarksmodels.MarksSemesterModel;

import java.util.ArrayList;
import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;

    private List<MarksSemesterModel> MarksSemesterModelList;
    private List<MarksDataModel> coursesList;

    ArrayList<Integer> counter = new ArrayList<Integer>();

    public MarksAdapter(FragmentActivity activity, List<MarksSemesterModel> data, List<MarksDataModel> courses) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.MarksSemesterModelList = data;
        this.coursesList = courses;

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
        View view = inflater.inflate(R.layout.custom_inner_studyplan_layout, parent, false);
        MarksAdapter.MyViewHolder vh = new MarksAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MarksSemesterModel model = MarksSemesterModelList.get(position);


        holder.tvSemester.setText(model.getSemester());

        InnerMarksAdapter itemInnerRecyclerView = new InnerMarksAdapter(MarksSemesterModelList.get(position).getCourses());
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter.get(position) % 2 == 0) {
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.cardRecyclerView.setVisibility(View.GONE);
                }

                counter.set(position, counter.get(position) + 1);


            }
        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return MarksSemesterModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester;
        RecyclerView cardRecyclerView;
        CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            tvSemester = itemView.findViewById(R.id.categoryTitle);
            cardRecyclerView = itemView.findViewById(R.id.innerRecyclerView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}