package com.aldar.studentportal.models.dashboardItemModels;

public class DashboardItemModel {
    private String title;
    private int imageID;

    public DashboardItemModel(String title, int imageID) {
        this.title = title;
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
