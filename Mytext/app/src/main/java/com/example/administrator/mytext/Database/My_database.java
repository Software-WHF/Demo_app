package com.example.administrator.mytext.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class My_database extends SQLiteOpenHelper{

    private Context my_context;
    public static final String Create_land = "create table land ("
            + "land_id text primary key not null ,"
            + "land_position text ,"
            + "land_area text ,"
            + "land_time text ,"
            + "land_purchase_method text ,"
            + "land_introduction text ,"
            + "land_credential text ,"
            + "land_soil_condition text ,"
            + "land_equipment text ,"
            + "land_environment text ,"
            + "land_management text ,"
            + "land_policy text)";

    public My_database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        my_context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_land);
        Toast.makeText(my_context, "数据库创建成功", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

