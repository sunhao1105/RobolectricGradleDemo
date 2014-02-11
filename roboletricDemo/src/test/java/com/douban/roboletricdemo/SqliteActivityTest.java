package com.douban.roboletricdemo;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.TextView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.robolectric.Robolectric.shadowOf;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SqliteActivityTest
{
    private SQLiteDatabase database;
    private SQLiteStatement statement;

    @Before
    public void setUp()
    {
        database = SQLiteDatabase.openDatabase("path", null, 0);
        statement = database.compileStatement("create table if not exists person (_id integer primary key autoincrement, name varchar, age integer)");
        statement.execute();
        statement = database.compileStatement("insert into person values(null, ?, ?)");
        statement.bindString(1, "test1");
        statement.bindLong(2, 51);
        statement.executeInsert();
        statement.clearBindings();
        statement.bindString(1, "test2");
        statement.bindLong(2, 52);
        statement.executeInsert();
    }

    @After
    public void tearDown()
    {
        database.close();
    }

    @Test
    public void testCheckData() throws SQLException
    {
        Activity activity = Robolectric.buildActivity(SqliteActivity.class).create().get();
        TextView textView = (TextView) activity.findViewById(R.id.publicString);
        Statement statement = shadowOf(database).getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from person where name = 'test1'");
        textView.setText(String.valueOf(resultSet.getInt(3)));
        assertEquals("51", String.valueOf(textView.getText()));
    }
}
