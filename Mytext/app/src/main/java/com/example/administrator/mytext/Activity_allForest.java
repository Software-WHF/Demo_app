package com.example.administrator.mytext;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.administrator.mytext.Database.CommonRequest;
import com.example.administrator.mytext.Database.CommonResponse;
import com.example.administrator.mytext.Database.Constant;
import com.example.administrator.mytext.Database.Land;
import com.example.administrator.mytext.Database.Land2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_allForest extends Activity{
    ListView lv_land;
    private ArrayList<Land2> al = null;
    String[] numbers1 = { "全部" };
    String[] numbers2 = { "区域","临安","桐庐","建德","余杭" };
    String[] numbers3 = { "形式","转让","转包" };
    String[] numbers4 = { "更多" };
    //private List<Land> landList = new ArrayList<Land>();
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case 1:
                    CommonResponse response = new CommonResponse(msg.obj.toString());
                    al = response.getDataList();
                    Adapter_land adapter = new Adapter_land(getApplicationContext(),R.layout.land_item,al);
                    lv_land.setAdapter(adapter);
                    break;

            }

        }


    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allforesty);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = null;
                    CommonRequest request = new CommonRequest();
                    URL url = new URL(Constant.URL_Product );
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(request.getJsonStr());
                    out.flush();
                    StringBuilder response = new StringBuilder();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK)
                    {
                        InputStream in = connection.getInputStream();
                        BufferedReader read = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while((line = read.readLine()) != null) {
                            response.append(line);
                        }

                    }
                    Message message = new Message();
                    message.what = 1;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }).start();



        lv_land = (ListView)findViewById(R.id.land_listView);

        lv_land.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Land2 land = al.get(i);
                Intent intent = new Intent(getApplicationContext(),DetailOfForest.class);
                intent.putExtra("land_id", land.getLand_id());
                intent.putExtra("land_position",land.getLand_position() );
                intent.putExtra("land_use",land.getLand_use() );
                intent.putExtra("land_area",land.getLand_area());
                //intent.putExtra("land_purchase_method",land.getLand_purchase_method());
                intent.putExtra("land_time",land.getLand_time());
                intent.putExtra("land_introduction",land.getLand_introduction());
                intent.putExtra("land_release_time",land.gelLand_release_time());
                intent.putExtra("land_inner_id",land.getLand_inner_id());
                intent.putExtra("land_price",land.getLand_money());
                //intent.putExtra("land_credential",land.getLand_credential());
                //intent.putExtra("land_soil_condition",land.getLand_soil_condition());
                //intent.putExtra("land_equipment",land.getLand_equipment());
                //intent.putExtra("land_environment",land.getLand_environment());
                //intent.putExtra("land_management",land.getLand_management());
                //intent.putExtra("land_policy",land.getLand_policy());
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
