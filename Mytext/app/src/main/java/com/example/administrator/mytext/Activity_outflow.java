package com.example.administrator.mytext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.mytext.Fragment.FragmentForOutflow.OutflowFragment;
import com.example.administrator.mytext.Fragment.FragmentForOutflow.ResellFragment;



import java.util.ArrayList;

public class Activity_outflow extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;
    private OutflowFragment outflowFragment = new OutflowFragment();
    private ResellFragment resellFragment = new ResellFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest_outflow);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar_outflow);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigationBar.setBackgroundStyle(
                BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.wylc,"我要流出").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.lzfw,"林转服务").setActiveColor(R.color.green))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }

    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(outflowFragment);
        fragments.add(resellFragment);
        return fragments;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame_outflow, outflowFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (outflowFragment == null) {
                    outflowFragment = new OutflowFragment();
                }
                transaction.replace(R.id.layFrame_outflow, outflowFragment);
                break;
            case 1:
                if (resellFragment == null) {
                    resellFragment = new ResellFragment();
                }
                transaction.replace(R.id.layFrame_outflow, resellFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        //     Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
