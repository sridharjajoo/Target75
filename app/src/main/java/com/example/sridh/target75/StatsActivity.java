package com.example.sridh.target75;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sridh.target75.database.AttendanceContract;
import com.example.sridh.target75.database.AttendanceDBHelper;

import static android.R.attr.id;

public class StatsActivity extends AppCompatActivity {

    private TextView textView,absent_count,present_count,attendance_percent;
    private Cursor cursor;
    private AttendanceDBHelper dbHelper,dbHelper1;
    private SQLiteDatabase dbs;
    private SharedPreferences sharedPreferences;
    private Integer i,position;
    private ImageView cross,tick;
    private int flag,present,absent;
    private ContentValues values;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        flag=-1;
        textView = (TextView) findViewById(R.id.subject);
        cross = (ImageView) findViewById(R.id.cross);
        tick = (ImageView) findViewById(R.id.tick);
        absent_count = (TextView) findViewById(R.id.absent_count);
        present_count = (TextView) findViewById(R.id.present_count);
        values = new ContentValues();
        dbHelper = new AttendanceDBHelper(this);
//        dbHelper1 = new AttendanceDBHelper(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        attendance_percent = (TextView) findViewById(R.id.percent_Attendance);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        dbs = dbHelper.getReadableDatabase();

        cursor = dbs.rawQuery("SELECT * FROM " + AttendanceContract.AttendanceEntry.TABLE_NAME, null);

        position = sharedPreferences.getInt("gridview_item", -1);
        cursor.moveToPosition(position);
Log.v("StatsActivity","Position is " + position);
        textView.setText(cursor.getString(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_SUBJECT)));

        int present1= cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_PRESENT));
        int absent1 = cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_ABSENT));


        present_count.setText("" + cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_PRESENT)));
        absent_count.setText("" + cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_ABSENT)));

        if(present1==0&&absent1==0){
            progressBar.setProgress(0);
            attendance_percent.setText("" + 0 + "%");
        }
        else{
            progressBar.setProgress(present1*100/(present1+absent1));
            attendance_percent.setText(""+present1*100/(present1+absent1)+" %");
        }

         cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                crossortick();
            }
        });

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            flag=1;
                crossortick();
                }
        });
    }

    public void crossortick(){
        if(flag==0){

            Log.v("StatsActivity","Inside cross");
            dbs = dbHelper.getWritableDatabase();
            Log.v("StatsActivity","Inside cross" + dbs);
            absent = cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_ABSENT));
            Log.v("StatsActivity","Inside cross " + absent);

            absent=absent+1;
            Log.v("StatsActivity","Inside cross" + absent);

            values.put(AttendanceContract.AttendanceEntry.COLUMN_ABSENT,absent);
            Log.v("StatsActivity","Inside cross " + values);

            dbs.update(AttendanceContract.AttendanceEntry.TABLE_NAME,values,"_id ="+cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry._ID)),null);
            Log.v("StatsActivity","Inside cross "+cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry._ID)));

            absent_count.setText("" +cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_ABSENT)));
            flag=-1;
            finish();

        }
        else if(flag==1)
        {
            dbs = dbHelper.getWritableDatabase();
            present = cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_PRESENT));
            present=present+1;
            values.put(AttendanceContract.AttendanceEntry.COLUMN_PRESENT,present);
            dbs.update(AttendanceContract.AttendanceEntry.TABLE_NAME,values,"_id ="+cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry._ID) ),null);

            present_count.setText("" +cursor.getInt(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_PRESENT)));
            flag=-1;
            finish();
        }
    }

}