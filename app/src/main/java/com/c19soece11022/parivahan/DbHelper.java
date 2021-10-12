package com.c19soece11022.parivahan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Parivahan.db";
    public static final String TB_NAME = "reg_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "uname";
    public static final String COL_3 = "password";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TB_NAME + " (id integer primary key autoincrement , uname text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TB_NAME);
        onCreate(db);
    }

    public boolean AddData(String name, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COL_2, name);
        val.put(COL_3, pass);
        long res = db.insert(TB_NAME, null, val);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TB_NAME, null);
        return res;
    }


    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COL_1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_2 + " = ?" + " AND " + COL_3 + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * SELECT user_id FROM user WHERE user_email = 'jack@test.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TB_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        if (cursorCount > 0)
        {
            return true;
        }

        return false;
    }

}
