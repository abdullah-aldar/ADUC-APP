package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.announcment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AnnouncementAdapter;
import com.aldar.studentportal.databinding.AnnouncementFragmentBinding;

public class AnnouncementFragment extends Fragment {
   private AnnouncementFragmentBinding binding;
   private AnnouncementAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.announcement_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnnouncementViewModel mViewModel = new ViewModelProvider(this).get(AnnouncementViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setAnnouncementViewModel(mViewModel);

        binding.rvAnnouncement.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewModel.getAnnouncementData().observe(getViewLifecycleOwner(),announcementReponseModel -> {
            if(announcementReponseModel.getData() != null){
                binding.rvAnnouncement.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new AnnouncementAdapter(announcementReponseModel.getData());
                binding.rvAnnouncement.setAdapter(adapter);

            }
        });

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
    }


    @Override
    public void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }
}
