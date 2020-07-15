package com.aldar.studentportal.ui.commonScreens.navigations.faq;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FAQAdapter;
import com.aldar.studentportal.databinding.FragmentFaqBinding;


public class FaqFragment extends Fragment {
    private FragmentFaqBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_faq,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        FaqViewmodel faqViewmodel = new ViewModelProvider(this).get(FaqViewmodel.class);
        binding.setLifecycleOwner(this);
        binding.setFaqViewMode(faqViewmodel);

        faqViewmodel.getFaqData().observe(getViewLifecycleOwner(), dashboardItemModels -> {
            if(dashboardItemModels != null){
                binding.rvFaq.setLayoutManager(new LinearLayoutManager(getActivity()));
                FAQAdapter faqAdapter = new FAQAdapter(dashboardItemModels);
                binding.rvFaq.setAdapter(faqAdapter);
            }
        });

    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}