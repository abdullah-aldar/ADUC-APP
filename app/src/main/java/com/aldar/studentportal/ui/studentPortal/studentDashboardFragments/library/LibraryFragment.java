package com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.library;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CustomSpinnerAdapter;
import com.aldar.studentportal.adapters.LibraryAdapter;
import com.aldar.studentportal.adapters.MarksAdapter;
import com.aldar.studentportal.databinding.LibraryFragmentBinding;

public class LibraryFragment extends Fragment {
    public LibraryFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.library_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LibraryViewModel mViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setLibraryViewModel(mViewModel);


        binding.spinnerBooks.setAdapter(new CustomSpinnerAdapter(getActivity(), R.layout.spinner_layout, getResources().getStringArray(R.array.books), "Choose"));
        binding.spinnerBooks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.searchBookBy.setValue(binding.spinnerBooks.getItemAtPosition(position).toString());
                binding.etBookTitle.setHint("Enter " + binding.spinnerBooks.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mViewModel.getLibraryData().observe(getViewLifecycleOwner(), libraryResponseModel -> {
            if (libraryResponseModel.getData() != null) {
                binding.rvLibrary.setLayoutManager(new LinearLayoutManager(getActivity()));
                LibraryAdapter adapter = new LibraryAdapter(getActivity(), libraryResponseModel.getData());
                binding.rvLibrary.setAdapter(adapter);
            }
        });

        binding.ivBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.btnSearchBooks.setOnClickListener(v -> {
            mViewModel.getLibraryBooks();
        });

        binding.btnDigitalLibray.setOnClickListener(v -> {
         getActivity().getSupportFragmentManager().
                 beginTransaction()
                 .replace(R.id.fragment_container,new DigitalLibraryFragment())
                 .addToBackStack("")
                 .commit();
        });
    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
