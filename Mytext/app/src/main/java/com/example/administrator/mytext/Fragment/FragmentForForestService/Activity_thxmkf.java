package com.example.administrator.mytext.Fragment.FragmentForForestService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Activity_thxmkf extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thxmkf);
    }
    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
