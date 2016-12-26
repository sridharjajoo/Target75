package com.example.sridh.target75.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sridh on 24-12-2016.
 */


public class AttendanceDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="attendance.db";
    private static final int DATABSE_VERSION =1;

    public AttendanceDBHelper(Context context){

          super(context,DATABASE_NAME,null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
final String SQL_CREATE_STRING="CREATE TABLE " + AttendanceContract.AttendanceEntry.TABLE_NAME + " ("
                                              + AttendanceContract.AttendanceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_SUBJECT + " TEXT,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_ABSENT +" INTEGER,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_PRESENT +" INTEGER,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_MONDAY + " BOOLEAN,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_TUESDAY +  " BOOLEAN,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_WEDNESDAY + " BOOLEAN,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_THURSDAY + " BOOLEAN,"
                                              + AttendanceContract.AttendanceEntry.COLUMN_FRIDAY + " BOOLEAN "+ ");";
 sqLiteDatabase.execSQL(SQL_CREATE_STRING);
        Log.v("","databasr +" + SQL_CREATE_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ AttendanceContract.AttendanceEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
