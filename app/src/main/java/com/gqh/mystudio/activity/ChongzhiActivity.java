package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.StringUtil;

public class ChongzhiActivity extends BaseHttpActivity {


    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.et_jine)
    private EditText et_jine;
    @ViewInject(R.id.bt_chongzhi)
    private Button bt_chongzhi;

    @OnClick({R.id.image_fanhui,R.id.bt_chongzhi})
    private void onClick(View v){
        try {

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_chongzhi:
                    String jine = et_jine.getText().toString();
                    if(StringUtil.isEmpty(jine)){
                        showToast("请输入充值金额");
                        break;
                    }
                    Intent intent=new Intent(this,ZhifuFangshiActivity.class);
                    intent.putExtra("jine",jine);
                    intent.putExtra("style",100);
                    toActivity(intent);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_chongzhi;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
    }
}
