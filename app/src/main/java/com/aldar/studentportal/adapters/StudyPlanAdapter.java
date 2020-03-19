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
import com.aldar.studentportal.models.mymarksmodels.MarksSemesterModel;
import com.aldar.studentportal.models.studyplan.StudyPlanDataModel;
import com.aldar.studentportal.models.studyplan.StudyPlanResponseModel;
import com.aldar.studentportal.models.studyplan.StudyPlanYearly;
import com.aldar.studentportal.models.studyplan.StudyPlanYearly;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanAdapter extends RecyclerView.Adapter<StudyPlanAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;

    private List<StudyPlanYearly> studyPlanYearlyList;

    private StudyPlanInterface studyPlanInterface;
    ArrayList<Integer> counter = new ArrayList<Integer>();

    public StudyPlanAdapter(FragmentActivity activity, List<StudyPlanYearly> semester,  StudyPlanInterface studyPlanInterface) {
        this.context = activity;
        inflater = LayoutInflater.from(context);
        this.studyPlanYearlyList = semester;

        this.studyPlanInterface = studyPlanInterface;

        for (int i = 0; i < semester.size(); i++) {
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
        StudyPlanAdapter.MyViewHolder vh = new StudyPlanAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        StudyPlanYearly model = studyPlanYearlyList.get(position);
        holder.tvSemester.setText(model.getYearLevel());
        holder.tvTotalCreditHour.setText("Total Credit Hours = "+String.valueOf(model.getTotalCH()));
        holder.tvCompletedCreditHours.setText("Passed Credit Hours = "+String.valueOf(model.getCompTotalCH()));

        InnerStudyPlanAdapter itemInnerRecyclerView = new InnerStudyPlanAdapter(studyPlanYearlyList.get(position).getCourses());
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

        studyPlanInterface.onGetCreditHour(String.valueOf(model.getTotalCH()), String.valueOf(model.getCompTotalCH()));
    }

    @Override
    public int getItemCount() {
        return studyPlanYearlyList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester,tvTotalCreditHour,tvCompletedCreditHours;
        RecyclerView cardRecyclerView;
        CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);
            tvSemester = itemView.findViewById(R.id.categoryTitle);
            tvTotalCreditHour = itemView.findViewById(R.id.tv_total_creditshours);
            tvCompletedCreditHours = itemView.findViewById(R.id.tv_completed_credithour);
            cardRecyclerView = itemView.findViewById(R.id.innerRecyclerView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}