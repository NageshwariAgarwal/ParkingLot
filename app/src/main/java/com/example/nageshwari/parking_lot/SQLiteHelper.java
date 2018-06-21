package com.example.nageshwari.parking_lot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NAGESHWARI on 13-06-2018.
 */
public class SQLiteHelper extends SQLiteOpenHelper{


    public SQLiteHelper(Context context) {
        super(context,"Parking" ,null ,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user_info(name varchar2(30) ,phone_no number(10), email varchar2(30),userid varchar2(30) unique, password varchar2(30))");
        db.execSQL("create table if not exists booking(b_id varchar2(5) primary key ,userid varchar2(30), date_b varchar2(11), " +
                "time_b varchar2(11), duration number(5), plot_no number(5), slot_no number(5), area varchar2(15))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+"user_info");
        db.execSQL("drop table if exists "+"booking");
        onCreate(db);
    }
}
