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
import android.widget.CheckBox;
import android.widget.Checkable;
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
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);
        btn = (Button) findViewById(R.id.button);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);

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

        ContentValues values = new ContentValues();


       if(checkBox1.isChecked()){
            values.put(AttendanceContract.AttendanceEntry.COLUMN_MONDAY,true);
        }
        else
            values.put(AttendanceContract.AttendanceEntry.COLUMN_MONDAY,false);


        if(checkBox2.isChecked()){

            values.put(AttendanceContract.AttendanceEntry.COLUMN_TUESDAY,true);
        }

        else
            values.put(AttendanceContract.AttendanceEntry.COLUMN_TUESDAY,false);
        if(checkBox3.isChecked()){

            values.put(AttendanceContract.AttendanceEntry.COLUMN_WEDNESDAY,true);
        }

        else
            values.put(AttendanceContract.AttendanceEntry.COLUMN_WEDNESDAY,false);
        if(checkBox4.isChecked()){

            values.put(AttendanceContract.AttendanceEntry.COLUMN_THURSDAY,true);
        }

        else
            values.put(AttendanceContract.AttendanceEntry.COLUMN_THURSDAY,false);

        if(checkBox5.isChecked()){

            values.put(AttendanceContract.AttendanceEntry.COLUMN_FRIDAY,true);
        }

        else
            values.put(AttendanceContract.AttendanceEntry.COLUMN_FRIDAY,false);

        subject_name = editText.getText().toString();
        values.put(AttendanceContract.AttendanceEntry.COLUMN_SUBJECT,subject_name);
        values.put(AttendanceContract.AttendanceEntry.COLUMN_PRESENT,0);
        values.put(AttendanceContract.AttendanceEntry.COLUMN_ABSENT,0);

        Log.v("NewSubjectSActivity","NewSubjectActivity " + values);
        long rowId =mdb.insert(AttendanceContract.AttendanceEntry.TABLE_NAME,null,values);

        if(rowId==-1){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show();
            Log.v("NewSubjectActivity","rowId =" + rowId);
    }

}