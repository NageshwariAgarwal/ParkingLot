package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void btn_user(View v)
    {
        Intent intent= new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }
    public void btn_admin(View v)
    {
        Intent intent1= new Intent(getApplication(),adminLogin.class);
        startActivity(intent1);
    }
}
