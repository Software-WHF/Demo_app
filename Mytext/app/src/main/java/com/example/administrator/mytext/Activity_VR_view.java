package com.example.administrator.mytext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Activity_VR_view extends Activity {
    private WebView webView;
    private TextView tv_land_name;
    String land_vr,land_inner_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_view);
        Intent intent = getIntent();
        land_inner_id = intent.getStringExtra("land_inner_id");
        land_vr = intent.getStringExtra("land_vr");
        Toast.makeText(getApplication(),land_vr,Toast.LENGTH_LONG).show();
        init();
    }

    private void init(){
        webView = (WebView) findViewById(R.id.webView);
        tv_land_name = (TextView) findViewById(R.id.vr_land_name);
        tv_land_name.setText(land_inner_id);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);//有可能是DOM储存API没有打开
        webView.getSettings().setBlockNetworkImage(false);
        //WebView加载web资源"http://720yun.com/t/750jOruaty6?pano_id=1731294"
        String url = "http://"+land_vr;
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
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
