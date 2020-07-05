package com.aldar.studentportal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.CustomConversionLayoutBinding;
import com.aldar.studentportal.interfaces.SectionIDInterface;
import com.aldar.studentportal.models.gradeConversionModel.GradeConversionData;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;


public class GradeConversionAdapter extends RecyclerView.Adapter<GradeConversionAdapter.MyViewHolder> {

    private List<? extends GradeConversionData> mDataList;
    private SectionIDInterface sectionIDListener;
    ArrayList<String> listSectionIDs = new ArrayList<>();

    public GradeConversionAdapter(List<? extends GradeConversionData> GradeConversionDataList, SectionIDInterface sectionIDListener) {
        mDataList = GradeConversionDataList;
        this.sectionIDListener = sectionIDListener;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomConversionLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_conversion_layout,
                        parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        GradeConversionData model = mDataList.get(position);
        holder.binding.setConversionModel(model);

        holder.binding.checkSection.setTag(position);
        holder.binding.checkSection.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(holder.binding.checkSection.isChecked()){
                listSectionIDs.add(String.valueOf(model.getSectionID()));
                String sectionIDs = setListSectionIDs(listSectionIDs);
                sectionIDListener.setSectionID(sectionIDs);
            }
            else {
                listSectionIDs.remove(String.valueOf(model.getSectionID()));
                String sectionIDs = setListSectionIDs(listSectionIDs);
                sectionIDListener.setSectionID(sectionIDs);
            }

        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CustomConversionLayoutBinding binding;

        private MyViewHolder(CustomConversionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private String setListSectionIDs(ArrayList<String> listSectionIDs) {
        String strSectionIDs = "";
        for (int i = 0; i < listSectionIDs.size(); i++) {
            if (i == 0) {
                strSectionIDs += listSectionIDs.get(i);
            } else {
                strSectionIDs += "," + listSectionIDs.get(i);
            }
        }
        return strSectionIDs;
    }
}