package com.example.yi_an.work;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parent_Milk extends AppCompatActivity {
    private static  String DATABASE_TABLE = "milk";
    private SQLiteDatabase db;
    private MyDBHelper1 dbHelper;
    private EditText editmilk;
    private TextView txttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent__milk);
        dbHelper = new MyDBHelper1(this);
        db = dbHelper.getWritableDatabase();
        editmilk = (EditText)findViewById(R.id.editText4);
        txttime = (TextView)findViewById(R.id.textView18);
        SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm");
        Date dt=new Date();
        String dts=sdf.format(dt);
        txttime.setText(dts);
    }
    public void btn_addtime(View view)
    {
        new TimePickerDialog(Parent_Milk.this,listener, Calendar.HOUR,Calendar.MINUTE,true).show();
    }

    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txttime.setText(hourOfDay+":"+minute);
        }
    };
    public void btn_addmilkdb(View view)
    {

        long id;
        ContentValues cv = new ContentValues();
        if("".equals(editmilk.getText().toString().trim()) && "".equals(txttime.getText().toString().trim()))
        {}
        else {
            SharedPreferences settings = getSharedPreferences("setting",0);
            String account = settings.getString("account", "root");
            String certification = settings.getString("certification","root");
            cv.put("milk",editmilk.getText().toString().trim());
            cv.put("date", txttime.getText().toString().trim());
            cv.put("account",account);
            cv.put("certification",certification);
            id = db.insert(DATABASE_TABLE,null,cv);
            Log.d("123",editmilk.getText().toString()+" "+txttime.getText().toString().trim()+" "+account+" "+certification);
            Log.d("db", ("新增記錄成功: " + id));
            Intent intent = new Intent();
            intent.setClass(Parent_Milk.this,Parent1.class);
            startActivity(intent);
        }

    }
}
