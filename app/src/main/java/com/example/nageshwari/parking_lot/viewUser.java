package com.example.nageshwari.parking_lot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class viewUser extends AppCompatActivity {

    SQLiteDatabase db=null;
    ArrayList<String> Name= new ArrayList<String>();
    ArrayList<String> Phnno= new ArrayList<String>();
    ArrayList<String> Email= new ArrayList<String>();
    ListView listview1;
    SQLiteListAdapter list;
    SQLiteHelper helper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        listview1 = (ListView) findViewById(R.id.listview1);
        helper = new SQLiteHelper(this);
    }
    @Override
    protected void onResume() {
        showSQLiteDBData();
        super.onResume();
    }
    private void showSQLiteDBData()
    {
        db= helper.getWritableDatabase();
        cursor=db.rawQuery("Select * from user_info",null);
        if (cursor.moveToFirst())
        {
            do {
                Name.add(cursor.getString(cursor.getColumnIndex("name")));
                Phnno.add(cursor.getString(cursor.getColumnIndex("phone_no")));
                Email.add(cursor.getString(cursor.getColumnIndex("email")));
            }while (cursor.moveToNext());
        }
        list=new SQLiteListAdapter(viewUser.this,Name,Phnno,Email);
        listview1.setAdapter(list);
        cursor.close();
    }
}
