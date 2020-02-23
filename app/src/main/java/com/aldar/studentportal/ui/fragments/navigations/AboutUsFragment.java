package com.aldar.studentportal.ui.fragments.navigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CampusLifeViewPagerAdapter;
import com.aldar.studentportal.databinding.FragmentAboutUsBinding;
import com.aldar.studentportal.databinding.FragmentCampusBinding;
import com.aldar.studentportal.utilities.ZoomOutPageTransformer;

public class AboutUsFragment extends Fragment{
    private FragmentAboutUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false);
        binding.setLifecycleOwner(this);

        setTabLayout();

        return binding.getRoot();
    }


    private void setTabLayout() {
        binding.viewpagerAboutUs.setPageTransformer(true, new ZoomOutPageTransformer());
        binding.tabDots.setupWithViewPager(binding.viewpagerAboutUs, true);
        int[] layouts = new int[]{
                R.layout.mission_layout,
                R.layout.vision_layout};

        CampusLifeViewPagerAdapter sliderViewPagerAdapter = new CampusLifeViewPagerAdapter(layouts, getContext());
        binding.viewpagerAboutUs.setAdapter(sliderViewPagerAdapter);
    }


}