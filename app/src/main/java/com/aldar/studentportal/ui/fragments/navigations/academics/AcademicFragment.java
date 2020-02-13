package com.aldar.studentportal.ui.fragments.navigations.academics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.aldar.studentportal.R;

public class AcademicFragment extends Fragment {
    private AcademicViewModel academicViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        academicViewModel =
                new ViewModelProvider(this).get(AcademicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        academicViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
        return root;
    }

}