package com.example.pp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class previousmatches extends AppCompatActivity {

    private static final String TAG = "ListPreviousMatchActivity";

    DatabaseHelper mDataBaseHelper;

    private ListView myliste;
    private ArrayList<Match> listData;
    private ArrayList<String> info_match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previousmatches);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myliste = findViewById(R.id.Liste_matches);
        mDataBaseHelper= new DatabaseHelper(this);


        populateListView ();

    }

    private void populateListView()
    {
        listData = new ArrayList<>();
        info_match = new ArrayList<>();
        Cursor data = mDataBaseHelper.getData();
        while (data.moveToNext()){
            listData.add(new Match(data.getString(1), data.getString(2), data.getInt(12), data.getInt(13), data.getInt(3), data.getInt(4), data.getInt(5), data.getInt(6), data.getInt(7), data.getInt(10), data.getInt(11), data.getInt(8), data.getInt(9)));
            info_match.add(data.getString(1) + data.getString(2));
        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,info_match);
        myliste.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.previous_match) {
            Intent intent= new Intent (this, previousmatches.class);
            startActivity(intent);

        }
        if (id == R.id.match) {
            Intent intent= new Intent (this, MainActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

}
