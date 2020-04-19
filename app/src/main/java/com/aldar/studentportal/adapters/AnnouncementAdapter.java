package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomAnnouncementLayoutBinding;
import com.aldar.studentportal.databinding.CustomDigitalLayoutBinding;
import com.aldar.studentportal.models.announcementModel.AnnouncementDataModel;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    private List<AnnouncementDataModel> AnnouncementDataModelList;

    public AnnouncementAdapter(List<AnnouncementDataModel> AnnouncementDataModelList) {
        this.AnnouncementDataModelList = AnnouncementDataModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomAnnouncementLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_announcement_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        AnnouncementDataModel model = AnnouncementDataModelList.get(position);
        holder.binding.setAnnoucementDataModel(model);



    }

    @Override
    public int getItemCount() {
        return AnnouncementDataModelList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomAnnouncementLayoutBinding binding;

        private MyViewHolder(CustomAnnouncementLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}