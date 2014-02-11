package com.douban.roboletricdemo;


import android.content.Intent;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class IntentActivityTest
{
    @Test
    public void testIntent()
    {
        Intent intent = new Intent().putExtra("giveString", "sunhao");
        IntentActivity intentActivity = Robolectric.buildActivity(IntentActivity.class).create().get();
        intentActivity.setIntent(intent);
        Robolectric.shadowOf(intentActivity).callOnCreate(null);
        TextView textView = (TextView) intentActivity.findViewById(R.id.publicString);
        assertEquals("sunhao", String.valueOf(textView.getText()));
    }
}