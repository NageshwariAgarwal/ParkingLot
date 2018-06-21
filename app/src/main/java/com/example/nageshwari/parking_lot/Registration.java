package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText name, phone, email, password,UserId;
    SQLiteDatabase db = null;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        UserId = (EditText)findViewById(R.id.UserId);
        btn = (Button)findViewById(R.id.Submit);
        db = openOrCreateDatabase("Parking", MODE_PRIVATE, null);
        db.execSQL("create table if not exists user_info(name varchar2(30) ,phone_no number(10), email varchar2(30),userid varchar2(30) unique, password varchar2(30))");
    }
    public void btn_submit(View v) {
        String nm = name.getText().toString();
        String em = email.getText().toString();
        String paswrd = password.getText().toString();
        String phn = phone.getText().toString();
        String uid = UserId.getText().toString();
        if (TextUtils.isEmpty(nm) || TextUtils.isEmpty(em) || TextUtils.isEmpty(paswrd) || TextUtils.isEmpty(phn) || TextUtils.isEmpty(uid)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                db.execSQL("insert into user_info values('" + nm + "','" + phn + "','" + em + "','" + uid + "','" + paswrd + "')");
                name.getText().clear();
                phone.getText().clear();
                email.getText().clear();
                password.getText().clear();
                Toast.makeText(this, "Registered Succesfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }catch (SQLException e){
                Toast.makeText(this,"User Id already taken.Try another.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

