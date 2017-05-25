package com.example.administrator.mytext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.mytext.Fragment.CommunityFragment;
import com.example.administrator.mytext.Fragment.HomeFragment;
import com.example.administrator.mytext.Fragment.MessageFragment;
import com.example.administrator.mytext.Fragment.MineFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment = new HomeFragment();
    private CommunityFragment communityFragment = new CommunityFragment();
    private MessageFragment messageFragment = new MessageFragment();
    private MineFragment mineFragment = new MineFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigationBar.setBackgroundStyle(
                BottomNavigationBar.BACKGROUND_STYLE_STATIC
        );

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.shouye,"首页").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.shequ,"社区").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.xiaoxi,"消息").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.wode,"我的").setActiveColor(R.color.green))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }
    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(communityFragment);
        fragments.add(messageFragment);
        fragments.add(mineFragment);
        return fragments;
    }
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, homeFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.layFrame, homeFragment);
                break;
            case 1:
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment();
                }
                transaction.replace(R.id.layFrame, communityFragment);
                break;
            case 2:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                }
                transaction.replace(R.id.layFrame, messageFragment);
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                transaction.replace(R.id.layFrame, mineFragment);
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
