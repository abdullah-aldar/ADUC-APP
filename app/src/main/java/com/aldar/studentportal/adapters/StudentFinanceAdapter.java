package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomCoursesheduleLayoutBinding;
import com.aldar.studentportal.databinding.CustomFinanceLayoutBinding;
import com.aldar.studentportal.models.financeModel.FinanceDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentFinanceAdapter extends RecyclerView.Adapter<StudentFinanceAdapter.MyViewHolder> {

    private List<? extends FinanceDataModel> mDataList;

    public  StudentFinanceAdapter(List<? extends FinanceDataModel> FinanceDataModelList) {
        mDataList = FinanceDataModelList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomFinanceLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_finance_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        FinanceDataModel model = mDataList.get(position);
        holder.binding.setFinanceModel(model);

        holder.tvCount.setText("#"+String.valueOf(position+1));

        String amount = model.getAmount();
        amount = amount.replace("-","");
        holder.tvBalance.setText(amount);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomFinanceLayoutBinding binding;
        TextView tvCount,tvBalance;

        private MyViewHolder(CustomFinanceLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            tvCount = binding.getRoot().findViewById(R.id.tv_count);
            tvBalance = binding.getRoot().findViewById(R.id.tv_balance);
        }

    }
}