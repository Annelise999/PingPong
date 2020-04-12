package com.example.pp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class previousmatches extends AppCompatActivity {

    DatabaseHelper mDataBaseHelper;

    private ListView myliste;
    private ArrayList<String> info_match;
    private Button go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previousmatches);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myliste = findViewById(R.id.Liste_matches);
        mDataBaseHelper= new DatabaseHelper(this);
        go_back= findViewById(R.id.GoBack);


        populateListView ();

    }

    private void populateListView()
    {
        info_match = new ArrayList<>();
        Cursor data = mDataBaseHelper.getData();
        while (data.moveToNext()){
            info_match.add("SCORE : " + data.getString(1) + " : " + data.getInt(3) + " VS " + data.getString(2) + " : " + data.getInt(4) + " the WINNER is " + data.getString(14) );
        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,info_match);
        myliste.setAdapter(adapter);
    }

    public void myClickGoBack(View view){
        if (view.getId()== R.id.GoBack){
            Intent intent= new Intent (this, MainActivity.class);
            startActivity(intent);
        }
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
