package com.douban.roboletricdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private TextView giveText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goSqliteActivity = (Button) findViewById(R.id.goSqlite);
        goSqliteActivity.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SqliteActivity.class));
            }
        });
        Button goJsonActivity = (Button) findViewById(R.id.goJson);
        goJsonActivity.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, JsonActivity.class));
            }
        });
        giveText = (TextView) findViewById(R.id.giveString);
        Button goIntent = (Button) findViewById(R.id.goIntent);
        goIntent.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("giveString", String.valueOf(giveText.getText()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public int add(int a, int b)
    {
        return a+b;
    }
}
