package com.aldar.studentportal.utilities;

import com.aldar.studentportal.models.announcementModel.AnnouncementDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainObjectClass {

    private JSONObject jsonObject;

    public MainObjectClass(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
