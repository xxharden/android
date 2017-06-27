package com.gqh.mystudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;

public class YueJiaruHongdongOkActivity extends BaseActivity {
    private int groupId;
/*
    @ViewInject(R.id.bt_x)
    private Button bt_x;*/

    @OnClick({R.id.bt_x,R.id.bt_ok,R.id.bt_yaoqing})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.bt_x:
                    finish();
                    break;
                case R.id.bt_ok:
                    finish();
                    break;
                case R.id.bt_yaoqing:
                    Intent intent=new Intent(this,YueYaoQingHaoyouActivity.class);
                    intent.putExtra("GroupID",groupId);
                    toActivity(intent);
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
        setContentView(R.layout.activity_yue_jiaru_hongdong);
        ViewUtils.inject(this);

        groupId=getIntent().getIntExtra("GroupID",0);
    }

}
