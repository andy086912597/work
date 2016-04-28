package com.example.yi_an.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yi_An on 2016/1/11.
 */
public class MyDBHelper1 extends SQLiteOpenHelper {
    private  static  final String DataBase_Name="food";
    private  static final int DataBase_Version=1;
    public MyDBHelper1(Context context) {
        super(context,DataBase_Name,null,DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE milk (_id "+"integer primary key autoincrement, "+
                                          "account text no null, milk text, date text, certification text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS milk");
        onCreate(db);
    }
}
