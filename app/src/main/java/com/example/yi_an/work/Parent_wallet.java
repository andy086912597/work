package com.example.yi_an.work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Parent_wallet extends Activity {
    private static  String DATABASE_TABLE = "credit";
    private SQLiteDatabase db;
    private MyDBHelper6 dbHelper;
    private String[] credit;
    private int r=0;
    private ListView listcredit;
    private String credit1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_wallet);
        dbHelper = new MyDBHelper6(this);
        db = dbHelper.getWritableDatabase();
        Bundle bundle = this.getIntent().getExtras();
        credit1=bundle.getString("credit");
        if(!credit1.equals("wallet"))
        {
            long id;
            ContentValues cv = new ContentValues();

            SharedPreferences settings = getSharedPreferences("setting",0);
            String account = settings.getString("account", "root");
            String certification = settings.getString("certification","root");
            cv.put("credit",credit1);
            cv.put("account",account);
            cv.put("certification",certification);
            Log.d("credit", credit1);
            id = db.insert(DATABASE_TABLE,null,cv);
            Log.d("db", ("新增記錄成功: " + id));
        }
        String[] colNames = new String[]{"_id","account","credit","ds","de","number"};
        String str="";
        Cursor c = db.query(DATABASE_TABLE,colNames,null,null,null,null,null);
        c.moveToNext();
        credit=new String[c.getCount()];
        r=c.getCount();
        for(int i=0;i<c.getCount();i++)
        {
            str+=c.getString(2);
            credit[i]=str;
            c.moveToNext();
            str="";
        }
        listcredit = (ListView) findViewById(R.id.listcredit);
        listcredit.setAdapter(new MyAdapter());
    }
    public void  btn_addcredit(View view)
    { try {
        Context myContext = this.createPackageContext("com.devmarvel.creditcardentrydemo", 0);
        Intent intent = new Intent();
        intent.setPackage("com.devmarvel.creditcardentrydemo");
        intent.setClassName(myContext, "com.devmarvel.creditcardentrydemo.MainActivity");
        intent.setAction("android.intent.action.MAIN");
        this.startActivity(intent);

    }catch (Exception e)
    { // TODO Auto-generated catch block
        e.printStackTrace();}}
    private class MyAdapter extends BaseAdapter {
        private ArrayList<String> mList = new ArrayList<String>();

        public MyAdapter(){
            for(int i = 0; i < r; i++){
                mList.add(credit[i]);
            }
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(Parent_wallet.this).inflate(R.layout.list_credit, null);
                holder = new Holder();
                holder.editcredit = (EditText) v.findViewById(R.id.editText5);
                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.editcredit.setText(mList.get(position) + "");
            v.setBackgroundColor(Color.WHITE);
            return v;
        }
        class Holder{
            EditText editcredit;

        }
    }
}
