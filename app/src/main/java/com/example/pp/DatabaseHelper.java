package com.example.pp;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "PingPong_Match";
    public static final String JOUEUR1= "joueur1";
    public static final String JOUEUR2= "joueur2";
    public static final String BALLE = "balle";
    public static final String ACES_J1= "aces_j1";
    public static final String ACES_J2= "aces_j2";
    public static final String FAUTES_J1= "fautes_j1";
    public static final String FAUTES_J2 = "fautes_j2";
    public static final String POINTS_J1= "points_j1";
    public static final String POINTS_J2= "points_j2";
    public static final String MANCHES_J1= "manches_j1";
    public static final String MANCHES_J2 = "manches_j2";
    public static final String LET_J1= "let_j1";
    public static final String LET_J2 = "let_j2";




    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " ( id INTEGER PRIMARY KEY AUTOINCREMENT, joueur1 TEXT, joueur2 TEXT, points_j1 INTEGER, points_j2 INTEGER, balle INTEGER, aces_j1 INTEGER, aces_j2 INTEGER, fautes_j1 INTEGER, fautes_j2 INTEGER, let_j1 INTEGER, let_j2 INTEGER, manches_j1 INTEGER, manches_j2 INTEGER)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL (" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void onCreate (SQLiteDatabase db){

    db.execSQL (TABLE_CREATE);


    }



    public boolean addData (Match m)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(JOUEUR1, m.getJoueur1());
        contentValues.put(JOUEUR2, m.getJoueur2());
        contentValues.put(POINTS_J1, m.getPts_j1());
        contentValues.put(POINTS_J2, m.getPts_j2());
        contentValues.put(BALLE, m.getBalle());
        contentValues.put(ACES_J1, m.getAces_j1());
        contentValues.put(ACES_J2, m.getAces_j2());
        contentValues.put(FAUTES_J1, m.getFautes_j1());
        contentValues.put(FAUTES_J2, m.getFautes_j2());
        contentValues.put(LET_J1, m.getLet_j1());
        contentValues.put(LET_J2, m.getLet_j2());
        contentValues.put(MANCHES_J1, m.getManches_j1());
        contentValues.put(MANCHES_J2, m.getManches_j2());

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
