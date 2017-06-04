package com.example.administrator.mytext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.mytext.Database.CommonRequest;
import com.example.administrator.mytext.Database.Constant;
import com.example.administrator.mytext.Fragment.FragmentForDetail.Forest_Introduction;
import com.example.administrator.mytext.Fragment.FragmentForDetail.Forest_basicInfomation;
import com.example.administrator.mytext.Fragment.FragmentForDetail.Forest_more;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class DetailOfForest extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private ImageView[] tips;
    private ImageView[] mImageViews;
    private int[] imgIdArray;
    private TextView tv_landName,tv_landID,tv_landPrice,tv_landReleaseTime;
    private TextView test;
    private ImageView img;


    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case 1:
                    img.setImageBitmap(String2Bitmap(msg.obj.toString()));

                    break;



            }

        }


    };

    /**
     * s三个导航按钮
     * */
    private Button btn_basic_info;
    private Button btn_forest_intro;
    private Button btn_forest_more;
    /**
     * 作为页面容器的ViewPager
     */
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    ArrayList<Fragment> fragmentList;

    /**
     * 3个Fragment（页面）
     */
    Forest_basicInfomation forest_basicInfomation;
    Forest_Introduction forest_introduction;
    Forest_more forest_more;

    /**
     覆盖层
     */
    ImageView imageviewOvertab;
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab=-1;

    private Button btn_vr,btn_contact_2,btn_contact_3,btn_to_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forest);

        img = (ImageView)findViewById(R.id.test_pic);

        btn_forest_intro = (Button)findViewById(R.id.forest_introduction);
        btn_basic_info = (Button)findViewById(R.id.forest_basic_information);
        btn_forest_more = (Button)findViewById(R.id.forest_more);
        tv_landName = (TextView)findViewById(R.id.detail_forest_name) ;
        tv_landPrice = (TextView)findViewById(R.id.detail_price);
        tv_landID = (TextView)findViewById(R.id.land_id);
        tv_landReleaseTime = (TextView)findViewById(R.id.land_release_time);
        viewPager = (ViewPager) findViewById(R.id.viewPager2);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup2);
        imgIdArray = new int[]{R.drawable.blank};
        tips = new ImageView[imgIdArray.length];

        btn_basic_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(0);
            }
        });
        btn_forest_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(1);
            }
        });
        btn_forest_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(2);
            }
        });

        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        fragmentList = new ArrayList<>();
        forest_basicInfomation = new Forest_basicInfomation();
        forest_introduction = new Forest_Introduction();
        forest_more = new Forest_more();
        fragmentList.add(forest_basicInfomation);
        fragmentList.add(forest_introduction);
        fragmentList.add(forest_more);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        btn_forest_intro.measure(0, 0);
        imageviewOvertab=(ImageView) findViewById(R.id.imgv_overtab);
        FrameLayout.LayoutParams imageParams=new FrameLayout.LayoutParams(screenWidth/3, btn_forest_intro.getMeasuredHeight()-137);
        imageParams.topMargin = 137;
        imageviewOvertab.setLayoutParams(imageParams);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));


        /**
         * 接收上一个活动的数据
         * */
        final Intent intent = getIntent();
        final String land_id = intent.getStringExtra("land_id");
        String land_position = intent.getStringExtra("land_position");
        String land_use = intent.getStringExtra("land_use");
        String land_area = intent.getStringExtra("land_area");
        String land_way = intent.getStringExtra("land_way");
        String land_time = intent.getStringExtra("land_time");
        String land_introduction = intent.getStringExtra("land_introduction");
        String land_credential = intent.getStringExtra("land_credential");
        String land_equipment = intent.getStringExtra("land_equipment");
        String land_environment = intent.getStringExtra("land_environment");
        String land_management = intent.getStringExtra("land_management");
        String land_policy = intent.getStringExtra("land_policy");
        String land_release_time = intent.getStringExtra("land_release_time");
        final String land_inner_id = intent.getStringExtra("land_inner_id");
        String land_price = intent.getStringExtra("land_price");
        String land_detail = intent.getStringExtra("land_detail");
        final String land_vr = intent.getStringExtra("land_vr");
        final String land_phone = intent.getStringExtra("land_phone");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String land_id = intent.getStringExtra("land_id");
                    final String[] id = {land_id};

                    try {
                        id[0] = URLEncoder.encode(id[0], "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    HttpURLConnection connection = null;
                    CommonRequest request = new CommonRequest();
                    URL url = new URL(Constant.URL_Download + "?land_id=" + id[0]);
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


        btn_contact_2 = (Button)findViewById(R.id.btn_two);
        btn_contact_3 = (Button)findViewById(R.id.btn_three);
        btn_to_chat = (Button)findViewById(R.id.btn_four);
        btn_contact_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailOfForest.this);
                dialog.setTitle("提示");
                dialog.setMessage("联系电话:"+land_phone);
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+land_phone));
                        startActivity(intent2);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.show();
            }
        });
        btn_contact_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailOfForest.this);
                dialog.setTitle("提示");
                dialog.setMessage("预约电话:"+land_phone);
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent3 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+land_phone));
                        startActivity(intent3);
                        finish();
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.show();
            }
        });
        btn_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(),MainActivity.class);
                intent4.putExtra("chat_message",1);
                startActivity(intent4);
            }
        });
        btn_vr = (Button)findViewById(R.id.detail_btn_vr);
        btn_vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (land_vr == null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailOfForest.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("尚未添加！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认", null);
                    dialog.show();
                }
                else {
                    Intent intent = new Intent(getApplication(), Activity_VR_view.class);
                    intent.putExtra("land_vr", land_vr);
                    intent.putExtra("land_inner_id", land_inner_id);
                    startActivity(intent);
                }
            }
        });

        tv_landName.setText(land_inner_id);
        tv_landID.setText("ID："+land_id);
        tv_landPrice.setText(land_price+"元");
        tv_landReleaseTime.setText(" "+land_release_time);

        Bundle bundle1 = new Bundle();
        bundle1.putString("area",land_area);
        bundle1.putString("way",land_way);
        bundle1.putString("position",land_position);
        bundle1.putString("time",land_time);
        bundle1.putString("use",land_use);
        bundle1.putString("introduction",land_introduction);
        bundle1.putString("credential",land_credential);
        bundle1.putString("equipment",land_equipment);
        bundle1.putString("environment",land_environment);
        bundle1.putString("management",land_management);
        bundle1.putString("policy",land_policy);
        bundle1.putString("detail",land_detail);
        forest_basicInfomation.setArguments(bundle1);
        forest_introduction.setArguments(bundle1);
        forest_more.setArguments(bundle1);


        for(int i=0; i<tips.length; i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0)
            {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }
            else
            {
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
                ImageView imageView = new ImageView(getApplicationContext());
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
        }
        else if (imgIdArray.length == 2 || imgIdArray.length == 3) {
            mImageViews = new ImageView[imgIdArray.length * 2];
            for (int i = 0; i < (mImageViews.length); i++) {
                ImageView imageView = new ImageView(getApplicationContext());
                mImageViews[i] = imageView;
                imageView.setBackgroundResource(imgIdArray[(i > (imgIdArray.length-1)) ? i -imgIdArray.length  : i]);
            }
        } else {
            mImageViews = new ImageView[imgIdArray.length];
            for (int i = 0; i < mImageViews.length; i++) {
                ImageView imageView = new ImageView(getApplicationContext());
                mImageViews[i] = imageView;
                imageView.setBackgroundResource(imgIdArray[i]);
            }
        }
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem((mImageViews.length) * 100);
    }

    /**
     * 定义自己的ViewPager适配器。
     * 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
     */
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

    /**
     * 移动覆盖层
     * @param moveToTab 目标Tab，也就是要移动到的导航选项按钮的位置
     * 第一个导航按钮对应0，第二个对应1，以此类推
     */
    private void imageMove(int moveToTab)
    {
        int startPosition=0;
        int movetoPosition=0;

        startPosition=currenttab*(screenWidth/3);
        movetoPosition=moveToTab*(screenWidth/3);
        //平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movetoPosition, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
        imageviewOvertab.startAnimation(translateAnimation);
    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        mViewPager.setCurrentItem(desTab, true);
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

    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }


    public static Bitmap String2Bitmap(String pic) {
        byte[] bytes = Base64.decode(pic, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }



}
