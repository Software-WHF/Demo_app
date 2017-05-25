package com.example.administrator.mytext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2017/3/19.
 */

public class Activity_outflow_publish extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfolw_publish);
    }
    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
