package com.example.administrator.mytext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

import java.net.Inet4Address;

/**
 * Created by Administrator on 2017/3/19.
 */

public class Activity_Search extends Activity {

    private EditText min_price,max_price,your_district,other_need;
    private RadioGroup rg_purpose,rg_style,rg_area,rg_year;
    private RadioButton rb_purpose,rb_style,rb_area,rb_year;
    private TextView search_complete;
    String string_purpose,string_style,string_area,string_year;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    void init()
    {
        min_price = (EditText)findViewById(R.id.ET_min_price);
        max_price = (EditText)findViewById(R.id.ET_max_price);
        your_district = (EditText)findViewById(R.id.ET_your_district);
        other_need = (EditText)findViewById(R.id.ET_search_other);
        search_complete = (TextView)findViewById(R.id.search_complete);

        rg_area = (RadioGroup)findViewById(R.id.RG_area);
        rg_area.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rb_area = (RadioButton)findViewById(rg_area.getCheckedRadioButtonId());
                string_area = rb_area.getText().toString();
            }
        });


        rg_purpose = (RadioGroup)findViewById(R.id.RG_purpose);
        rg_purpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rb_purpose = (RadioButton)findViewById(rg_purpose.getCheckedRadioButtonId());
                string_purpose = rb_purpose.getText().toString();
            }
        });

        rg_style = (RadioGroup)findViewById(R.id.RG_style);
        rg_style.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rb_style = (RadioButton)findViewById(rg_style.getCheckedRadioButtonId());
                string_style = rb_style.getText().toString();
            }
        });

        rg_year = (RadioGroup)findViewById(R.id.RG_year);
        rg_year.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                rb_year = (RadioButton)findViewById(rg_year.getCheckedRadioButtonId());
                string_year = rb_year.getText().toString();
            }
        });

        search_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Activity_search_result.class);
                intent.putExtra("min_price",min_price.getText().toString().trim());
                intent.putExtra("max_price",max_price.getText().toString().trim());
                intent.putExtra("your_district",your_district.getText().toString().trim());
                intent.putExtra("other_need",other_need.getText().toString().trim());
                intent.putExtra("purpose",string_purpose);
                intent.putExtra("style",string_style);
                intent.putExtra("area",string_area);
                intent.putExtra("year",string_year);

                startActivity(intent);
            }
        });

    }
    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
