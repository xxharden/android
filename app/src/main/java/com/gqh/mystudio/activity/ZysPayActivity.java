package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.gqh.mystudio.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import java.io.UnsupportedEncodingException;
import cn.myapp.base.BaseActivity;

public class ZysPayActivity extends BaseActivity {

    @ViewInject(R.id.pay_webview)
    private WebView wv;
    private String USERID = "40";
    private String mString;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zys_pay);
    }

    private void initialize() {
        String thingId = getIntent().getStringExtra("thingId");
        String productnamepro = null;
        String productdescpro = null;
        try {
            mString = new String(thingId.getBytes(), "utf-8");
            productnamepro = new String("易联港".getBytes(), "utf-8");
            productdescpro = new String("测试".getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //USERID = mApplication.getString(App.KEY_USERID);//用户ID
        Log.i("dyy", "thingID" + mString);
        Log.i("dyy", "UserID" + USERID);
        url = "http://www.yiliangang.net/JuHeFuPay/pay.jsp?facilitator=0001&carrieroperator=0002&useridpro=" + USERID + "&thingidpro=" + mString + "&productnamepro=" + productnamepro + "&productdescpro=" + productdescpro + "&usernamepro=饮水机1&emailpro=m13240371551@163.com&phonepro=13240371551&redirecturlpro=";
        WebSettings web = wv.getSettings();
        wv.loadUrl(url);
        web.setJavaScriptEnabled(true);
        web.setAllowContentAccess(true);
        web.setAppCacheEnabled(false);
        web.setBuiltInZoomControls(false);
        web.setUseWideViewPort(true);
        web.setLoadWithOverviewMode(true);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }
}
