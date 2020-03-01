package com.aldar.studentportal.ui.activities.common.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FAQAdapter;
import com.aldar.studentportal.databinding.ActivityFaqBinding;

public class FaqActivity extends AppCompatActivity {
    private ActivityFaqBinding binding;
    private FAQAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_faq);
        binding.setLifecycleOwner(this);

        initViews();
    }

    private void initViews() {
        FaqViewmodel faqViewmodel = new ViewModelProvider(this).get(FaqViewmodel.class);
        binding.setLifecycleOwner(this);
        binding.setFaqViewMode(faqViewmodel);

        faqViewmodel.getFaqData().observe(this, dashboardItemModels -> {
            if(dashboardItemModels != null){
                binding.rvFaq.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                FAQAdapter faqAdapter = new FAQAdapter(dashboardItemModels);
                binding.rvFaq.setAdapter(faqAdapter);
            }
        });


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        binding = null;
        adapter = null;
        super.onDestroy();
    }
}
