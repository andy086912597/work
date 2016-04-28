package com.example.yi_an.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yi_An on 2016/1/11.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private  static  final String DataBase_Name="User";
    private  static final int DataBase_Version=1;
    public MyDBHelper(Context context) {
        super(context,DataBase_Name,null,DataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (_id "+"integer primary key autoincrement, "+
                                          "account text no null, password text, certification text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
