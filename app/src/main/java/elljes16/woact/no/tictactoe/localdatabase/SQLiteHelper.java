package elljes16.woact.no.tictactoe.localdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by grntor
 */

public class SQLiteHelper extends SQLiteOpenHelper
{
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "user";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_USER
            + " text not null);";

    public SQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(SQLiteHelper.class.getName(), "Metode for å oppgradere DB");
        db.execSQL("DROP TABLE If EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
