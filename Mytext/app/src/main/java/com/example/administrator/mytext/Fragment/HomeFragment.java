package com.example.administrator.mytext.Fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.example.administrator.mytext.Activity_Search;
import com.example.administrator.mytext.Activity_allForest;
import com.example.administrator.mytext.Adapter.Adapter_home_land;
import com.example.administrator.mytext.Database.Land;
import com.example.administrator.mytext.DetailOfForest;
import com.example.administrator.mytext.R;
import com.example.administrator.mytext.Fragment.SmallFragment;
import com.example.administrator.mytext.Fragment.SmallFragment2;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager,viewPager3;
    private ImageView[] tips;
    private ImageView[] mImageViews;
    private int[] imgIdArray;

    private Button btn_one,btn_two;
    private ArrayList<Fragment> fragmentList;
    private SmallFragment small_fragment1;
    private SmallFragment2 small_fragment2;
    private ImageView imageviewOvertab;
    private int screenWidth;
    private int currenttab=-1;
    private List<Land> landList;

    private TextView hmft_checkall;
    private ImageView img_checkall;

    private EditText ET_top_serch;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.homefragment, container, false);

        ScrollView scrollView = (ScrollView)view.findViewById(R.id.scrollview_hf);
        scrollView.smoothScrollTo(0,20);
        ET_top_serch = (EditText)view.findViewById(R.id.search_edittext);
        ET_top_serch.setFocusable(false);
        ET_top_serch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Search.class);
                startActivity(intent);
            }
        });



        hmft_checkall = (TextView)view.findViewById(R.id.homefg_checkall);
        img_checkall = (ImageView) view.findViewById(R.id.homefg_img_checkall);
        hmft_checkall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Activity_allForest.class);
                startActivity(intent);
            }
        });

        img_checkall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Activity_allForest.class);
                startActivity(intent);
            }
        });


        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup);
        imgIdArray = new int[]{R.drawable.forest1, R.drawable.forest2, R.drawable.forstr3, R.drawable.forset4};
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
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
            viewPager.setOnTouchListener(new View.OnTouchListener() {

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

        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem((mImageViews.length) * 100);


        btn_one = (Button) view.findViewById(R.id.btn_one);
        btn_two = (Button) view.findViewById(R.id.btn_two);

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(0);

            }
        });
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(1);

            }
        });
        InitViewPager(view);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        btn_one.measure(0,0);
        imageviewOvertab=(ImageView) view.findViewById(R.id.imgv_overtab);
        FrameLayout.LayoutParams imageParams=new FrameLayout.LayoutParams(screenWidth/2, btn_one.getMeasuredHeight()-38);
        imageParams.topMargin=0;
        imageviewOvertab.setLayoutParams(imageParams);

        landList = new ArrayList<Land>();
        initLands();
        Adapter_home_land adapter = new Adapter_home_land(getContext(),R.layout.land_item_home,landList);
        ListView lv_home_land = (ListView) view.findViewById(R.id.land_listView2);
        lv_home_land.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lv_home_land);
        lv_home_land.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Land land = landList.get(position);
                Intent intent = new Intent(getActivity(),DetailOfForest.class);
                intent.putExtra("land_id", land.getLand_id());
                intent.putExtra("land_position",land.getLand_position() );
                intent.putExtra("land_use",land.getLand_use() );
                intent.putExtra("land_area",land.getLand_area());
                intent.putExtra("land_purchase_method",land.getLand_purchase_method());
                intent.putExtra("land_time",land.getLand_time());
                intent.putExtra("land_introduction",land.getLand_introduction());
                intent.putExtra("land_credential",land.getLand_credential());
                intent.putExtra("land_soil_condition",land.getLand_soil_condition());
                intent.putExtra("land_equipment",land.getLand_equipment());
                intent.putExtra("land_environment",land.getLand_environment());
                intent.putExtra("land_management",land.getLand_management());
                intent.putExtra("land_policy",land.getLand_policy());
                startActivity(intent);
            }
        });



        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
                // 获取ListView对应的Adapter
             Adapter_home_land adapter = (Adapter_home_land) listView.getAdapter();
             if (adapter == null) {
                      return;
               }

             int totalHeight = 0;
             for (int i = 0, len = adapter.getCount(); i < len; i++) {
                    // listAdapter.getCount()返回数据项的数目
                     View listItem = adapter.getView(i, null, listView);
                     // 计算子项View 的宽高
                     listItem.measure(0, 0);
                       // 统计所有子项的总高度
                     totalHeight += listItem.getMeasuredHeight();
                 }
            LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));
             // listView.getDividerHeight()获取子项间分隔符占用的高度
              // params.height最后得到整个ListView完整显示需要的高度
            listView.setLayoutParams(params);
       }

    private void initLands()
    {
        Land land1 = new Land("100亩荒山转让",
                "杭州 临安市",
                "苗圃种值",
                "100亩",
                "12年",
                "转让",
                "适合各类作物种植，种养殖都可以！感兴趣的老板欢迎来咨询。",
                "林权证",
                "壤土","供水：已接通  供电：已接通  进入道路：林道  机械化程度：无",
                "高速：0—10km 机场： 50—100km  铁路：50—100km 港口：50—100km",
                "地块适合何种经营：苗圃、林木种植 \n" +
                        "当地劳动力资源：充裕\n" +
                        "当地农民主要收入来源：在家种养\n" +
                        "当地农民人均年纯收入：8000元\n" +
                        "地块利用现状：种植\n" +
                        "地块产出：无",
                "良种补贴/农资补贴/农机购置补贴/农机报废更新补贴/土壤有机质提升补助",
                R.drawable.zxwtfu);
        landList.add(land1);

        Land land2 = new Land("杭州天目山景区口2000亩林地转让",
                "杭州 临安市",
                "林木种植",
                "2000亩",
                "70年",
                "转让",
                "杭州天目山景区口有林地近2000亩，有多年生长杉木等。有意向者请与本人联系。",
                "林权证",
                "壤土",
                "水：暂未接通，但可接通  供电：已接通  进入道路：水泥路  机械化程度：无",
                " 高速公路：10—50km机场： 无  铁路：无 港口：无",
                "地块适合何种经营：苗圃、林木种植 \n" +
                        "当地劳动力资源：充裕\n" +
                        "当地农民主要收入来源：在家种养\n" +
                        "当地农民人均年纯收入：8000元\n" +
                        "地块利用现状：种植\n" +
                        "地块产出：无",
                "良种补贴/农资补贴/农机购置补贴/农机报废更新补贴/土壤有机质提升补助",
                R.drawable.fbld);
        landList.add(land2);


    }
    private void InitViewPager(View parentView)
    {
        small_fragment1 = new SmallFragment();
        small_fragment2 = new SmallFragment2();
        fragmentList = new ArrayList<>();
        viewPager3 = (ViewPager) parentView.findViewById(R.id.viewpager3);
        fragmentList.add(small_fragment1);
        fragmentList.add(small_fragment2);
        viewPager3.setAdapter(new MyFrageStatePagerAdapter(getChildFragmentManager()));
    }


    class MyFrageStatePagerAdapter extends FragmentPagerAdapter
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

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container)
        {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem=viewPager3.getCurrentItem();
            if (currentItem==currenttab)
            {
                return ;
            }
            imageMove(viewPager3.getCurrentItem());
            currenttab=viewPager3.getCurrentItem();
        }
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
   private void changeView(int desTab)
   {
       viewPager3.setCurrentItem(desTab, true);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}



