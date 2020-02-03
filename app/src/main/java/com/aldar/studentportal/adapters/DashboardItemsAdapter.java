package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;

import java.util.List;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemsAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private List<DashboardItemModel> dashboardItemList;
    private Context context;

    public DashboardItemsAdapter(Context context, List<DashboardItemModel> dashboardItemList) {

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
    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivDashboard;
        private MyViewHolder(View itemView) {
            super(itemView);
             ivDashboard = itemView.findViewById(R.id.iv_dashboard);
             tvTitle = itemView.findViewById(R.id.title);
        }
    }
}