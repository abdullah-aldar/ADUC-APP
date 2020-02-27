package com.aldar.studentportal.models.dashboardItemModels;

public class DashboardItemModel {
    private int countID;
    private String title;
    private int imageID;

    public DashboardItemModel(int countID,String title, int imageID) {
        this.countID = countID;
        this.title = title;
        this.imageID = imageID;
    }

    public int getId() {
        return countID;
    }

    public void setId(int countID) {
        this.countID = countID;
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
