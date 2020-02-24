package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FAQAdapter;
import com.aldar.studentportal.databinding.ActivityFaqBinding;
import com.aldar.studentportal.models.faqModels.FAQModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqActivity extends AppCompatActivity {
    private ActivityFaqBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_faq);
        binding.setLifecycleOwner(this);
        this.getSupportActionBar().hide();

        initViews();
    }

    private void initViews() {
        binding.rvFaq.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FAQAdapter faqAdapter = new FAQAdapter(getApplicationContext(), preparedFAQItems());
        binding.rvFaq.setAdapter(faqAdapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private List<FAQModel> preparedFAQItems() {
        List<FAQModel> faqModelList = new ArrayList<>();
        String[] questionsList = getResources().getStringArray(R.array.faq_question);
        String[] answerList = getResources().getStringArray(R.array.faq_answer);

        int count = 0;
        for (String titleName : questionsList) {
            faqModelList.add(new FAQModel(titleName, answerList[count]));
            count++;
        }

        return faqModelList;

    }
}
