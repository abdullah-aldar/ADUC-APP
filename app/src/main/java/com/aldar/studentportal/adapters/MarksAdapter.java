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
import com.aldar.studentportal.models.studyplan.StudyPlanYearly;

import java.util.ArrayList;
import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;

    private List<MarksSemesterModel> marksSemesterModelList;

    ArrayList<Integer> counter = new ArrayList<Integer>();

    public MarksAdapter(FragmentActivity activity, List<MarksSemesterModel> data) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.marksSemesterModelList = data;


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
        MarksAdapter.MyViewHolder vh = new MarksAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MarksSemesterModel model = marksSemesterModelList.get(position);
        holder.tvSemester.setText(model.getSemester());

        InnerMarksAdapter itemInnerRecyclerView = new InnerMarksAdapter(marksSemesterModelList.get(position).getCourses());
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
        return marksSemesterModelList.size();
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