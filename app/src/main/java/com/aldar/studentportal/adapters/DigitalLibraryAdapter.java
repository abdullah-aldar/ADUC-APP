package com.aldar.studentportal.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomDigitalLayoutBinding;
import com.aldar.studentportal.interfaces.ItemClickCallBack;
import com.aldar.studentportal.models.libraryModels.DigitalLibraryDataModel;
import com.aldar.studentportal.ui.activities.common.WebActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DigitalLibraryAdapter extends RecyclerView.Adapter<DigitalLibraryAdapter.MyViewHolder> {

    private List<? extends DigitalLibraryDataModel> mItemList;
    private Context context;

    public DigitalLibraryAdapter(FragmentActivity activity, List<DigitalLibraryDataModel> data) {
        this.mItemList = data;
        this.context = activity;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomDigitalLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_digital_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DigitalLibraryDataModel model = mItemList.get(position);
        holder.binding.setDigitalLibraryModel(model);

        if(model.getPassword().equals("")){
            holder.binding.pasword.setVisibility(View.INVISIBLE);
        }

        holder.binding.layout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("link", model.getResourceLink());
            context.startActivity(new Intent(context, WebActivity.class).putExtras(bundle));
        });



    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return mItemList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomDigitalLayoutBinding binding;

        private MyViewHolder(CustomDigitalLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}