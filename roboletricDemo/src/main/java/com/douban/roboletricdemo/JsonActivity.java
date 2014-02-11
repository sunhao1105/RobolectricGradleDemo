package com.douban.roboletricdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class JsonActivity extends Activity
{
    private TextView textView;
    private static String url = "http://www.weather.com.cn/data/cityinfo/101010100.html";
    private String result = "null result";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public);
        textView = (TextView) findViewById(R.id.publicString);
        new MyTask().execute(url);
    }
    public class MyTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params)
        {
            HttpGet httpResquest = new HttpGet(params[0]);
            String result = "";
            String cityName = "";
            String cityID = "";
            try
            {
                HttpClient httpClient = new DefaultHttpClient();
                HttpResponse httpResponse = httpClient.execute(httpResquest);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    result = EntityUtils.toString(httpResponse.getEntity());
                }
                JSONObject jsonObject = new JSONObject(result).getJSONObject("weatherinfo");
                cityName = jsonObject.getString("city");
                cityID = jsonObject.getString("cityid");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return cityID +":"+cityName;
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            textView.setText(result);
        }
    }
}
