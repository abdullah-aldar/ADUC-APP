package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomLibraryLayoutBinding;
import com.aldar.studentportal.models.libraryModels.LibraryDataModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.MyViewHolder> {

    private List<? extends LibraryDataModel> mDataList;
    private Context context;

    public LibraryAdapter(FragmentActivity activity, List<? extends LibraryDataModel> LibraryDataModelList) {
        mDataList = LibraryDataModelList;
        this.context = activity;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomLibraryLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_library_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        LibraryDataModel model = mDataList.get(position);
        holder.binding.setLibraryDataModel(model);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomLibraryLayoutBinding binding;

        private MyViewHolder(CustomLibraryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}