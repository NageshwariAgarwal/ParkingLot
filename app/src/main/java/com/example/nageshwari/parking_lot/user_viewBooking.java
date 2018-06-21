package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class user_viewBooking extends AppCompatActivity {

    EditText bid;
    TextView plot,slot,id_,date,text_plot,text_slot,text_id, text_date;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_booking);
        bid = (EditText)findViewById(R.id.bid);
        plot = (TextView)findViewById(R.id.plot);
        text_plot = (TextView)findViewById(R.id.text_plot);
        slot = (TextView)findViewById(R.id.slot);
        text_slot = (TextView)findViewById(R.id.text_slot);
        id_ = (TextView)findViewById(R.id.id);
        text_id = (TextView)findViewById(R.id.text_id);
        date = (TextView)findViewById(R.id.date);
        text_date = (TextView)findViewById(R.id.text_date);
    }
    public void btn_sbmt(View v)
    {
        String bbid= bid.getText().toString();
        if (TextUtils.isEmpty(bbid))
        {
            Toast.makeText(this,"Please enter booking id",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i = getIntent();
            String uid = i.getStringExtra("user");
            db = openOrCreateDatabase("Parking", MODE_PRIVATE, null);
            cursor = db.rawQuery("select * from booking where userid ='" + uid + "' and b_id = '" + bbid + "'", null);
            boolean status = cursor.moveToNext();
            if (status) {
                text_plot.setVisibility(View.VISIBLE);
                text_slot.setVisibility(View.VISIBLE);
                text_id.setVisibility(View.VISIBLE);
                text_date.setVisibility(View.VISIBLE);
                String d = cursor.getString(cursor.getColumnIndex("date_b"));
                date.setText(d);
                String s = cursor.getString(cursor.getColumnIndex("slot_no"));
                slot.setText(s);
                String p = cursor.getString(cursor.getColumnIndex("plot_no"));
                plot.setText(p);
                id_.setText(uid);
            }
            else
            {
                Toast.makeText(this,"No record found",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
