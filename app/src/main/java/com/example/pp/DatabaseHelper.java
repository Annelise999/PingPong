package com.example.pp;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DataBaseHelper";
    private static final String TABLE_NAME = "PingPong_table";
    private static final String COL_1 = "ID";
    private static final String COL_2= "Name";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL (" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void onCreate (SQLiteDatabase db){
    String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_1 + "TEXT )" ;
    db.execSQL (createTable);
    }

    public boolean addData (String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item);

        Log.d(TAG,  "addData : Adding" + item + "to " + TABLE_NAME);

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
