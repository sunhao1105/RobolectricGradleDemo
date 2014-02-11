package com.douban.roboletricdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class IntentActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        TextView textView = (TextView) findViewById(R.id.publicString);
        textView.setText(this.getIntent().getStringExtra("giveString"));
    }
}
