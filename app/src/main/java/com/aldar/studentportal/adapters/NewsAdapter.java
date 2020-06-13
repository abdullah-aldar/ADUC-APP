package com.aldar.studentportal.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomNewsLayoutBinding;

import com.aldar.studentportal.models.newDataModels.NewsDataModel;
import com.aldar.studentportal.ui.activities.common.NewsActivity;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<? extends NewsDataModel> mDataList;
    private Context context;

    public NewsAdapter(List<? extends NewsDataModel> courseScheduleDataModelList) {
        mDataList = courseScheduleDataModelList;
    }

    @NotNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomNewsLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_news_layout,
                        parent, false);
        this.context = parent.getContext();
        return new NewsAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {
        Collections.reverse(mDataList);
        NewsDataModel model = mDataList.get(position);

        Glide.with(context).load(model.getImg()).into(holder.binding.ivNews);
        holder.binding.setNewDataModel(model);

        holder.binding.layout.setOnClickListener(v -> {
            context.startActivity(new Intent(context, NewsActivity.class));
          //  showDialog(model.getImg(),model.getHeader(),model.getBody());
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomNewsLayoutBinding binding;

        private MyViewHolder(CustomNewsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    private void showDialog(String image,String title,String detail){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.news_dialog);
        ImageView ivNews = dialog.findViewById(R.id.iv_news);
        TextView tvTitle = dialog.findViewById(R.id.tv_news_title);
        TextView tvDetail = dialog.findViewById(R.id.tv_new_detail);
        ImageView ivCancel = dialog.findViewById(R.id.iv_cancel);

        Glide.with(context).load(image).into(ivNews);
        tvTitle.setText(title);
        tvDetail.setText(detail);

        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}