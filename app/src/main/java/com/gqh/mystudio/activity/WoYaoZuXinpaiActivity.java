package com.gqh.mystudio.activity;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.gqh.mystudio.R;

import cn.myapp.base.BaseActivity;

public class WoYaoZuXinpaiActivity extends BaseActivity {

    private WebView webview;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_yao_zu_xinpai);

        webview= (WebView) findViewById(R.id.webview);
        iv_back= (ImageView) findViewById(R.id.image_fanhui);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();//销毁当前页面

            }
        });

//        String url1="http://www.cypalife.com/rent/leasing_new.html";
       String url1= getIntent().getStringExtra("URL");


        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl(url1);
        //设置Web视图
        webview.setWebViewClient(new webViewClient());

    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent keyevent)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//销毁当前页面
        return false;
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
