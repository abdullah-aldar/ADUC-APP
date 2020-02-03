package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aldar.studentportal.R;
import com.aldar.studentportal.adapters.DashboardItemsAdapter;
import com.aldar.studentportal.adapters.FAQAdapter;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.models.faqModels.FAQModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqActivity extends AppCompatActivity {
    @BindView(R.id.rv_faq)
    RecyclerView rvFAQ;
    private FAQAdapter faqAdapter;
    private List<FAQModel> faqModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        this.setTitle(getResources().getString(R.string.faq_question));
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        rvFAQ.setLayoutManager(new LinearLayoutManager(this));
        faqAdapter = new FAQAdapter(this, preparedFAQItems());
        rvFAQ.setAdapter(faqAdapter);

    }


    private List<FAQModel> preparedFAQItems() {
        faqModelList = new ArrayList<>();
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
