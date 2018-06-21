package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {

    EditText auser, apassword;
    Button alogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        auser = (EditText)findViewById(R.id.auser);
        apassword =(EditText)findViewById(R.id.apassword);
        alogin =(Button)findViewById(R.id.alogin);
    }
    public void btn_login(View v) {
        try {
            String id = auser.getText().toString();
            String paswrd = apassword.getText().toString();
            if (id.equals("Admin") && paswrd.equals("admin")) {
                Intent intent = new Intent(getApplication(), adminWelcome.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid id or password", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
