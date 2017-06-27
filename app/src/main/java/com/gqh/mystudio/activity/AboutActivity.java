package com.gqh.mystudio.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;

public class AboutActivity extends Activity {

    @OnClick({R.id.image_fanhui})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ViewUtils.inject(this);
    }
}
