package com.aldar.studentportal.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.aldar.studentportal.models.coursesAdviceModels.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean checkTiming(String courseCode, String sectionId, List<Time> timeList) {
        boolean checkTimeAndDAte = false;
        for (int k = 0; k < timeList.size(); k++) {
            String dayName = timeList.get(k).getDayName();
            String startTime = timeList.get(k).getStartTime();
            String endTime = timeList.get(k).getEndTime();
            checkTimeAndDAte = checkExistTiming(courseCode,sectionId, dayName, formatTime(startTime), formatTime(endTime));
        }

        return checkTimeAndDAte;
    }

    //check date and timing if exist
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean checkExistTiming(String courseCode,String sectionID, String dayName, String startTime, String endTime) {

        String query = "SELECT * FROM TIMING WHERE dayName = '" + dayName + "' AND startTime = '" + startTime + "' AND endTime = '" + endTime + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            isTimingAddedCart = true;

        } else {
            if (!checkExistTime(courseCode,sectionID)) {
                ContentValues values = new ContentValues();
                values.put("courseCode", courseCode);
                values.put("sectionID", sectionID);
                values.put("dayName", dayName);
                values.put("startTime", startTime);
                values.put("endTime", endTime);
                sqLiteDatabase.insert("TIMING", null, values);
            } else {
                ContentValues values = new ContentValues();
                values.put("courseCode", courseCode);
                values.put("sectionID", sectionID);
                values.put("dayName", dayName);
                values.put("startTime", startTime);
                values.put("endTime", endTime);
                sqLiteDatabase.insert("TIMING", null, values);
            }
            isTimingAddedCart = false;

        }

        return isTimingAddedCart;
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


    //fetching all courses
    public Cursor getAllCourses() {
        String query = "SELECT * FROM CART";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
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

    private boolean checkExistTime(String courseCode,String sectionID) {

        String query = "SELECT * FROM TIMING WHERE courseCode = '" + courseCode + "' ";
        Cursor cursor = this.sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            this.sqLiteDatabase.delete("TIMING", "courseCode = '" + courseCode + "' AND sectionID != '" + sectionID + "'", null);
            return  true;
        }
        else {
            return  false;
        }

    }

    private void insertTime(String courseCode,String sectionID,String dayName,String startTime,String endTime){
        ContentValues values = new ContentValues();
        values.put("courseCode", courseCode);
        values.put("sectionID", sectionID);
        values.put("dayName", dayName);
        values.put("startTime", startTime);
        values.put("endTime", endTime);
        sqLiteDatabase.insert("TIMING", null, values);
    }

    private void showToast(String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
    }

    private boolean comparingTimings(String myStartTime, String myEndTime, String savedStartTime, String savedEndtime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date myStartDate = sdf.parse(myStartTime);
            Date myEndDate = sdf.parse(myEndTime);

            Date savevdStartDate = sdf.parse(savedStartTime);
            Date savevdEndDate = sdf.parse(savedEndtime);

            if (myStartDate.after(savevdStartDate) && myStartDate.before(savevdEndDate) ||
                    myStartDate.equals(savevdStartDate) && myEndDate.before(savevdEndDate)) {
                Toast.makeText(context, "time matched", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(context, "time not matched", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String formatTime(String strTiming) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(strTiming, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        return dateTime.format(formatter2);
    }
}
