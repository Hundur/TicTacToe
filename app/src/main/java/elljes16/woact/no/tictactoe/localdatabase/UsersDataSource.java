package elljes16.woact.no.tictactoe.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsersDataSource
{
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String [] allColumns = { SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_USER};

    public UsersDataSource(Context context)
    {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close ()
    {
        dbHelper.close();
    }

    public User createUser(String user)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USER, user);

        long insertId = database.insert(SQLiteHelper.TABLE_USERS,null, values);

        Cursor cursor = database.query(
                SQLiteHelper.TABLE_USERS,
                allColumns,
                SQLiteHelper.COLUMN_ID + " = " + insertId,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public ArrayList<String> getAllUsernames()
    {
        ArrayList<String> users = new ArrayList<String>();

        Cursor cursor = database.query(
                SQLiteHelper.TABLE_USERS,
                allColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            User user = cursorToUser(cursor);
            users.add(user.getName());
            cursor.moveToNext();
        }

        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor)
    {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        return user;
    }
}
