package com.aldar.studentportal.ui.fragments.navigations.academics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CampusLifeViewPagerAdapter;
import com.aldar.studentportal.databinding.FragmentAcademicBinding;
import com.aldar.studentportal.utilities.ZoomOutPageTransformer;

public class AcademicFragment extends Fragment {
    private FragmentAcademicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_academic,container,false);

        setTabLayout();

        return binding.getRoot();
    }

    private void setTabLayout() {
        binding.viewpageAcademics.setPageTransformer(true, new ZoomOutPageTransformer());
        binding.tabDots.setupWithViewPager(binding.viewpageAcademics, true);
        int[] layouts = new int[]{
                R.layout.engineering_school_layout,
                R.layout.business_layout,
                R.layout.arts_layout};

        CampusLifeViewPagerAdapter sliderViewPagerAdapter = new CampusLifeViewPagerAdapter(layouts, getContext());
        binding.viewpageAcademics.setAdapter(sliderViewPagerAdapter);
    }


}