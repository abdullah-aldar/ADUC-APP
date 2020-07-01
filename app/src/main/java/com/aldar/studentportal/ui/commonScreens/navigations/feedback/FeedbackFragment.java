package com.aldar.studentportal.ui.commonScreens.navigations.feedback;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldar.studentportal.R;
import com.aldar.studentportal.databinding.FeedbackFragmentBinding;

public class FeedbackFragment extends Fragment {
    private FeedbackFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.feedback_fragment,container,false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FeedbackViewModel mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setFeedbackViewModel(mViewModel);

        mViewModel.getfeedBackData().observe(getViewLifecycleOwner(),commonApiResponse -> {
            if(commonApiResponse != null){
                Toast.makeText(getContext(), ""+commonApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnFeedback.setOnClickListener(v -> {
            mViewModel.stars.setValue(binding.rattingbar.getRating());
            mViewModel.onClick();
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
