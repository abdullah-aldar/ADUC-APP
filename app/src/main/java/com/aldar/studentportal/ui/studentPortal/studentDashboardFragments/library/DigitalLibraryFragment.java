package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.library;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.DigitalLibraryAdapter;
import com.aldar.studentportal.adapters.LibraryAdapter;
import com.aldar.studentportal.databinding.DigitalLibraryFragmentBinding;

public class DigitalLibraryFragment extends Fragment {

    private DigitalLibraryViewModel viewModel;
    private DigitalLibraryFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.digital_library_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DigitalLibraryViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setDigitalLibraryViewModel(viewModel);

        viewModel.getDigitalLibraryData().observe(getViewLifecycleOwner(),digitalLibraryResponseModel -> {
            if(digitalLibraryResponseModel.getData() != null){
                binding.rvDigitalLibrary.setLayoutManager(new LinearLayoutManager(getActivity()));
                DigitalLibraryAdapter adapter = new DigitalLibraryAdapter(getActivity(), digitalLibraryResponseModel.getData());
                binding.rvDigitalLibrary.setAdapter(adapter);
            }
        });

        binding.ivBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }

}
