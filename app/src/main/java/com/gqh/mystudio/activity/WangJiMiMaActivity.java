package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.CodeResult;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.StringUtil;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/20
 * desc: 忘记密码
 */
public class WangJiMiMaActivity extends BaseHttpActivity {
    private TextView tv_phone;
    private EditText et_code;
    private String code;
    private String phone;
    private ImageView bt_fanhui;
    private String codeResult=null;

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{

            CodeResult result = GsonUtils.getSingleBean(jsonStr, CodeResult.class);
            if(result.isSuccess()){
                showToast("发送成功");
                codeResult= result.data.getCode();
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
        return R.layout.activity_wang_ji_mi_ma;
    }

    @Override
    protected void initView() {
        tv_phone= (TextView) findViewById(R.id.tv_phone);
        et_code= (EditText) findViewById(R.id.et_code);

        phone = getIntent().getStringExtra("phone");
        tv_phone.setText(phone);
    }

    public void doClick(View view){
        try{
            switch (view.getId()){

                case R.id.bt_fanhui:
                    finish();
                    break;

                case R.id.bt_code_send:
                    //发送获取验证码请求
                    RequestParams params = new RequestParams();
                    params.put("phone", phone);
                    postDialog(Constant.GET_CODE, params,false);
                    break;


                case R.id.bt_ok:
                    code = et_code.getText().toString();
                    //判断验证码是否为空
                    if (StringUtil.isEmpty(code)) {
                        showToast("请输入验证码");
                        break;
                    }

                    if(codeResult!=null&&code.equals(codeResult)){
                        Intent intent=new Intent(this,SetNewPasswordActivity.class);
                        intent.putExtra("code",code);
                        intent.putExtra("phone",phone);
                        toActivity(intent);
                        finish();
                    }else {
                        showToast("验证码输入不正确");
                    }


                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }
}
