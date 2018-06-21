package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity {

    SQLiteDatabase db = null;
    Cursor cursor;
    TextView nm, phn, em,bk;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nm = (TextView) findViewById(R.id.name);
        phn = (TextView) findViewById(R.id.phone);
        em = (TextView) findViewById(R.id.email);
        bk = (TextView)findViewById(R.id.booking) ;
        Intent i = getIntent();
        uid = i.getStringExtra("user");
        db = openOrCreateDatabase("Parking", MODE_PRIVATE, null);
        cursor = db.rawQuery("select * from user_info where userid ='" + uid + "'", null);
        boolean status = cursor.moveToNext();
        if (status) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            nm.setText(name);
            String ph = cursor.getString(cursor.getColumnIndex("phone_no"));
            phn.setText(ph);
            String e = cursor.getString(cursor.getColumnIndex("email"));
            em.setText(e);
        }
        cursor = db.rawQuery("select * from booking where userid ='"+ uid +"'",null);
        if (cursor.moveToFirst()) {
            do
            {
                String bid = cursor.getString(cursor.getColumnIndex("b_id"));
                bk.append(bid+"  ");
            } while (cursor.moveToNext());
        }
    }
}
