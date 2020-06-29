package com.aldar.studentportal.ui.activities.common.faq;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.faqModels.FAQModel;

import java.util.ArrayList;
import java.util.List;

public class FaqViewmodel extends AndroidViewModel {

    public MutableLiveData<String> course = new MutableLiveData<>();

    private MutableLiveData<List<FAQModel>> mutableLiveData = new MutableLiveData<>();

    public FaqViewmodel(@NonNull Application application) {
        super(application);
        mutableLiveData.setValue(faqModelList());
    }

    public MutableLiveData<List<FAQModel>> getFaqData(){
        return mutableLiveData;
    }

    private List<FAQModel> faqModelList() {
        List<FAQModel> faqList = new ArrayList<>();
        String[] questionsArray = getApplication().getApplicationContext().getResources().getStringArray(R.array.faq_question);
        String[] answersArray = getApplication().getApplicationContext().getResources().getStringArray(R.array.faq_answer);

        int count = 0;
        for (String questions : questionsArray) {
            faqList.add(new FAQModel(questions,answersArray[count]));
            count++;
        }

        return faqList;

    }
}
