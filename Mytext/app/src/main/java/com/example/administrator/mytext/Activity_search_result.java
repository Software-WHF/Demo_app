package com.example.administrator.mytext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytext.Adapter.Adapter_land;
import com.example.administrator.mytext.Database.CommonRequest;
import com.example.administrator.mytext.Database.CommonResponse;
import com.example.administrator.mytext.Database.Constant;
import com.example.administrator.mytext.Database.Land2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26.
 */

public class Activity_search_result extends Activity {
    ListView lv_land;
    private ArrayList<Land2> al = null;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
      //  TextView textView  = (TextView) findViewById(R.id.result_search);
        lv_land = (ListView) findViewById(R.id.lv_search_result);
        Intent intent = getIntent();
        String min_price = intent.getStringExtra("min_price");
        String max_price = intent.getStringExtra("max_price");
        String your_district = intent.getStringExtra("your_district");
        String other_need = intent.getStringExtra("other_need");
        String purpose = intent.getStringExtra("purpose");
        String style = intent.getStringExtra("style");
        String area = intent.getStringExtra("area");
        String year = intent.getStringExtra("year");

    //    textView.setText(min_price+max_price+your_district+other_need+purpose+style+area+year);

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
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }}
