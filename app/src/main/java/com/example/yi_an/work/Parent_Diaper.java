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
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parent_Diaper extends AppCompatActivity {
    private static  String DATABASE_TABLE = "diaper";
    private SQLiteDatabase db;
    private MyDBHelper5 dbHelper;
    private String account="",certification="",diaper="";
    private TextView txttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent__diaper);
        dbHelper = new MyDBHelper5(this);
        db = dbHelper.getWritableDatabase();
        txttime = (TextView)findViewById(R.id.textView23);
        SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm");
        Date dt=new Date();
        String dts=sdf.format(dt);
        txttime.setText(dts);
    }
    public void btn_addtime(View view)
    {
        new TimePickerDialog(Parent_Diaper.this,listener, Calendar.HOUR,Calendar.MINUTE,true).show();
    }

    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txttime.setText(hourOfDay+":"+minute);
        }
    };
    public void btn_adddiaperdb(View view)
    {
        long id;
        ContentValues cv = new ContentValues();

            SharedPreferences settings = getSharedPreferences("setting",0);
            String account = settings.getString("account", "root");
            String certification = settings.getString("certification","root");
            cv.put("diaper","1");
            cv.put("account",account);
            cv.put("certification",certification);
            cv.put("date",txttime.getText().toString().trim());
            id = db.insert(DATABASE_TABLE,null,cv);
            Log.d("db", ("新增記錄成功: " + id));
        Intent intent = new Intent();
        intent.setClass(Parent_Diaper.this,Parent1.class);
        startActivity(intent);
        }
}

