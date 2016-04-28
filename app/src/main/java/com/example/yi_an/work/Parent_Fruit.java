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

public class Parent_Fruit extends AppCompatActivity {
    private static  String DATABASE_TABLE = "fruit";
    private SQLiteDatabase db;
    private MyDBHelper3 dbHelper;
    private List<String> spanner_list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Spinner spinner;
    private List<String> spanner_list2 = new ArrayList<String>();
    private ArrayAdapter<String> adapter2;
    private Spinner spinner2;
    private List<String> spanner_list3 = new ArrayList<String>();
    private ArrayAdapter<String> adapter3;
    private Spinner spinner3;
    private String account="",certification="",fruit="",count="",unit="";
    private TextView txttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent__fruit);
        dbHelper = new MyDBHelper3(this);
        db = dbHelper.getWritableDatabase();
        spinner = (Spinner)findViewById(R.id.spinner4);
        spinner2 = (Spinner)findViewById(R.id.spinner5);
        spinner3 = (Spinner)findViewById(R.id.spinner6);
        txttime = (TextView)findViewById(R.id.textView20);
        SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm");
        Date dt=new Date();
        String dts=sdf.format(dt);
        txttime.setText(dts);
        spanner_list.add("蘋果");
        spanner_list.add("水梨");
        spanner_list.add("鳳梨");
        spanner_list.add("芒果");
        spanner_list.add("葡萄");
        spanner_list.add("布丁");
        spanner_list.add("龍演");
        spanner_list2.add("1");
        spanner_list2.add("2");
        spanner_list2.add("3");
        spanner_list2.add("4");
        spanner_list2.add("5");
        spanner_list2.add("6");
        spanner_list2.add("1/2");

        spanner_list3.add("份");
        spanner_list3.add("顆");
        spanner_list3.add("碗");
        spanner_list3.add("杯");
        spanner_list3.add("匙");



        adapter = new ArrayAdapter<String>(Parent_Fruit.this,android.R.layout.simple_spinner_item, spanner_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("下拉菜单");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerOnItemSelectListener());

        adapter2 = new ArrayAdapter<String>(Parent_Fruit.this,android.R.layout.simple_spinner_item, spanner_list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setPrompt("份量");
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new SpinnerOnItemSelectListener2());

        adapter3 = new ArrayAdapter<String>(Parent_Fruit.this,android.R.layout.simple_spinner_item, spanner_list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setPrompt("單位");
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new SpinnerOnItemSelectListener3());


    }
    class SpinnerOnItemSelectListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> AdapterView, View view, int position,
                                   long arg3) {
            // TODO Auto-generated method stub
            String selected = AdapterView.getItemAtPosition(position).toString();
            fruit = AdapterView.getItemAtPosition(position).toString();
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
            count = AdapterView.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
            System.out.println("NothingSelected");
        }
    }

    class SpinnerOnItemSelectListener3 implements AdapterView.OnItemSelectedListener {

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
        new TimePickerDialog(Parent_Fruit.this,listener, Calendar.HOUR,Calendar.MINUTE,true).show();
    }

    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txttime.setText(hourOfDay+":"+minute);
        }
    };
    public void btn_addfruitdb(View view)
    {
        long id;
        ContentValues cv = new ContentValues();
        if("".equals(fruit.trim()) && "".equals(count.trim()) &&  "".equals(unit.trim()))
        {}
        else {
            SharedPreferences settings = getSharedPreferences("setting",0);
            String account = settings.getString("account", "root");
            String certification = settings.getString("certification","root");
            cv.put("fruit",fruit.trim());
            cv.put("count", count.trim());
            cv.put("unit",unit.trim());
            cv.put("account",account);
            cv.put("certification",certification);
            cv.put("date",txttime.getText().toString().trim());
            id = db.insert(DATABASE_TABLE,null,cv);
            Log.d("123", fruit + " " + count + " " + unit);
            Log.d("db", ("新增記錄成功: " + id));
            Intent intent = new Intent();
            intent.setClass(Parent_Fruit.this, Parent1.class);
            startActivity(intent);
        }
    }

}

