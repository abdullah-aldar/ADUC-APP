package com.aldar.studentportal.ui.fragments.navigations.campusLife;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FragmentCampusBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampusFragment extends Fragment implements View.OnClickListener {

    private CampusViewModel campusViewModel;
    private FragmentCampusBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_campus,container,false);

        campusViewModel = new ViewModelProvider(this).get(CampusViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCampusViewMode(campusViewModel);

        binding.tvStudentService.setOnClickListener(this);
        binding.tvIeee.setOnClickListener(this);
        binding.tvAboutUae.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_student_service:
                binding.ivHeader.setImageResource(R.drawable.campus_life);
                campusViewModel.setText(getActivity().getResources().getString(R.string.campus_life));
                break;
            case R.id.tv_ieee:
                binding.ivHeader.setImageResource(R.drawable.ieee);
                campusViewModel.setText(getActivity().getResources().getString(R.string.ieee_text));
                break;
            case R.id.tv_about_uae:
                binding.ivHeader.setImageResource(R.drawable.building);
                campusViewModel.setText(getActivity().getResources().getString(R.string.about_uae));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}