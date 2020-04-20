package com.aldar.studentportal.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomChequeLayoutBinding;
import com.aldar.studentportal.interfaces.CallBackAmount;
import com.aldar.studentportal.models.chequesModels.ChequeDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChequesAdapter extends RecyclerView.Adapter<ChequesAdapter.MyViewHolder> {
    private CallBackAmount callBackAmount;
    private List<? extends ChequeDataModel> mItemList;

    public ChequesAdapter(List<ChequeDataModel> data,CallBackAmount callBackAmount) {
        this.mItemList = data;
        this.callBackAmount = callBackAmount;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomChequeLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_cheque_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ChequeDataModel model = mItemList.get(position);
        holder.binding.setChequesDataModel(model);

        holder.binding.btnAddAmount.setOnClickListener(v -> {
          callBackAmount.onGetCreditHour(model.getAmount(),model.getTransId());
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
        CustomChequeLayoutBinding binding;

        private MyViewHolder(CustomChequeLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}