package com.example.administrator.mytext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.mytext.Fragment.FragmentForForestService.Forest_service_baike;
import com.example.administrator.mytext.Fragment.FragmentForForestService.Forest_service_recommend;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Activity_forest_service extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private ImageView[] tips;
    private ImageView[] mImageViews;


    /**
     * 2个导航按钮
     * */
    private RelativeLayout rl_recommend;
    private RelativeLayout rl_baike;
    /**
     * 作为页面容器的ViewPager
     */
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    ArrayList<Fragment> fragmentList;

    /**
     * 2个Fragment（页面）
     */
    Forest_service_recommend forest_service_recommend;
    Forest_service_baike forest_service_baike;

    /**
     覆盖层
     */
    ImageView imageviewOvertab;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forset_service);

        rl_recommend = (RelativeLayout)findViewById(R.id.forest_service_recommend_RL);
        rl_baike = (RelativeLayout)findViewById(R.id.forest_service_baike_RL);
        rl_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(0);
            }
        });
        rl_baike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(1);
            }
        });

        mViewPager = (ViewPager)findViewById(R.id.forest_service_viewpager);
        fragmentList = new ArrayList<>();
        forest_service_recommend = new Forest_service_recommend();
        forest_service_baike = new Forest_service_baike();
        fragmentList.add(forest_service_recommend);
        fragmentList.add(forest_service_baike);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        rl_recommend.measure(0, 0);
        imageviewOvertab=(ImageView) findViewById(R.id.imgv_overtab11);
        FrameLayout.LayoutParams imageParams=new FrameLayout.LayoutParams(screenWidth/2, rl_recommend.getMeasuredHeight()-314);
        imageParams.topMargin = 314;
        imageviewOvertab.setLayoutParams(imageParams);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
    }

    private void imageMove(int moveToTab)
    {
        int startPosition=0;
        int movetoPosition=0;

        startPosition=currenttab*(screenWidth/2);
        movetoPosition=moveToTab*(screenWidth/2);
        //平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movetoPosition, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
        imageviewOvertab.startAnimation(translateAnimation);
    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter
    {

        public MyFrageStatePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container)
        {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem=mViewPager.getCurrentItem();
            if (currentItem==currenttab)
            {
                return ;
            }
            imageMove(mViewPager.getCurrentItem());
            currenttab=mViewPager.getCurrentItem();
        }

    }
    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        mViewPager.setCurrentItem(desTab, true);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % mImageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
}
