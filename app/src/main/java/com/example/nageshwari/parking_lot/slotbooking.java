package com.example.nageshwari.parking_lot;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class slotbooking extends AppCompatActivity {

    TextView number;
    static EditText date1;
    EditText time;
    TimePickerDialog timePickerDialog;
    Spinner duration;
    SQLiteDatabase db;
    Intent i;
    Boolean status;
    Cursor cursor;
    int max;
    LinearLayout l2;
    String s,p,u,du;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slotbooking);
        number=(TextView)findViewById(R.id.number);
        l2= (LinearLayout)findViewById(R.id.l2);
        db = openOrCreateDatabase("Parking", MODE_PRIVATE, null);
        db.execSQL("create table if not exists booking(b_id varchar2(5) primary key ,userid varchar2(30), date_b varchar2(11), " +
                "time_b varchar2(11), duration number(5), plot_no number(5), slot_no number(5), area varchar2(15))");
        Intent i =getIntent();
        s = i.getStringExtra("slot");
        p = i.getStringExtra("plot");
        u = i.getStringExtra("user");
        number.setText(s);
        date1 = (EditText)findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        duration = (Spinner) findViewById(R.id.duration);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int min = mcurrentTime.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(slotbooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timepicker, int hourOfDay, int minute) {
                        time.setText(hourOfDay + ":" + minute);
                    }
                },hour,min,false);
                timePickerDialog.show();
            }
        });
    }
    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");

    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @RequiresApi(api = Build.VERSION_CODES.N)
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c= Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return  new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date1.setText(day+"/"+(month+1)+"/"+year);
        }

    }
    public void btn_search(View v) {
        final String dt = date1.getText().toString();
        final String tm = time.getText().toString();
        du = duration.getSelectedItem().toString();
        if  (TextUtils.isEmpty(dt) || TextUtils.isEmpty(tm) || du.equalsIgnoreCase("none")) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            String str[] = s.split(" ");
            String plt[] = p.split(" ");
            final int sno = Integer.parseInt(str[1]);
            final int pno = Integer.parseInt(plt[1]);
            cursor = db.rawQuery("select * from plot_info where slot_no = '" + sno + "' and plot_no = '" + pno + "'", null);
            status = cursor.moveToNext();
            if (status) {
                String max_area = cursor.getString(cursor.getColumnIndex("area"));
                max = Integer.parseInt(max_area);
                for (int i = 1; i <= max; i++) {
                    final Button btn = new Button(getApplication());
                    btn.setId(Integer.parseInt(Integer.toString(i)));
                    final int id_ = btn.getId();
                    btn.setText("Area " + id_);
                    l2.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String area = (String) btn.getText();
                            cursor = db.rawQuery("select b_id from booking where b_id= (SELECT MAX(b_id) FROM booking)",null);
                            status = cursor.moveToNext();
                            if (status) {
                                String row = cursor.getString(cursor.getColumnIndex("b_id"));
                                String []arr = row.split("_");
                                max = Integer.parseInt(arr[1]);
                            }
                            else
                            {
                                max =0;
                            }
                            String bid = "B_"+(max+1);
                            int d =Integer.parseInt(du);
                            db.execSQL("insert into booking values('" + bid + "','" + u + "','" + dt + "','" + tm + "','" + d + "','" + pno + "','" + sno + "','" + area + "')");
                            Toast.makeText(getApplication(), "Booking succesful!!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplication(),"Booking Id = "+bid,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), welcome.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
}
