package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class adminWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
    }
    public void btn_logout(View v)
    {
        Intent intent= new Intent(getApplication(),adminLogin.class);
        startActivity(intent);
    }
    public void btn_userdetails(View v)
    {
        Intent intent= new Intent(getApplication(),viewUser.class);
        startActivity(intent);
    }
    public void newslot(View v)
    {
        Intent intent = new Intent(getApplication(), newSlot.class);
        startActivity(intent);
    }
    public void btn_view(View v)
    {
        Intent intent = new Intent(getApplication(), admin_viewBooking.class);
        startActivity(intent);
    }

}
