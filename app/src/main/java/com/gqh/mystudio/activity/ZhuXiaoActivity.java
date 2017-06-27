package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ACache;
import cn.myapp.util.ExceptionUtil;

public class ZhuXiaoActivity extends BaseActivity {

    @ViewInject(R.id.bt_x)
    private Button bt_x;
    @ViewInject(R.id.tuichu)
    private Button tuichu;
    @ViewInject(R.id.quxiao)
    private Button quxiao;
    @OnClick({R.id.bt_x,R.id.tuichu,R.id.quxiao})
    private void onClick (View v){
        try{
            switch (v.getId()){
                case R.id.bt_x:
                    finish();
                    break;
                case R.id.quxiao:
                    finish();
                    break;
                case R.id.tuichu:
                    ACache aCache = ACache.get(context);
                    aCache.clear();
                    BamsApplication.getInstance().setUser(null);
                    toActivityAfterFinishThis(ChooseEnterActivity.class);
                    break;
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_xiao);
        ViewUtils.inject(this);
    }

}
