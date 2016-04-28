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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Parent_Shit extends AppCompatActivity {
    private static  String DATABASE_TABLE = "shit";
    private SQLiteDatabase db;
    private MyDBHelper4 dbHelper;
    private List<String> spanner_list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Spinner spinner;
    private List<String> spanner_list2 = new ArrayList<String>();
    private ArrayAdapter<String> adapter2;
    private Spinner spinner2;
    private String account="",certification="",shit="",unit="";
    private TextView txttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent__shit);
        dbHelper = new MyDBHelper4(this);
        db = dbHelper.getWritableDatabase();
        spinner = (Spinner)findViewById(R.id.spinner7);
        spinner2 = (Spinner)findViewById(R.id.spinner8);
        txttime = (TextView)findViewById(R.id.textView21);
        SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm");
        Date dt=new Date();
        String dts=sdf.format(dt);
        txttime.setText(dts);
        spanner_list.add("黑色");
        spanner_list.add("咖啡色");
        spanner_list.add("紅色");
        spanner_list.add("黑咖色");
        spanner_list.add("黑紅色");
        spanner_list.add("紅咖色");

        spanner_list2.add("條狀");
        spanner_list2.add("顆粒狀");
        spanner_list2.add("稀狀");





        adapter = new ArrayAdapter<String>(Parent_Shit.this,android.R.layout.simple_spinner_item, spanner_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("顏色");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerOnItemSelectListener());

        adapter2 = new ArrayAdapter<String>(Parent_Shit.this,android.R.layout.simple_spinner_item, spanner_list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setPrompt("形狀");
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new SpinnerOnItemSelectListener2());



    }
    class SpinnerOnItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> AdapterView, View view, int position,
                                   long arg3) {
            // TODO Auto-generated method stub
            String selected = AdapterView.getItemAtPosition(position).toString();
            shit = AdapterView.getItemAtPosition(position).toString();
            System.out.println(selected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
            System.out.println("NothingSelected");
        }
    }

    class SpinnerOnItemSelectListener2 implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> AdapterView, View view, int position,
                                   long arg3) {
            // TODO Auto-generated method stub
            unit = AdapterView.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
            System.out.println("NothingSelected");
        }
    }

    public void btn_addtime(View view)
    {
        new TimePickerDialog(Parent_Shit.this,listener, Calendar.HOUR,Calendar.MINUTE,true).show();
    }

    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txttime.setText(hourOfDay+":"+minute);
        }
    };
    public void btn_addshitdb(View view)
    {
        long id;
        ContentValues cv = new ContentValues();
        if("".equals(shit.trim()) &&  "".equals(unit.trim()))
        {}
        else {
            SharedPreferences settings = getSharedPreferences("setting",0);
            String account = settings.getString("account", "root");
            String certification = settings.getString("certification","root");
            cv.put("shit",shit.trim());
            cv.put("unit",unit.trim());
            cv.put("account",account);
            cv.put("certification",certification);
            cv.put("date",txttime.getText().toString().trim());
            id = db.insert(DATABASE_TABLE,null,cv);
            Log.d("123", shit + " " + unit + " " + unit);
            Log.d("db", ("新增記錄成功: " + id));
            Intent intent = new Intent();
            intent.setClass(Parent_Shit.this, Parent1.class);
            startActivity(intent);
        }
    }

}
