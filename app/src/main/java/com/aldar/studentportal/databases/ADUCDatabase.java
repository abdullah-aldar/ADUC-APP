package com.aldar.studentportal.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ADUCDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "ADUC_DB";
    private static int DB_VERSION = 6;

    public ADUCDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE CART (ID INTEGER PRIMARY KEY AUTOINCREMENT,sectionId,sectionCode,courseCode,courseName,schedule,insName,timing)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CART");
        onCreate(db);
    }
}
