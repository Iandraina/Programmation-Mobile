package ck.edu.com.sportapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MatchBasket.db";
    public static final String TABLE_NAME = "BASKET";
    public static final String COLUMN_ID = "MATCH_ID";
    public static final String COLUMN_TEAM = "TEAMS";
    public static final String COLUMN_SCORE = "SCORE";
    public static final String COLUMN_ASSIST = "ASSIST";
    public static final String COLUMN_FAULT = "FAULT";
    public static final String COLUMN_REBOUND = "REBOUND";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_TEAM + " TEXT, " + COLUMN_SCORE + " TEXT, " + COLUMN_ASSIST + " TEXT, " + COLUMN_FAULT + " TEXT, "+ COLUMN_REBOUND + " TEXT) ";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean insertData(String teams, String score, String assit, String fault, String rebound){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEAM, teams);
        contentValues.put(COLUMN_SCORE, score);
        contentValues.put(COLUMN_ASSIST, assit);
        contentValues.put(COLUMN_FAULT, fault);
        contentValues.put(COLUMN_REBOUND, rebound);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if(result == -1) {
            return false;
        }
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor queryRES = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return queryRES;
    }

    public int countElement() {
        int cpt;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM " +TABLE_NAME;
        cpt = (int) DatabaseUtils.longForQuery(db,query,null);
        db.close();
        return cpt;
    }
}
