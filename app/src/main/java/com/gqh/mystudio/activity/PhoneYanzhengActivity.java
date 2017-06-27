package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.CodeResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class PhoneYanzhengActivity extends BaseHttpActivity {

    private String phone;

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;
    @ViewInject(R.id.bt_code)
    private Button bt_code;
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @ViewInject(R.id.bt_ok)
    private Button bt_ok;
    private String codeResult;
    private int style;

    @OnClick({R.id.image_fanhui,R.id.bt_ok,R.id.bt_code})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;

                case R.id.bt_ok:
                    String code = et_code.getText().toString();

                    //判断验证码是否为空
                    if (StringUtil.isEmpty(code)) {
                        showToast("请输入验证码");
                        break;
                    }
                    if(code.equals(codeResult)){
                        Intent intent=new Intent(this,SetPayPasswordActivity.class);
                        intent.putExtra("code",code);
                        intent.putExtra("phone",phone);
                        intent.putExtra("style",style);
                        toActivity(intent);
                        finish();
                        break;
                    }
                    break;

                case R.id.bt_code:
                    //发送获取验证码请求
                    RequestParams params = new RequestParams();
                    params.put("phone", phone);
                    postDialog(Constant.GET_CODE, params,false);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            MyLog.i("code",jsonStr);
            CodeResult result = GsonUtils.getSingleBean(jsonStr, CodeResult.class);
            if(result.isSuccess()){
                showToast("发送成功");
                codeResult=result.data.getCode();
            }else {
                showToast("手机号不存在");
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_phone_yanzheng;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);
        phone=BamsApplication.getInstance().getUser().getULoginID();
        tv_phone.setText(phone);
        style=getIntent().getIntExtra("style",0);
    }
}
