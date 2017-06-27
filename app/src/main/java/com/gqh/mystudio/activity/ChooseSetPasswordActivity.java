package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.View;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseActivity;

public class ChooseSetPasswordActivity extends BaseActivity {

    @OnClick({R.id.image_fanhui,R.id.tv_xiuGaiPass,R.id.tv_wangjiPass})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.image_fanhui:
                finish();
                break;
            case R.id.tv_xiuGaiPass:
                toActivityAfterFinishThis(XiuGaiPayPasswordActivity.class);
                break;
            case R.id.tv_wangjiPass:
                toActivityAfterFinishThis(WangJiPayMiMaActivity.class);
//                toActivityAfterFinishThis(PhoneYanzhengActivity.class);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_set_password);
        ViewUtils.inject(this);
    }
}
