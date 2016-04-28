package com.example.yi_an.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yi_An on 2016/1/11.
 */
public class MyDBHelper6 extends SQLiteOpenHelper {
    private  static  final String DataBase_Name="credit";
    private  static final int DataBase_Version=1;
    public MyDBHelper6(Context context) {
        super(context,DataBase_Name,null,DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE credit (_id "+"integer primary key autoincrement, "+
                                          "account text no null, credit text,  ds text , de text, number text, certification text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS credit");
        onCreate(db);
    }
}
