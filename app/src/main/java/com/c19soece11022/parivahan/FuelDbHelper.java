package com.c19soece11022.parivahan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FuelDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Fuel.db";
    public static final String T_NAME = "fuel_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "date";
    public static final String COL_3 = "vno";
    public static final String COL_4 = "ometer";
    public static final String COL_5 = "fuel";
    public static final String COL_6 = "price";


    public FuelDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + T_NAME + " (id integer primary key autoincrement , date text, vno text,ometer double,fuel double,price double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + T_NAME);
        onCreate(db);
    }

    public boolean AddData(String date, String vno, Double ometer, Double fuel, Double price) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COL_2, date);
        val.put(COL_3, vno);
        val.put(COL_4, ometer);
        val.put(COL_5, fuel);
        val.put(COL_6, price);

        long res = db.insert(T_NAME, null, val);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + T_NAME, null);
        return res;
    }


    public int getAllPriceSum() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + FuelDbHelper.COL_6 + ") as Total FROM " + FuelDbHelper.T_NAME, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            return total;
        }
        return 0;

    }



}
