package com.douban.roboletricdemo;

import android.app.Activity;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import java.io.UnsupportedEncodingException;

@RunWith(RobolectricTestRunner.class)
public class JsonActivityTest
{
    @Test
    public void testCheckTextview() throws UnsupportedEncodingException
    {
        String result = "{\"weatherinfo\":{\"city\":\"sunhao\",\"cityid\":\"999999999\",\"temp1\":\"21℃\",\"temp2\":\"17℃\",\"weather\":\"霾转阵雨\",\"img1\":\"d53.gif\",\"img2\":\"n3.gif\",\"ptime\":\"11:00\"}}";
        ProtocolVersion httpProtocolVersion = new ProtocolVersion("HTTP", 1, 1);
        HttpResponse successResponse = new BasicHttpResponse(httpProtocolVersion, 200, "OK");
        successResponse.setEntity(new StringEntity(result));

        Robolectric.addHttpResponseRule("http://www.weather.com.cn/data/cityinfo/101010100.html", successResponse);
        Robolectric.runBackgroundTasks();

        Activity activity = Robolectric.buildActivity(JsonActivity.class).create().get();
        Robolectric.runUiThreadTasksIncludingDelayedTasks();
        TextView textView = (TextView) activity.findViewById(R.id.publicString);
        Assert.assertEquals("999999999:sunhao", String.valueOf(textView.getText()));
    }
}
