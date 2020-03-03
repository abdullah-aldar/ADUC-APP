package com.aldar.studentportal.ui.fragments.navigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.CampusLifeViewPagerAdapter;
import com.aldar.studentportal.databinding.FragmentCampusBinding;
import com.aldar.studentportal.utilities.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampusFragment extends Fragment{
    private FragmentCampusBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_campus, container, false);
        binding.setLifecycleOwner(this);


        setTabLayout();


        return binding.getRoot();
    }


    private void setTabLayout() {
        binding.viewpageCampusLife.setPageTransformer(true, new ZoomOutPageTransformer());
        binding.tabDots.setupWithViewPager(binding.viewpageCampusLife, true);
        int[] layouts = new int[]{
                R.layout.student_services_layout,
                R.layout.ieee_layout,
                R.layout.about_uae_layout};

        CampusLifeViewPagerAdapter sliderViewPagerAdapter = new CampusLifeViewPagerAdapter(layouts, getContext());
        binding.viewpageCampusLife.setAdapter(sliderViewPagerAdapter);
    }


}