package com.example.sridh.target75;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sridh.target75.database.AttendanceContract;
import com.example.sridh.target75.database.AttendanceDBHelper;

public class MainActivity extends AppCompatActivity implements AttendanceRecyclerAdapter.ListItemClickListener{

    private TextView textView;
    private RecyclerView rv;
    private SQLiteDatabase database;
    private AttendanceRecyclerAdapter adapter;
    private Cursor cursor;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv_item);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setHasFixedSize(true);
        }

    @Override
    protected void onStart() {
        super.onStart();
        AttendanceDBHelper dbHelper = new AttendanceDBHelper(this);
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + AttendanceContract.AttendanceEntry.TABLE_NAME, null);
        adapter =new AttendanceRecyclerAdapter(this,cursor,this);

        rv.setAdapter(adapter);
        rv.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int item_id = item.getItemId();
        if (item_id == R.id.new_subject) {
            startActivity(new Intent(MainActivity.this, NewSubjectActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickeditemindex) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putInt("gridview_item",clickeditemindex).commit();
        startActivity(new Intent(MainActivity.this, StatsActivity.class));

        }

}