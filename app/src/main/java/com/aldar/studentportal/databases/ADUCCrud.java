package com.aldar.studentportal.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ADUCCrud {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ADUCCrud(Context context) {
        ADUCDatabase database = new ADUCDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }

    public void addCourseToCart(String sectionId, String sectionCode, String courseCode, String courseName, String schedule, String insName, String timing) {
        if (!checkExistData(courseCode, timing)) {
            ContentValues values = new ContentValues();
            values.put("sectionId", sectionId);
            values.put("sectionCode", sectionCode);
            values.put("courseCode", courseCode);
            values.put("courseName", courseName);
            values.put("schedule", schedule);
            values.put("insName", insName);
            values.put("timing", timing);
            sqLiteDatabase.insert("CART", null, values);
            showToast("Your course saved successfully");
        } else {
            updateCourse(sectionId, sectionCode, courseCode, courseName, schedule, insName, timing);
        }
    }


    //fetching all data
    public Cursor getAllCourses() {
        String query = "SELECT * FROM CART";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //deleting course
    public boolean deleteCourse(String courseCode) {
        this.sqLiteDatabase.delete("CART", "courseCode= '" + courseCode + "'", null);
        showToast("course deleted successful");
        return true;
    }

    //check course if exist
    private boolean checkExistData(String courseCode, String timing) {
        boolean isItemAddChart = false;
        String query = "SELECT * FROM CART WHERE courseCode = '" + courseCode + "' ";
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }

    //updaing course
    private void updateCourse(String sectionId, String sectionCode, String courseCode, String courseName,
                              String schedule, String insName, String timing) {
        ContentValues values = new ContentValues();
        values.put("sectionId", sectionId);
        values.put("sectionCode", sectionCode);
        values.put("courseCode", courseCode);
        values.put("courseName", courseName);
        values.put("schedule", schedule);
        values.put("insName", insName);
        values.put("timing", timing);
        sqLiteDatabase.update("CART", values, "courseCode= '" + courseCode + "'", null);
        Toast.makeText(context, "course updated", Toast.LENGTH_SHORT).show();
    }

    private void showToast(String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
    }

}
