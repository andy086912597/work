package com.example.yi_an.work;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Parent1 extends FragmentActivity {
    private ListView listsearch;
    private String search [] ={"餵奶","附食品","點心、水果","排便","尿布"};
    private static  String DATABASE_TABLE = "milk";
    private SQLiteDatabase db;
    private MyDBHelper1 dbHelper;
    private static  String DATABASE_TABLE2 = "vice";
    private SQLiteDatabase db2;
    private MyDBHelper2 dbHelper2;
    private static  String DATABASE_TABLE3 = "fruit";
    private SQLiteDatabase db3;
    private MyDBHelper3 dbHelper3;
    private static  String DATABASE_TABLE4 = "shit";
    private SQLiteDatabase db4;
    private MyDBHelper4 dbHelper4;
    private static  String DATABASE_TABLE5 = "diaper";
    private SQLiteDatabase db5;
    private MyDBHelper5 dbHelper5;
    String [] milk;
    String [] vice;
    String [] fruit;
    String [] shit;
    String [] diaper;
    int r=0,r1=0,r2=0,r3=0,r4=0,r5=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent1);
        dbHelper = new MyDBHelper1(this);
        db = dbHelper.getWritableDatabase();
        dbHelper2 = new MyDBHelper2(this);
        db2 = dbHelper2.getWritableDatabase();
        dbHelper3 = new MyDBHelper3(this);
        db3 = dbHelper3.getWritableDatabase();
        dbHelper4 = new MyDBHelper4(this);
        db4 = dbHelper4.getWritableDatabase();
        dbHelper5 = new MyDBHelper5(this);
        db5 = dbHelper5.getWritableDatabase();
        String[] colNames = new String[]{"_id","date"};
        String str="";
        Cursor c = db.query(DATABASE_TABLE,colNames,null,null,null,null,null);
        c.moveToNext();
        milk=new String[c.getCount()+1];
        r=c.getCount();
        milk[c.getCount()]=r+"";
        for(int i=0;i<c.getCount();i++)
        {
            str+=c.getString(1);
            milk[i]=str;
            c.moveToNext();
            str="";
        }
        Cursor c2 = db2.query(DATABASE_TABLE2,colNames,null,null,null,null,null);
        c2.moveToNext();
        vice=new String[c2.getCount()+1];
        r1=c2.getCount();
        vice[c2.getCount()]=r1+"";
        for(int i=0;i<c2.getCount();i++)
        {
            str+=c2.getString(2);
            vice[i]=str;
            c2.moveToNext();
            str="";
        }
        Cursor c3 = db3.query(DATABASE_TABLE3,colNames,null,null,null,null,null);
        c3.moveToNext();
        fruit=new String[c3.getCount()+1];
        r2=c3.getCount();
        fruit[c3.getCount()]=r2+"";
        for(int i=0;i<c3.getCount();i++)
        {
            str+=c3.getString(2);
            fruit[i]=str;
            c3.moveToNext();
            str="";
        }
        Cursor c4 = db4.query(DATABASE_TABLE4,colNames,null,null,null,null,null);
        c4.moveToNext();
        shit=new String[c4.getCount()+1];
        r3=c4.getCount();
        shit[c4.getCount()]=r3+"";
        for(int i=0;i<c4.getCount();i++)
        {
            str+=c4.getString(2);
            shit[i]=str;
            c4.moveToNext();
            str="";
        }
        Cursor c5 = db5.query(DATABASE_TABLE5,colNames,null,null,null,null,null);
        c5.moveToNext();
        diaper=new String[c5.getCount()+1];
        r4=c5.getCount();
        diaper[c5.getCount()]=r4+"";
        for(int i=0;i<c5.getCount();i++)
        {
            str+=c5.getString(2);
            diaper[i]=str;
            c5.moveToNext();
            str="";
        }
        listsearch = (ListView) findViewById(R.id.list);
        listsearch.setAdapter(new MyAdapter());
        listsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "你選擇的是" + search[position], Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                }
            }
        });
        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec homeSpec = host.newTabSpec("search"); // This param will
        // be used as tabId.
        homeSpec.setIndicator(null, // This param will diplay as title.
                getResources().getDrawable(R.drawable.l1));
        homeSpec.setContent(R.id.tab1);
        host.addTab(homeSpec);

        TabHost.TabSpec garbageSpec = host.newTabSpec("addtime");
        garbageSpec.setIndicator(null, getResources().getDrawable(R.drawable.l2));
        garbageSpec.setContent(R.id.tab2);
        host.addTab(garbageSpec);

        TabHost.TabSpec maybeSpec = host.newTabSpec("additem");
        maybeSpec.setIndicator(null, getResources().getDrawable(R.drawable.l3));
        maybeSpec.setContent(R.id.tab3);
        host.addTab(maybeSpec);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast toast = Toast.makeText(Parent1.this, tabId, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 50);
                toast.show();
            }
        });

    }

    private class MyAdapter extends BaseAdapter {
        private ArrayList<String> mList = new ArrayList<String>();
        private ArrayList<String> mList2 = new ArrayList<String>();
        private ArrayList<String> mList3 = new ArrayList<String>();



        public MyAdapter(){
            for(int i = 0; i < 5; i++){
                mList.add(search[i]);
            }
            mList2.add(milk[r]);
            mList2.add(vice[r1]);
            mList2.add(fruit[r2]);
            mList2.add(shit[r3]);
            mList2.add(diaper[r4]);

            if(r>0) {
                mList3.add(milk[r - 1]);
            }
            else{mList3.add("");}
            if(r1>0) {
                mList3.add(vice[r1 - 1]);
            }
            else{mList3.add("");}
            if(r2>0) {
                mList3.add(fruit[r2 - 1]);
            }
            else{mList3.add("");}
            if(r3>0) {
                mList3.add(shit[r3 - 1]);
            }
            else{mList3.add("");}
            if(r4>0) {
                mList3.add(diaper[r4 - 1]);
            }
            else{mList3.add("");}
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
                v = LayoutInflater.from(Parent1.this).inflate(R.layout.list_item, null);
                holder = new Holder();
                holder.text = (TextView) v.findViewById(R.id.text);
                holder.text1 = (TextView)v.findViewById(R.id.textView14);
                holder.text2 = (TextView)v.findViewById(R.id.textView16);

                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.text.setText(mList.get(position) + "");
            holder.text1.setText(mList2.get(position)+"");
            holder.text2.setText(mList3.get(position) + "");
            v.setBackgroundColor(Color.WHITE);
            return v;
        }
        class Holder{
            TextView text,text1,text2;

        }
    }
    public void btn_addmilk(View view)
    {   Intent intent = new Intent();
        intent.setClass(Parent1.this,Parent_Milk.class);
        startActivity(intent);
    }
    public void btn_addvice(View view)
    {   Intent intent = new Intent();
        intent.setClass(Parent1.this,Parent_Vice.class);
        startActivity(intent);}
    public void btn_addfruit(View view)
    {   Intent intent = new Intent();
        intent.setClass(Parent1.this,Parent_Fruit.class);
        startActivity(intent);}
    public void btn_addshit(View view)
    {   Intent intent = new Intent();
        intent.setClass(Parent1.this,Parent_Shit.class);
        startActivity(intent);}
    public void btn_adddiaper(View view)
    {   Intent intent = new Intent();
        intent.setClass(Parent1.this,Parent_Diaper.class);
        startActivity(intent);}


}

