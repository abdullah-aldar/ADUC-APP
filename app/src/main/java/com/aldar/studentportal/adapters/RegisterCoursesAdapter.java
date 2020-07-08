package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomDropLayoutBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.addAndDropModel.Sections;
import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesData;
import com.aldar.studentportal.models.addAndDropModel.StudentRegisteredCoursesResponse;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RegisterCoursesAdapter extends RecyclerView.Adapter<RegisterCoursesAdapter.MyViewHolder> {

    private List<? extends Sections> mDataList;
    ArrayList<Integer> counter = new ArrayList<Integer>();
    private Context context;

    public RegisterCoursesAdapter(Context context, List<Sections> GradeConversionDataList) {
        this.context = context;
        mDataList = GradeConversionDataList;

        for (int i = 0; i < mDataList.size(); i++) {
            counter.add(i);
        }
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_marks_layout, parent, false);
        RegisterCoursesAdapter.MyViewHolder vh = new RegisterCoursesAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Sections model = mDataList.get(position);

        holder.tvSemester.setText(model.getSemester());
        InnerCourseRegisterAdapter itemInnerRecyclerView = new InnerCourseRegisterAdapter(context,mDataList.get(position).getCourses());
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        holder.cardView.setOnClickListener(view -> {

            if (counter.get(position) % 2 == 0) {
                holder.cardRecyclerView.setVisibility(View.VISIBLE);
                holder.ivExpand.setImageResource(R.drawable.expand_less);
            } else {
                holder.cardRecyclerView.setVisibility(View.GONE);
                holder.ivExpand.setImageResource(R.drawable.expand_more);
            }

            counter.set(position, counter.get(position) + 1);


        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
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

    private String setListSectionIDs(ArrayList<String> listSectionIDs) {
        String strSectionIDs = "";
        for (int i = 0; i < listSectionIDs.size(); i++) {
            if (i == 0) {
                strSectionIDs += listSectionIDs.get(i);
            } else {
                strSectionIDs += "," + listSectionIDs.get(i);
            }
        }
        return strSectionIDs;
    }
}