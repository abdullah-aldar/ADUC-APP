package com.aldar.studentportal.dagger;

import android.app.Application;

import dagger.internal.DaggerCollections;

class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

      //  appComponent = createMyComponent();
    }

    private AppComponent createMyComponent() {
        return null;
    }

    AppComponent getAppComponent(){
        return appComponent;
    }
}
