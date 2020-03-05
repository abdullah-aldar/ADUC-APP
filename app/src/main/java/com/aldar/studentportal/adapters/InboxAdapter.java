package com.aldar.studentportal.adapters;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomInboxLayoutBinding;
import com.aldar.studentportal.models.inboxModels.StudentInboxDataModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> {

    private List<? extends StudentInboxDataModel> mDataList;

    public InboxAdapter(List<? extends StudentInboxDataModel> courseScheduleDataModelList) {
        mDataList = courseScheduleDataModelList;
    }

    @NotNull
    @Override
    public InboxAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomInboxLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_inbox_layout,
                        parent, false);
        return new InboxAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final InboxAdapter.MyViewHolder holder, final int position) {
        StudentInboxDataModel model = mDataList.get(position);
        holder.binding.setInboxDataModel(model);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomInboxLayoutBinding binding;

        private MyViewHolder(CustomInboxLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}