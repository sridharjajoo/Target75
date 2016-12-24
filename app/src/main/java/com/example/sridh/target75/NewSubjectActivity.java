package com.example.sridh.target75;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sridh.target75.database.AttendanceContract;
import com.example.sridh.target75.database.AttendanceDBHelper;

import static android.os.Build.VERSION_CODES.M;

public class NewSubjectActivity extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mSharedEditor;
    private Button btn;
    private boolean button_boolean = false;
    private SQLiteDatabase mdb;
    private String subject_name;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        btn = (Button) findViewById(R.id.button);


        editText = (EditText) findViewById(R.id.edit_text); //to read subject name

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("NewSubject.class","Inside onClick");
                addSubject();
                finish();
                Toast.makeText(NewSubjectActivity.this,"Successfully Inserted",Toast.LENGTH_SHORT);
            }
        });

    }

    public void addSubject(){


        AttendanceDBHelper dbhelper = new AttendanceDBHelper(this);
        mdb = dbhelper.getWritableDatabase();

        subject_name = editText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(AttendanceContract.AttendanceEntry.COLUMN_SUBJECT,subject_name);
        long rowId =mdb.insert(AttendanceContract.AttendanceEntry.TABLE_NAME,null,values);

        if(rowId==-1){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show();

    }

}