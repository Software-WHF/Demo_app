package com.example.administrator.mytext;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/19.
 */

public class Activity_Search extends Activity {

    private EditText min_price,max_price,your_district,other_need;
    private RadioGroup rg_purpose,rg_style,rg_area,rg_year;
    private RadioButton rb_purpose,rb_style,rb_area,rb_year;
    String string_purpose,string_style,string_area,string_year;
    Button btntest;

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


    }
    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
