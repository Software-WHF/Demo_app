package com.example.administrator.mytext;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.mytext.Adapter.Adapter_land;
import com.example.administrator.mytext.Database.Land;

import java.util.ArrayList;
import java.util.List;

public class Activity_allForest extends Activity{
    ListView lv_land;
    String[] numbers1 = { "全部" };
    String[] numbers2 = { "区域","临安","桐庐","建德","余杭" };
    String[] numbers3 = { "形式","转让","转包" };
    String[] numbers4 = { "更多" };
    private List<Land> landList = new ArrayList<Land>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allforesty);
//        final My_database my_db = new My_database(getApplicationContext(), "1.db",null,1);
//        final Main_operate operate = new Main_operate();
//
//        final ArrayList<Land> al = operate.Select_All_Land(my_db);
//        Adapter_land adapter = new Adapter_land(getApplicationContext(),R.layout.land_item,al);

        initLands();
        Adapter_land adapter = new Adapter_land(getApplicationContext(),R.layout.land_item,landList);
        lv_land = (ListView)findViewById(R.id.land_listView);
        lv_land.setAdapter(adapter);
        lv_land.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Land land = landList.get(position);
                Intent intent = new Intent(getApplicationContext(),DetailOfForest.class);
                intent.putExtra("land_id", land.getLand_id());
                intent.putExtra("land_position",land.getLand_position() );
                intent.putExtra("land_use",land.getLand_use() );
                intent.putExtra("land_area",land.getLand_area());
                intent.putExtra("land_purchase_method",land.getLand_purchase_method());
                intent.putExtra("land_time",land.getLand_time());
                intent.putExtra("land_introduction",land.getLand_introduction());
                intent.putExtra("land_credential",land.getLand_credential());
                intent.putExtra("land_soil_condition",land.getLand_soil_condition());
                intent.putExtra("land_equipment",land.getLand_equipment());
                intent.putExtra("land_environment",land.getLand_environment());
                intent.putExtra("land_management",land.getLand_management());
                intent.putExtra("land_policy",land.getLand_policy());
                startActivity(intent);
            }
        });
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);

        SpinnerAdapter spinneradapter1 = new SpinnerAdapter(this,android.R.layout.simple_spinner_item, numbers1);
        SpinnerAdapter spinneradapter2 = new SpinnerAdapter(this,android.R.layout.simple_spinner_item, numbers2);
        SpinnerAdapter spinneradapter3 = new SpinnerAdapter(this,android.R.layout.simple_spinner_item, numbers3);
        SpinnerAdapter spinneradapter4 = new SpinnerAdapter(this,android.R.layout.simple_spinner_item, numbers4);

        spinner1.setAdapter(spinneradapter1);
        spinner2.setAdapter(spinneradapter2);
        spinner3.setAdapter(spinneradapter3);
        spinner4.setAdapter(spinneradapter4);
    }

    private void initLands()
    {
        Land land1 = new Land("100亩荒山转让",
                "杭州 临安市",
                "苗圃种植",
                "100亩",
                "12年",
                "转让",
                "适合各类作物种植，种养殖都可以！感兴趣的老板欢迎来咨询。",
                "林权证",
                "壤土",
                "供水：已接通  供电：已接通  进入道路：林道  机械化程度：无",
                "高速：0—10km \n机场： 50—100km  \n铁路：50—100km \n港口：50—100km",
                "地块适合何种经营：苗圃、林木种植 \n" +
                        "当地劳动力资源：充裕\n" +
                        "当地农民主要收入来源：在家种养\n" +
                        "当地农民人均年纯收入：8000元\n" +
                        "地块利用现状：种植\n" +
                        "地块产出：无",
                "良种补贴/农资补贴/农机购置补贴/农机报废更新补贴/土壤有机质提升补助",
                R.drawable.test1);
        landList.add(land1);

        Land land2 = new Land("杭州天目山景区口2000亩林地转让",
                "杭州 临安市",
                "林木种植",
                "2000亩",
                "70年",
                "转让",
                "杭州天目山景区口有林地近2000亩，有多年生长杉木等。有意向者请与本人联系。",
                "林权证",
                "壤土",
                "水：暂未接通，但可接通  供电：已接通  进入道路：水泥路  机械化程度：无",
                "高速公路：10—50km\n机场： 无  \n铁路：无 \n港口：无",
                "地块适合何种经营：苗圃、林木种植 \n" +
                        "当地劳动力资源：充裕\n" +
                        "当地农民主要收入来源：在家种养\n" +
                        "当地农民人均年纯收入：8000元\n" +
                        "地块利用现状：种植\n" +
                        "地块产出：无",
                        "良种补贴/农资补贴/农机购置补贴/农机报废更新补贴/土壤有机质提升补助",
                R.drawable.test2);
        landList.add(land2);

        Land land3 = new Land("临安岛石山核桃林转包",
                "杭州 临安市",
                "山核桃种植",
                "8亩",
                "5年",
                "转包",
                "位于临安市岛石镇，到杭州3个小时车程左右。8亩多年生山核桃林，本人有事无法妥善经营故转包，有意愿者请与我联系。",
                "林权证",
                "壤土",
                "供水：暂未接通，但可接通  供电：无  进入道路：林道  机械化程度：采摘专业机械作业",
                "高速公路：10—50km\n机场： 无  \n铁路：无 \n港口：无",
                "地块适合何种经营：山核桃种植 \n" +
                        "当地劳动力资源：充裕\n" +
                        "当地农民主要收入来源：在家种养\n" +
                        "当地农民人均年纯收入：8000元\n" +
                        "地块利用现状：种植\n" +
                        "地块产出：无",
                "良种补贴/农资补贴/农机购置补贴/农机报废更新补贴/土壤有机质提升补助",
                R.drawable.test3);
        landList.add(land3);
    }
    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
    private class SpinnerAdapter extends ArrayAdapter<String> {
        Context context;
        String[] items = new String[] {};

        public SpinnerAdapter(final Context context,
                              final int textViewResourceId, final String[] objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.context = context;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items[position]);
            tv.setGravity(Gravity.CENTER);
//          tv.setTextColor(Color.BLUE);
            tv.setTextSize(18);
            return convertView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            // android.R.id.text1 is default text view in resource of the android.
            // android.R.layout.simple_spinner_item is default layout in resources of android.

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items[position]);
            tv.setGravity(Gravity.CENTER);
//            tv.setTextColor(Color.BLUE);
            tv.setTextSize(13);
            return convertView;
        }
    }
}
