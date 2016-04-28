package com.example.yi_an.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Parent_babaycare extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_babaycare);
    }
    public void btn_menu(View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parent_babaycare.this,Parentindex.class);
        startActivity(intent);
    }
    public void btn_babycare1(View view)
    {
        Intent intent = new Intent();
        intent.setClass(Parent_babaycare.this,Parent_babycare1.class);
        startActivity(intent);
    }
    public void btn_babycare2(View view)
    {

    }
}
