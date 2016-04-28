package com.example.yi_an.work;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class Parentindex extends AppCompatActivity {
    String credit;
    private static  String DATABASE_TABLE = "credit";
    private SQLiteDatabase db;
    private MyDBHelper6 dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentindex);


    }
    public void btn_index (View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parentindex.this,Parent1.class);
        startActivity(intent);
    }
    public void btn_babyshop (View view)
    {
//        Intent intent = new Intent();
//        intent.setClass(Parentindex.this,Parent1.class);
//        startActivity(intent);
        Toast.makeText(Parentindex.this,"尚未做完",Toast.LENGTH_SHORT).show();
    }
    public void btn_wallet (View view)
    {
        Bundle bundle = new Bundle();
        bundle.putString("credit", "wallet");
        Intent intent = new Intent();
        intent.setClass(Parentindex.this,Parent_wallet.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void btn_visitrecord (View view)
    {
        Toast.makeText(Parentindex.this,"尚未做完",Toast.LENGTH_SHORT).show();
    }
    public void btn_babycare (View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parentindex.this,Parent_babaycare.class);
        startActivity(intent);
    }
    public void btn_zing (View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parentindex.this,Zing.class);
        startActivity(intent);
    }
    public void btn_babycarer (View view)
    {
        Toast.makeText(Parentindex.this,"尚未做完",Toast.LENGTH_SHORT).show();
    }
    public void btn_babycenter (View view)
    {
        Toast.makeText(Parentindex.this,"尚未做完",Toast.LENGTH_SHORT).show();
    }
}
