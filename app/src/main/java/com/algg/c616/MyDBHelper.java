package com.algg.c616;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 光光 on 2017/12/11.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_USERDATA =
            "create table userData(" +
                    " id integer primary key autoincrement,"
                    + " userName varchar(20),"
                    + " passWord varchar(20),"
                    + " nickName varchar(20))";

    private Context mContext;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onCreate(db);
    }

}