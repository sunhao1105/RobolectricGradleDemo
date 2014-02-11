package com.douban.roboletricdemo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SqliteActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        TextView textView = (TextView) findViewById(R.id.publicString);
        textView.setText(getPerson("sunhao1"));
    }
    private String getPerson(String getName)
    {
        String str = "";
        SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        db.execSQL("drop table if exists person");
        db.execSQL("create table if not exists person (_id integer primary key autoincrement, name varchar, age integer)");
        for (int i=1; i<5; i++)
        {
            db.execSQL("insert into person values(null, ?, ?)", new Object[]{("sunhao" + i), (20 + i)});
        }
        Cursor cursor = db.rawQuery("select age from person where name = ?", new String[]{getName});
        if (cursor.moveToFirst())
        {
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            Log.i("tag", String.valueOf(age));
            str = String.valueOf(age);
        }
        else str = "no this name";
        cursor.close();
        db.close();
        return str;
    }
}
