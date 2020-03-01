package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.digitalLibrary;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldar.studentportal.R;

public class DigitalLibraryFragment extends Fragment {

    private DigitalLibraryViewModel mViewModel;

    public static DigitalLibraryFragment newInstance() {
        return new DigitalLibraryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.digital_library_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DigitalLibraryViewModel.class);
        // TODO: Use the ViewModel
    }

}
