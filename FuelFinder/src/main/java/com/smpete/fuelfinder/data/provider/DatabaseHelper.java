package com.smpete.fuelfinder.data.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.smpete.fuelfinder.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fuelfinder.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_STATION = "CREATE TABLE IF NOT EXISTS "
            + StationColumns.TABLE_NAME + " ( "
            + StationColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + StationColumns.NAME + " TEXT, "
            + StationColumns.ADDRESS + " TEXT, "
            + StationColumns.CITY + " TEXT, "
            + StationColumns.STATE + " TEXT, "
            + StationColumns.ZIP + " TEXT, "
            + StationColumns.PHONE + " TEXT, "
            + StationColumns.ACCESS_TIME + " TEXT, "
            + StationColumns.GEOCODE_STATUS + " TEXT, "
            + StationColumns.FUEL_TYPE + " TEXT, "
            + StationColumns.LATITUDE + " FLOAT, "
            + StationColumns.LONGITUDE + " FLOAT, "
            + StationColumns.UPDATED_AT + " INTEGER "
            + " );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_STATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
