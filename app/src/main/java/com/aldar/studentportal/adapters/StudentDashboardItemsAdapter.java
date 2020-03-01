package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomDashboardLayoutBinding;
import com.aldar.studentportal.interfaces.ItemClickCallBack;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class StudentDashboardItemsAdapter extends RecyclerView.Adapter<StudentDashboardItemsAdapter.MyViewHolder> {

    private List<? extends DashboardItemModel> mItemList;

    @Nullable
    private ItemClickCallBack mItemClickCallback;

    public StudentDashboardItemsAdapter(@Nullable ItemClickCallBack mClickCallback) {
        mItemClickCallback = mClickCallback;
    }

    public void setProductList(final List<? extends DashboardItemModel> dashboardItemModels){
        if(mItemList == null){
           mItemList = dashboardItemModels;
        }
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomDashboardLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_dashboard_layout,
                        parent, false);
        binding.setCallback(mItemClickCallback);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DashboardItemModel model = mItemList.get(position);
        holder.binding.ivDashboard.setImageResource(model.getImageID());
        holder.binding.setItem(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getId();
    }


        static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomDashboardLayoutBinding binding;

        private MyViewHolder(CustomDashboardLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}