package com.douban.roboletricdemo;

import android.widget.Button;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Robolectric.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest
{
    private MainActivity mainActivity;

    @Before
    public void setUp()
    {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testCheckHomeString()
    {
        TextView homeString = (TextView) mainActivity.findViewById(R.id.homeString);
        assertEquals("My Module", String.valueOf(homeString.getText()));
    }

    @Test
    public void testCheckJsonActivity()
    {
        Button goJson = (Button) mainActivity.findViewById(R.id.goJson);
        goJson.performClick();
        ShadowActivity shadowActivity = shadowOf(mainActivity);
        ShadowIntent shadowIntent = shadowOf(shadowActivity.getNextStartedActivity());
        assertEquals(shadowIntent.getComponent().getClassName(), JsonActivity.class.getName());
    }

    @Test
    public void testCheckIntentActivity()
    {
        Button goIntent = (Button) mainActivity.findViewById(R.id.goIntent);
        goIntent.performClick();
        ShadowActivity shadowActivity = shadowOf(mainActivity);
        ShadowIntent shadowIntent = shadowOf(shadowActivity.getNextStartedActivity());
        assertEquals(shadowIntent.getComponent().getClassName(), IntentActivity.class.getName());
    }

    @Test
    public void testCheckSqliteActivity()
    {
        Button goSqlite = (Button) mainActivity.findViewById(R.id.goSqlite);
        goSqlite.performClick();
        ShadowActivity shadowActivity = shadowOf(mainActivity);
        ShadowIntent shadowIntent = shadowOf(shadowActivity.getNextStartedActivity());
        assertEquals(shadowIntent.getComponent().getClassName(), SqliteActivity.class.getName());
    }

    @Test
    public void testAdd()
    {
        assertEquals(3, new MainActivity().add(1, 2));
    }
}
