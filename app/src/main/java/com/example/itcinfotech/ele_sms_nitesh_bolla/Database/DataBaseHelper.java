package com.example.itcinfotech.ele_sms_nitesh_bolla.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nitesh Bolla on 1/29/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ELE_SMS.db";
    public static DataBaseHelper dbInstance = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

    }


    public static DataBaseHelper getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DataBaseHelper(context);
        }
        return dbInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.create_table_message);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TABLE_MESSAGE);
        onCreate(db);
    }
}
