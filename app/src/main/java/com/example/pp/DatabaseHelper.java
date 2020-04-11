package com.example.pp;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "mylist.db";
    private static final String TABLE_NAME = "PingPong_table";
    private static final String COL1 = "ID";
    private static final String COL2= "joueur1";
    private static final String COL3= "joueur2";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL (" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void onCreate (SQLiteDatabase db){
    String createTable = "CREATE TABLE " + TABLE_NAME +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " joueur1 TEXT )" ;
    db.execSQL (createTable);

    }

    public boolean addData (String item)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);



        long result = db.insert (TABLE_NAME, null, contentValues);

        if (result == -1)
        {
        return false;
        }
        else
        {
        return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;

    }



}
