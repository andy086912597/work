package com.example.yi_an.work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.SQLException;

public class Login extends AppCompatActivity {
    private static  String DATABASE_TABLE = "user";
    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    private EditText editaccount,editpassword;
    private RadioGroup rgroup;
    private RadioButton radbtn1,radbtn2,radbtn3;
    private String radio="";
    private SharedPreferences setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();
        editaccount = (EditText)findViewById(R.id.editaccount);
        editpassword = (EditText)findViewById(R.id.editpass);
//        editaccount.setText("andy");
//        editpassword.setText("123");
        radbtn1 = (RadioButton)findViewById(R.id.radioButton);
        radbtn2 = (RadioButton)findViewById(R.id.radioButton2);
        radbtn3 = (RadioButton)findViewById(R.id.radioButton3);
        rgroup = (RadioGroup)findViewById(R.id.rgroup);
        rgroup.setOnCheckedChangeListener(listener);
        setting = getSharedPreferences("setting",0);
        radbtn1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    radbtn1.setBottom(R.drawable.l1_1);
                } else {
                    radbtn1.setBottom(R.drawable.l1);
                }
            }
        });
        radbtn2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {radbtn2.setBottom(R.drawable.l2_1);}
                else
                {radbtn2.setBottom(R.drawable.l2);}
            }
        });
        radbtn3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {radbtn3.setBottom(R.drawable.l3_1);}
                else
                {radbtn3.setBottom(R.drawable.l3);}
            }
        });
    }
    private  RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton:
                    radio="父母";
                    Log.e("1","1");
                    break;
                case R.id.radioButton2:
                    radio="保姆";
                    Log.e("2","2");
                    break;
                case R.id.radioButton3:
                    radio="托育中心";
                    Log.e("3","3");
                    break;

            }
        }
    };
    public void btn_login(View view)
    {
        String account="",password="";
        Cursor c = db.rawQuery("SELECT account,password FROM user", null);
        if(c.moveToFirst()){
            do{
                //assing values
                account = c.getString(0);
                password = c.getString(1);
                //Do something Here with values
                Log.e("user",account+" "+password);
                if(account.equals(editaccount.getText().toString()))
                {
                    Log.e("success","success");
                    SharedPreferences.Editor editor = setting.edit();
                    editor.putString("account",editaccount.getText().toString());
                    editor.putString("password",editpassword.getText().toString());
                    editor.putString("certification",radio);
                    Intent intent = new Intent();
                    intent.setClass(Login.this,Parentindex.class);
                    startActivity(intent);
                }
                else
                {
                    Log.e("fail","fail");
                }
            }while(c.moveToNext());

        }


    }

    public void btn_register(View view)
    {
        Intent intent = new Intent();
        intent.setClass(Login.this,Register.class);
        startActivity(intent);
    }
}
