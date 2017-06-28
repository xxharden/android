package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gqh.mystudio.R;

import cn.myapp.base.BaseActivity;

public class PeiDianXiangActivity extends BaseActivity {


    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p);
        initialize();
    }
    private void initialize() {
        wb = (WebView) findViewById(R.id.wb);
        String url = "http://www.yiliangang.net:8012/witbox/box.html";

        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        wb.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        wb.getSettings().setSupportZoom(false);//是否可以缩放，默认true
        wb.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        wb.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        wb.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        wb.getSettings().setAppCacheEnabled(true);//是否使用缓存
        wb.getSettings().setDomStorageEnabled(true);//DOM Storage
        wb.loadUrl(url);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);   //在当前的webview中跳转到新的url
                return true;
            }
        });

    }

    // 设置回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wb.canGoBack()) {
            wb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
