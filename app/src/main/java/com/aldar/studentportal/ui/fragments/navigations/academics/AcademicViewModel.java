package com.aldar.studentportal.ui.fragments.navigations.academics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.R;

public class AcademicViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> mImage;

    public AcademicViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Integer> getImage(){
        return mImage;
    }
}
