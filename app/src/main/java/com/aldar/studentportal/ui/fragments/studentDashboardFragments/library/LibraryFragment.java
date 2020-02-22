package com.aldar.studentportal.ui.fragments.studentDashboardFragments.library;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.databinding.LibraryFragmentBinding;

public class LibraryFragment extends Fragment {
    public LibraryFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.library_fragment,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LibraryViewModel mViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());


        binding.spinnerBooks.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, getResources().getStringArray(R.array.books), "Choose"));

        binding.spinnerBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.etBookTitle.setHint("Enter "+binding.spinnerBooks.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
