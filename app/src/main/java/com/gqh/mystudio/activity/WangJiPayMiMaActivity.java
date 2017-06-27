package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.CodeResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class WangJiPayMiMaActivity extends BaseHttpActivity {

    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @ViewInject(R.id.et_password_1)
    private EditText et_password_1;
    @ViewInject(R.id.et_password_2)
    private EditText et_password_2;
    private String phone;
    private String codeResult;

    @OnClick({R.id.image_fanhui,R.id.bt_code_send,R.id.bt_ok})
    private void onClick(View v){
        try{

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_code_send:
                    //发送获取验证码请求
                    RequestParams params1 = new RequestParams();
                    params1.put("phone", phone);
                    postDialog(Constant.GET_CODE, params1,1,false);
                    break;
                case R.id.bt_ok:
                    String code = et_code.getText().toString();
                    String pass1=et_password_1.getText().toString();
                    String pass2=et_password_2.getText().toString();

                    //非空验证
                    if (StringUtil.isEmpty(pass1)|StringUtil.isEmpty(pass2)){
                        showToast("请输入新密码");
                        break;
                    }
                    if (!pass1.equals(pass2)){
                        showToast("两次密码输入不一致");
                        break;
                    }

                    //判断验证码是否为空
                    if (StringUtil.isEmpty(code)) {
                        showToast("请输入验证码");
                        break;
                    }


                    if(codeResult!=null&&code.equals(codeResult)){
                        //发送请求设置密码
                        RequestParams params2=new RequestParams();
                        params2.put("phone",phone);
                        params2.put("code",code);
                        params2.put("newpwd",pass2);
                        postDialog(Constant.WANGJI_PAY_PASSWORD,params2,2);

                    }else {
                        showToast("验证码输入不正确");
                    }
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_wang_ji_pay_mi_ma;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        phone= BamsApplication.getInstance().getUser().getULoginID();
        tv_phone.setText(phone);
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{

            if (requestCode==1){
                CodeResult result = GsonUtils.getSingleBean(jsonStr, CodeResult.class);
                if(result.isSuccess()){
                    showToast("发送成功");
                    codeResult= result.data.getCode();
                }else {
                    showToast("手机号不存在");
                }
            }

            if (requestCode==2){
                JSONObject object=new JSONObject(jsonStr);

                if(object.getString("code").equals("0")){
                    showToast("密码设置成功");
                }else {
                    showToast(object.getString("message"));
                }
            }


        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

    }
}
