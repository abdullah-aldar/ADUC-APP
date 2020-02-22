package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.letterRequest.LetterRequestFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.library.LibraryFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseAdvice.MyCourseAdviceFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myCourseSchedule.CourseScheduleFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myFinance.MyFinanceFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myMarks.MyMarksFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myProfile.StudentProfileFragment;
import com.aldar.studentportal.ui.fragments.studentDashboardFragments.myStudyPlan.MyStudyPlanFragment;
import com.aldar.studentportal.utilities.GeneralUtilities;
import com.aldar.studentportal.utilities.OtherUtils;

import java.util.List;

public class StudentDashboardItemsAdapter extends RecyclerView.Adapter<StudentDashboardItemsAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private List<DashboardItemModel> dashboardItemList;
    private Context context;

    public StudentDashboardItemsAdapter(Context context, List<DashboardItemModel> dashboardItemList) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.dashboardItemList = dashboardItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_dashboard_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DashboardItemModel model = dashboardItemList.get(position);
        holder.ivDashboard.setImageResource(model.getImageID());
        holder.tvTitle.setText(model.getTitle());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScreen(context, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivDashboard;
        ConstraintLayout itemLayout;

        private MyViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            ivDashboard = itemView.findViewById(R.id.iv_dashboard);
            tvTitle = itemView.findViewById(R.id.title);
        }
    }

    private void goToScreen(Context context, int position) {
        switch (position) {
            case 0:
                GeneralUtilities.connectFragmentWithBack(context, new CourseScheduleFragment());
                break;
            case 1:
                GeneralUtilities.connectFragmentWithBack(context, new MyMarksFragment());
                break;
            case 2:
                GeneralUtilities.connectFragmentWithBack(context, new MyStudyPlanFragment());
                break;
            case 3:
                GeneralUtilities.connectFragmentWithBack(context, new MyCourseAdviceFragment());
                break;
            case 4:
                GeneralUtilities.connectFragmentWithBack(context, new LetterRequestFragment());
                break;
            case 5:
                GeneralUtilities.connectFragmentWithBack(context, new MyFinanceFragment());
                break;
            case 6:
                GeneralUtilities.connectFragmentWithBack(context, new StudentProfileFragment());
                break;
            case 7:
                break;
            case 8:
                OtherUtils.googleClassRoom(context);
                break;
            case 9:
                GeneralUtilities.connectFragmentWithBack(context, new LibraryFragment());
                break;
        }
    }
}