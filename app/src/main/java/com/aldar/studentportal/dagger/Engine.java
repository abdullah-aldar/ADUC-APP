package com.aldar.studentportal.dagger;

import com.aldar.studentportal.models.newDataModels.NewsDataModel;

import javax.inject.Inject;

public class Engine {

    private NewsDataModel newsDataModel;

    @Inject
    public Engine(NewsDataModel newsDataModel) {
        this.newsDataModel = newsDataModel;
    }
}
