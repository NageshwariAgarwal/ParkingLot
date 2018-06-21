package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class newSlot extends AppCompatActivity {

    SQLiteDatabase db =null;
    EditText plotno,slotno,area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_slot);
        db=openOrCreateDatabase("Parking",MODE_PRIVATE,null);
        db.execSQL("create table if not exists plot_info(plot_no number(5),slot_no number(5),area number(5))");
        plotno = (EditText)findViewById(R.id.plotno);
        slotno = (EditText)findViewById(R.id.slotno);
        area = (EditText)findViewById(R.id.area);
    }
    public void btn_submit(View v)
    {
        String pl = plotno.getText().toString();
        String sl = slotno.getText().toString();
        String ar = area.getText().toString();
        if (TextUtils.isEmpty(pl) || TextUtils.isEmpty(sl) || TextUtils.isEmpty(ar)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                db.execSQL("insert into plot_info values('" + pl + "','" + sl + "','" + ar + "')");
                plotno.getText().clear();
                slotno.getText().clear();
                area.getText().clear();
                Toast.makeText(this, "Slot created Succesfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), adminWelcome.class);
                startActivity(intent);
            }catch (SQLException e){
                Toast.makeText(this,"User Id already taken.Try another.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
