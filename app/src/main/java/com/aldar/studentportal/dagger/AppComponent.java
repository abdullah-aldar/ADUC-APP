package com.aldar.studentportal.dagger;

import com.aldar.studentportal.models.newDataModels.NewsDataModel;

import javax.inject.Singleton;

import dagger.Component;

interface AppComponent {

    Engine getEngin();
}
