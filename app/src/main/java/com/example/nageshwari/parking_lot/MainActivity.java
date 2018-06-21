package com.example.nageshwari.parking_lot;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db =null;
    EditText user, password;
    Cursor cursor;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.user);
        password =(EditText)findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);
        db=openOrCreateDatabase("Parking",MODE_PRIVATE,null);
        db.execSQL("create table if not exists user_info(name varchar2(30) ,phone_no number(10), email varchar2(30),userid varchar2(30) unique, password varchar2(30))");
    }

    public void new_user(View v)
    {
        Intent intent= new Intent(getApplication(),Registration.class);
        startActivity(intent);
    }
    public void btn_login(View v)
    {
        try{
            String uid = user.getText().toString();
            String paswrd = password.getText().toString();
            cursor = db.rawQuery("select * from user_info where userid ='" + uid + "' and password = '" + paswrd + "'", null);
            boolean status = cursor.moveToNext();
            if (status) {
                String n = cursor.getString(cursor.getColumnIndex("userid"));
                if (uid.equals(n)) {
                    Intent intent = new Intent(getApplication(), welcome.class);
                    String nm = cursor.getString(cursor.getColumnIndex("name"));
                    intent.putExtra("name", nm);
                    intent.putExtra("user",uid);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid User Id or Password", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
           // Log.e("MYAPP","exception: "+e.getMessage());
           // Log.e("MYAPP","exception: "+e.toString());

        }
    }
}
