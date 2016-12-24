package com.example.sridh.target75;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sridh.target75.database.AttendanceContract;
import com.example.sridh.target75.database.AttendanceDBHelper;

public class StatsActivity extends AppCompatActivity {

    private TextView textView;
    private Cursor cursor;
    private AttendanceDBHelper dbHelper;
    private SQLiteDatabase dbs;
    private SharedPreferences sharedPreferences;
    private Integer i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        textView = (TextView) findViewById(R.id.subject);
        dbHelper = new AttendanceDBHelper(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        dbs = dbHelper.getReadableDatabase();

        Cursor cursor = dbs.rawQuery("SELECT * FROM " + AttendanceContract.AttendanceEntry.TABLE_NAME, null);
        int position = sharedPreferences.getInt("gridview_item",-1);
        cursor.moveToPosition(position);
        textView.setText(cursor.getString(cursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_SUBJECT)));
    }
}
