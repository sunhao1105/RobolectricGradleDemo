package com.example.myapp;

import android.app.Activity;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest
{

    @Test
    public void testCheckStr()
    {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        TextView textView = (TextView) activity.findViewById(R.id.homeString);
        Assert.assertEquals("MyApp", String.valueOf(textView.getText()));
    }
}
