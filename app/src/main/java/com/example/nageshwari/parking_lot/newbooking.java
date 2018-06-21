package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class newbooking extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cursor;
    Button sbmt;
    int max;
    int rbid;
    RadioGroup rg;
    String selectplot,u;
    boolean status;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbooking);
        rg = (RadioGroup) findViewById(R.id.radiogroup);
        sbmt =(Button)findViewById(R.id.plotsubmit);
        layout = (LinearLayout) findViewById(R.id.l1);
        Intent in = getIntent();
        u=in.getStringExtra("user1");
        db = openOrCreateDatabase("Parking", MODE_PRIVATE, null);
        cursor = db.rawQuery("select max(plot_no) from plot_info", null);
        status = cursor.moveToNext();
        if (status) {
            String max_plot = cursor.getString(cursor.getColumnIndex("max(plot_no)"));
            max = Integer.parseInt(max_plot);
            for (int i = 1; i <= max; i++) {
                RadioButton button = new RadioButton(newbooking.this);
                button.setId(Integer.parseInt(Integer.toString(i)));
                button.setText("plot " + Integer.toString(i));
                rg.addView(button);
            }
        }
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeAllViews();
                rbid = rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)rg.findViewById(rbid);
                selectplot = (String)radioButton.getText();
                for (int i = 1; i <= max; i++) {
                    String pl = "plot " + Integer.toString(i);
                    if (selectplot.equals(pl)) {
                        cursor = db.rawQuery("select max(slot_no) from plot_info where plot_no = '" + i + "' group by plot_no", null);
                        break;
                    }
                }
                status = cursor.moveToNext();
                if (status) {
                    String max_slot = cursor.getString(cursor.getColumnIndex("max(slot_no)"));
                    max = Integer.parseInt(max_slot);
                    for (int i = 1; i <= max; i++) {
                        final Button btn = new Button(getApplication());
                        btn.setId(Integer.parseInt(Integer.toString(i)));
                        final int id_ = btn.getId();
                        btn.setText("SLOT " + id_);
                        layout.addView(btn);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String selectslot = (String) btn.getText();
                                Intent intent = new Intent(getApplication(),slotbooking.class);
                                intent.putExtra("slot", selectslot);
                                intent.putExtra("plot",selectplot);
                                intent.putExtra("user",u);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });

    }
}
