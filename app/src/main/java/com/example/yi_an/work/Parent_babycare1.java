package com.example.yi_an.work;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Parent_babycare1 extends Activity {
    private static  String DATABASE_TABLE = "babycare1";
    private SQLiteDatabase db;
    private MyDBHelper7 dbHelper;
    private String[] babycare;
    private String[] babycare2;
    private int r=0;
    private ListView listbabycare;
    private String babycare1="";
    private String babycare12="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_babycare1);
        dbHelper = new MyDBHelper7(this);
        db = dbHelper.getWritableDatabase();
        String[] colNames = new String[]{"_id","title","answer"};
        String str="";
        String str1="";
        Cursor c = db.query(DATABASE_TABLE,colNames,null,null,null,null,null);
        c.moveToNext();
        babycare=new String[c.getCount()];
        babycare2=new String[c.getCount()];
        r=c.getCount();
        for(int i=0;i<c.getCount();i++)
        {
            str+=c.getString(1);
            babycare[i]=str;
            str1+=c.getString(1);
            babycare2[i]=str1;
            c.moveToNext();
            str1="";
            str="";
        }
        listbabycare = (ListView) findViewById(R.id.listbabycare1);
        listbabycare.setAdapter(new MyAdapter());
    }
    private class MyAdapter extends BaseAdapter {
        private ArrayList<String> mList = new ArrayList<String>();
        private ArrayList<String> mList1 = new ArrayList<String>();
        public MyAdapter(){
            for(int i = 0; i < r; i++){
                mList.add(babycare[i]);
                mList1.add(babycare2[i]);
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
                v = LayoutInflater.from(Parent_babycare1.this).inflate(R.layout.list_babycare1, null);
                holder = new Holder();
                holder.txttitle = (TextView) v.findViewById(R.id.textView29);
                holder.txtanswer= (TextView) v.findViewById(R.id.textView30);
                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.txttitle.setText(mList.get(position) + "");
            holder.txtanswer.setText(mList1.get(position) + "");
            v.setBackgroundColor(Color.WHITE);
            return v;
        }
        class Holder{
           TextView txttitle,txtanswer;

        }
    }
    public void btn_menu(View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parent_babycare1.this,Parentindex.class);
        startActivity(intent);
    }
    public void btn_reply(View view)
    {
        final EditText editText,editText1;
        LayoutInflater inflater = LayoutInflater.from(Parent_babycare1.this);
        View reply_view = inflater.inflate(R.layout.replylayout, null);
        AlertDialog.Builder editDialog = new AlertDialog.Builder(Parent_babycare1.this);
        editDialog.setTitle("--- 發文 ---");
        editDialog.setView(reply_view);
        editText= (EditText)reply_view.findViewById(R.id.editText6);
        editText1=(EditText)reply_view.findViewById(R.id.editText7);
        editDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
                long id;

                ContentValues cv = new ContentValues();

                cv.put("title",editText.getText().toString());
                cv.put("answer", editText1.getText().toString());

                Log.d("title", editText.getText().toString());
                id = db.insert(DATABASE_TABLE,null,cv);
                Log.d("db", ("新增記錄成功: " + id));
                Intent intent = new Intent();
                intent.setClass(Parent_babycare1.this,Parent_babycare1.class);
                startActivity(intent);

            }
        });
        editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
                //...
            }
        });
        editDialog.show();
    }

}
