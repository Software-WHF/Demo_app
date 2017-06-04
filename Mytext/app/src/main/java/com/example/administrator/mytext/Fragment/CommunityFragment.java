package com.example.administrator.mytext.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mytext.Activity_Map;
import com.example.administrator.mytext.DetailOfForest;
import com.example.administrator.mytext.MainActivity;
import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/8.
 */

public class CommunityFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private ViewPager viewpager;
    private ImageView[] tips;
    private ImageView[] mImageViews;
    private int[] imgIdArray;
    private ImageView btn_toMap,btn_huati,btn_zhuanlan;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.communityfragment, container, false);

        btn_toMap = (ImageView) view.findViewById(R.id.community_fujin);
        btn_huati = (ImageView) view.findViewById(R.id.community_huati);
        btn_zhuanlan = (ImageView) view.findViewById(R.id.community_zhuanti);
        btn_toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Activity_Map.class);
                startActivity(intent);
            }
        });

        btn_huati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("该功能正在开发中......");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",null);
                dialog.show();
            }
        });

        btn_zhuanlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("该功能正在开发中......");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",null);
                dialog.show();
            }
        });

        viewpager = (ViewPager) view.findViewById(R.id.viewpager_community);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup_community);
        imgIdArray = new int[]{ R.drawable.community_huati_pic,R.drawable.example_3,R.drawable.example_4,R.drawable.example_1};

        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }


        if (imgIdArray.length == 1) {
            mImageViews = new ImageView[2];
            for (int i = 0; i < (mImageViews.length); i++) {
                ImageView imageView = new ImageView(getContext());
                mImageViews[i] = imageView;
                imageView.setBackgroundResource(imgIdArray[0]);
            }
            group.setVisibility(View.GONE);
            viewpager.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    // TODO Auto-generated method stub
                    return true;
                }
            });
        } else if (imgIdArray.length == 2 || imgIdArray.length == 3) {
            mImageViews = new ImageView[imgIdArray.length * 2];
            for (int i = 0; i < (mImageViews.length); i++) {
                ImageView imageView = new ImageView(getContext());
                mImageViews[i] = imageView;
                imageView.setBackgroundResource(imgIdArray[(i > (imgIdArray.length - 1)) ? i - imgIdArray.length : i]);
            }
        } else {
            mImageViews = new ImageView[imgIdArray.length];
            for (int i = 0; i < mImageViews.length; i++) {
                ImageView imageView = new ImageView(getContext());
                mImageViews[i] = imageView;
                imageView.setBackgroundResource(imgIdArray[i]);
            }
        }

        viewpager.setAdapter(new CommunityFragment.MyAdapter());
        viewpager.setOnPageChangeListener(this);
        viewpager.setCurrentItem((mImageViews.length) * 100);



        return view;
    }


    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
        }
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }
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



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

}

