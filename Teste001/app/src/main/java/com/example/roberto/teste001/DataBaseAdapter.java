package com.example.roberto.teste001;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseAdapter extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "SmartHome.DB";

    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE dispositivos" + "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " + "estado BOOLEAN, " + "SerialNumber INTEGER)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "CREATE TABLE IF EXISTS dispositivos";
        db.execSQL(sql);
        onCreate(db);



    }
}
