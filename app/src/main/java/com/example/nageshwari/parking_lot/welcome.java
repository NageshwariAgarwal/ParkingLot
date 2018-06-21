package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    TextView name;
    String s,u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        name=(TextView)findViewById(R.id.name);
        Intent i =getIntent();
        s= i.getStringExtra("name");
        u= i.getStringExtra("user");
        name.setText(s);
    }
    public void logout(View v)
    {
        Intent intent1= new Intent(getApplication(),MainActivity.class);
        startActivity(intent1);
    }
    public void btn_new(View v)
    {
        Intent intent2= new Intent(getApplication(),newbooking.class);
        intent2.putExtra("user1",u);
        startActivity(intent2);
    }
    public void btn_detail(View v)
    {
        Intent intent3 = new Intent(getApplication(), detail.class);
        intent3.putExtra("user", u);
        startActivity(intent3);
    }
    public void btn_view(View v)
    {
        Intent intent3 = new Intent(getApplication(), user_viewBooking.class);
        intent3.putExtra("user", u);
        startActivity(intent3);
    }
    public void btn_del(View v)
    {
        Intent intent3 = new Intent(getApplication(), delete.class);
        intent3.putExtra("user", u);
        startActivity(intent3);
    }


}
