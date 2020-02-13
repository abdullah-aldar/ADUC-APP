package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.FAQAdapter;
import com.aldar.studentportal.models.faqModels.FAQModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqActivity extends AppCompatActivity {
    @BindView(R.id.rv_faq)
    RecyclerView rvFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        this.setTitle(getResources().getString(R.string.faq_question));

        initViews();
    }

    private void initViews() {
        rvFAQ.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FAQAdapter faqAdapter = new FAQAdapter(getApplicationContext(), preparedFAQItems());
        rvFAQ.setAdapter(faqAdapter);

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
