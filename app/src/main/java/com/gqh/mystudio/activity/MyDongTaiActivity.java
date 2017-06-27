package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;

public class MyDongTaiActivity extends BaseActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.tv_shai)
    private TextView tv_shai;
    @ViewInject(R.id.tv_qiao)
    private TextView tv_qiao;
    @ViewInject(R.id.tv_yue)
    private TextView tv_yue;

    @OnClick({R.id.image_fanhui,R.id.tv_shai,R.id.tv_qiao,R.id.tv_yue})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.tv_shai:
                    toActivity(ShaiMyShaiActivity.class);
                    break;
                case R.id.tv_qiao:
                    toActivity(QiaoMyQiaoActivity.class);
                    break;
                case R.id.tv_yue:
                    toActivity(YueMyYueActivity.class);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dong_tai);
        ViewUtils.inject(this);
    }
}
