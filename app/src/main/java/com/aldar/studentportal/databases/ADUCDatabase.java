package com.aldar.studentportal.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ADUCDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "ADUC_DB";
    private static int DB_VERSION = 1;

    public ADUCDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String courseTable = "CREATE TABLE CART (ID INTEGER PRIMARY KEY AUTOINCREMENT,sectionId,sectionCode,courseCode,courseName,schedule,insName)";
        String timingTable = "CREATE TABLE TIMING (ID INTEGER PRIMARY KEY AUTOINCREMENT,courseCode,dayName,startTime,endTime)";

        sqLiteDatabase.execSQL(courseTable);
        sqLiteDatabase.execSQL(timingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CART");
        db.execSQL("DROP TABLE IF EXISTS TIMING");
        onCreate(db);
    }
}
