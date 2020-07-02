package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomNotificationLayoutBinding;
import com.aldar.studentportal.models.notificationModels.NotificationDataModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationDataModel> NotificationList;

    public NotificationAdapter(List<NotificationDataModel> NotificationList) {
        this.NotificationList = NotificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomNotificationLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_notification_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        NotificationDataModel model = NotificationList.get(position);
        holder.binding.setNotificationDataModel(model);

    }

    @Override
    public int getItemCount() {
        return NotificationList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomNotificationLayoutBinding binding;

        private MyViewHolder(CustomNotificationLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}