package com.aldar.studentportal.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.aldar.studentportal.models.coursesAdviceModels.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ADUCCrud {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    boolean isTimingAddedCart = false;

    public ADUCCrud(Context context) {
        ADUCDatabase database = new ADUCDatabase(context);
        sqLiteDatabase = database.getWritableDatabase();
        this.context = context;
    }

    public void addCourseToCart(String sectionId, String sectionCode, String courseCode, String courseName, String schedule, String insName) {
        if (!checkExistData(courseCode)) {
            ContentValues values = new ContentValues();
            values.put("sectionId", sectionId);
            values.put("sectionCode", sectionCode);
            values.put("courseCode", courseCode);
            values.put("courseName", courseName);
            values.put("schedule", schedule);
            values.put("insName", insName);
            sqLiteDatabase.insert("CART", null, values);
            showToast("Your course saved successfully");
        } else {
            updateCourse(sectionId, sectionCode, courseCode, courseName, schedule, insName);
        }
    }

    public boolean checkTiming(String courseCode, List<Time> timeList) {
        boolean checkTimeAndDAte = false;
        for (int k = 0; k < timeList.size(); k++) {
            String dayName = timeList.get(k).getDayName();
            String startTime = timeList.get(k).getStartTime();
            String endTime = timeList.get(k).getEndTime();
            checkTimeAndDAte = checkExistTiming(courseCode,dayName, startTime, endTime);
        }

        return checkTimeAndDAte;
    }


    //deleting course
    public boolean deleteCourse(String courseCode) {
        this.sqLiteDatabase.delete("CART", "courseCode= '" + courseCode + "'", null);
        this.sqLiteDatabase.delete("TIMING", "courseCode= '" + courseCode + "'", null);
        showToast("course deleted successful");
        return true;
    }

    //check course in a section if exist then it will update it
    private boolean checkExistData(String courseCode) {
        boolean isItemAddChart = false;
        String query = "SELECT * FROM CART WHERE courseCode = '" + courseCode + "' ";
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            isItemAddChart = true;
        }
        return isItemAddChart;

    }

    //check date and timing if exist
    private boolean checkExistTiming(String courseCode, String dayName, String startTime, String endTime) {
        String query = "SELECT * FROM TIMING WHERE dayName = '" + dayName + "' AND startTime = '" + startTime + "' AND endTime = '" + endTime + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            isTimingAddedCart = true;
            Toast.makeText(context, "exist", Toast.LENGTH_SHORT).show();


        } else {
            isTimingAddedCart = false;
            showToast("not exist");
            addTiming(isTimingAddedCart, courseCode,dayName, startTime, endTime);
        }

        return isTimingAddedCart;
    }

    private void addTiming(boolean isTimingAddedCart,String courseCode, String dayName, String startTime, String endTime) {
        if (!isTimingAddedCart) {
            ContentValues values = new ContentValues();
            values.put("courseCode", courseCode);
            values.put("dayName", dayName);
            values.put("startTime", startTime);
            values.put("endTime", endTime);
            sqLiteDatabase.insert("TIMING", null, values);
            showToast("Checking Time and Date conflict");
        } else {
            showToast("need updation");
        }
    }

    //updaing course
    private void updateCourse(String sectionId, String sectionCode, String courseCode, String courseName,
                              String schedule, String insName) {
        ContentValues values = new ContentValues();
        values.put("sectionId", sectionId);
        values.put("sectionCode", sectionCode);
        values.put("courseCode", courseCode);
        values.put("courseName", courseName);
        values.put("schedule", schedule);
        values.put("insName", insName);
        sqLiteDatabase.update("CART", values, "courseCode= '" + courseCode + "'", null);
        showToast("course updated");
    }


    //fetching all data
    public Cursor getAllCourses() {
        String query = "SELECT * FROM CART";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    private void showToast(String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
    }

    private boolean comparingTimings(String time, String endtime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.equals(date2)) {
                showToast("true");
                return true;
            } else {
                showToast("false");
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}
