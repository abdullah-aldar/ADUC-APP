package com.aldar.studentportal.ui.fragments.navigations.campusLife;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aldar.studentportal.R;

public class CampusViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;

    public CampusViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue(application.getResources().getString(R.string.campus_life));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String value){
        mText.setValue(value);
    }

}
