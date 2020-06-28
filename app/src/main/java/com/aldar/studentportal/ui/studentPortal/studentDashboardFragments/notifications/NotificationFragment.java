package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.AnnouncementAdapter;
import com.aldar.studentportal.adapters.NotificationAdapter;
import com.aldar.studentportal.databinding.AnnouncementFragmentBinding;
import com.aldar.studentportal.databinding.FragmentNotificationBinding;
import com.aldar.studentportal.models.libraryModels.DigitalLibraryResponseModel;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.announcment.AnnouncementViewModel;

public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_notification, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NotificationViewModel mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setNotificationViewModel(mViewModel);

        binding.rvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewModel.getNotificationData().observe(getViewLifecycleOwner(), announcementReponseModel -> {

            if(announcementReponseModel.getData() != null){
                binding.rvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
                NotificationAdapter adapter = new NotificationAdapter(announcementReponseModel.getData());
                binding.rvNotification.setAdapter(adapter);

            }
        });

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());
    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}