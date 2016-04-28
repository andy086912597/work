package com.example.yi_an.work;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText edituseraccount,edituserpassword,edituserpasscheck;
    private ImageButton imgbtn1,imgbtn2,imgbtn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        edituseraccount = (EditText)findViewById(R.id.editText);
        edituserpassword = (EditText)findViewById(R.id.editText2);
        edituserpasscheck = (EditText)findViewById(R.id.editText3);
        imgbtn1 = (ImageButton)findViewById(R.id.imageButton);
        imgbtn2 = (ImageButton)findViewById(R.id.imageButton2);
        imgbtn3 = (ImageButton)findViewById(R.id.imageButton3);

    }
    public void btn_registernext(View view)
    {
       // if(edituseraccount.getText().toString()!=null && edituserpassword.getText().toString()!=null && edituserpassword.getText().toString()==edituserpasscheck.getText().toString()) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("account", edituseraccount.getText().toString());
            bundle.putString("password", edituserpassword.getText().toString());
            intent.putExtras(bundle);
            intent.setClass(Register.this, Register2.class);
            startActivity(intent);
     //   }
      //  else {
            if(edituseraccount.getText().toString()==null){Toast.makeText(Register.this,"account 錯誤",Toast.LENGTH_SHORT).show();}
            if(edituserpassword.getText().toString()==null){Toast.makeText(Register.this,"password 錯誤",Toast.LENGTH_SHORT).show();}
           // if(edituserpassword.getText().toString()!=edituserpasscheck.getText().toString()){Toast.makeText(Register.this,"password 不一致",Toast.LENGTH_SHORT).show();}
            Log.e("error",edituseraccount.getText().toString()+" "+edituserpassword.getText().toString()+" "+edituserpasscheck.getText().toString());
       // }

    }
}
