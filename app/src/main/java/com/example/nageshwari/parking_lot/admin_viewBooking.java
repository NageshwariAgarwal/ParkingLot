package com.example.nageshwari.parking_lot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class admin_viewBooking extends AppCompatActivity {

    SQLiteDatabase db=null;
    ArrayList<String> bid= new ArrayList<String>();
    ArrayList<String> date= new ArrayList<String>();
    ArrayList<String> time= new ArrayList<String>();
    ArrayList<String> dura= new ArrayList<String>();
    ArrayList<String> user= new ArrayList<String>();
    ArrayList<String> plot= new ArrayList<String>();
    ArrayList<String> slot= new ArrayList<String>();
    ArrayList<String> area= new ArrayList<String>();
    ListView listview1;
    BookingAdapter list;
    SQLiteHelper helper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_booking);
        listview1 = (ListView) findViewById(R.id.listview1);
        helper = new SQLiteHelper(this);
    }
    protected void onResume() {
        showSQLiteDBData();
        super.onResume();
    }
    private void showSQLiteDBData()
    {
            db = helper.getWritableDatabase();
            cursor = db.rawQuery("Select * from booking", null);
            if (cursor.moveToFirst()) {
                do {
                    bid.add(cursor.getString(cursor.getColumnIndex("b_id")));
                    date.add(cursor.getString(cursor.getColumnIndex("date_b")));
                    time.add(cursor.getString(cursor.getColumnIndex("time_b")));
                    dura.add(cursor.getString(cursor.getColumnIndex("duration")));
                    user.add(cursor.getString(cursor.getColumnIndex("userid")));
                    plot.add(cursor.getString(cursor.getColumnIndex("plot_no")));
                    slot.add(cursor.getString(cursor.getColumnIndex("slot_no")));
                    area.add(cursor.getString(cursor.getColumnIndex("area")));
                } while (cursor.moveToNext());
            }
            list = new BookingAdapter(admin_viewBooking.this, bid, date, time, dura, user, plot, slot, area);
            listview1.setAdapter(list);
            cursor.close();
    }
}
