package com.example.administrator.mytext.Database;

/**
 * Created by Administrator on 2017/3/11.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Main_operate {
    public ArrayList<Land> Select_All_Land(My_database my_db)
    {
        ArrayList<Land> all_land = new ArrayList<>();
        SQLiteDatabase db = my_db.getWritableDatabase();
        Cursor cusor = db.query("land",null,null,null,null,null,null);
        if(cusor.moveToFirst())
        {
            do {
                String land_id = cusor.getString(cusor.getColumnIndex("land_id"));
                String land_position = cusor.getString(cusor.getColumnIndex("land_position"));
                String land_use = cusor.getString(cusor.getColumnIndex("land_use"));
                String land_area = cusor.getString(cusor.getColumnIndex("land_area"));
                String land_time = cusor.getString(cusor.getColumnIndex("land_time"));
                String land_purchase_method = cusor.getString(cusor.getColumnIndex("land_purchase_method"));
                String land_introduction = cusor.getString(cusor.getColumnIndex("land_introduction"));
                String land_credential = cusor.getString(cusor.getColumnIndex("land_credential"));
                String land_soil_condition = cusor.getString(cusor.getColumnIndex("land_soil_condition"));
                String land_equipment = cusor.getString(cusor.getColumnIndex("land_equipment"));
                String land_environment = cusor.getString(cusor.getColumnIndex("land_environment"));
                String land_management = cusor.getString(cusor.getColumnIndex("land_management"));
                String land_policy = cusor.getString(cusor.getColumnIndex("land_policy"));
                int land_imageId = cusor.getInt(cusor.getColumnIndex("land_imageId"));
                Land land = new Land(land_id,land_position,land_use,land_area,land_time,land_purchase_method,land_introduction,land_credential,land_soil_condition,land_equipment,land_environment,land_management,land_policy,land_imageId);
                all_land.add(land);
            }while(cusor.moveToNext());
        }
        return all_land;
    }

    public void Insert_Land(My_database my_db,Land land)
    {
        SQLiteDatabase db = my_db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("land_id",land.getLand_id());
        values.put("land_position",land.getLand_position());
        values.put("land_area",land.getLand_area());
        values.put("land_time",land.getLand_time());
        values.put("land_purchase_method",land.getLand_purchase_method());
        values.put("land_introduction",land.getLand_introduction());
        values.put("land_credential",land.getLand_credential());
        values.put("land_soil_condition",land.getLand_soil_condition());
        values.put("land_equipment",land.getLand_equipment());
        values.put("land_environment",land.getLand_environment());
        values.put("land_management",land.getLand_management());
        values.put("land_policy",land.getLand_policy());
        values.put("land_imageID",land.getLand_imageId());
        db.insert("land",null,values);
        values.clear();
    }

}

