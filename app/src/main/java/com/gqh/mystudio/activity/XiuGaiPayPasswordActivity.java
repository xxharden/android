package com.gqh.mystudio.activity;

import android.view.View;
import android.widget.EditText;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class XiuGaiPayPasswordActivity extends BaseHttpActivity {

    @ViewInject(R.id.et_password_1)
    private EditText et_password_1;

    @ViewInject(R.id.et_password_2)
    private EditText et_password_2;

    @ViewInject(R.id.et_password_3)
    private EditText et_password_3;
    private String pass2;
    private String pass1;

    @OnClick({R.id.image_fanhui,R.id.bt_queren})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.image_fanhui:
                finish();
                break;
            case R.id.bt_queren:
                pass1 = et_password_1.getText().toString();
                pass2 = et_password_2.getText().toString();
                String pass3 = et_password_3.getText().toString();
                //非空验证
                if (StringUtil.isEmpty(pass1)){
                    showToast("请输入原始密码");
                    break;
                }
                if (StringUtil.isEmpty(pass2)){
                    showToast("请输入新密码");
                    break;
                }
                // 两次输入比对
                if (!pass2.equals(pass3)){
                    showToast("两次新密码输入不一致，请重新输入");
                    et_password_1.setText("");
                    et_password_2.setText("");
                    et_password_3.setText("");
                    break;
                }

                //原始密码验证
                RequestParams params1 =new RequestParams();
                params1.put("UID",BamsApplication.getInstance().getUser().getUID());
                params1.put("Pwd",pass3);
                postDialog(Constant.CHECK_PAY_PASSWORD, params1,1,false);

                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xiu_gai_pay_password;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode==1){
                JSONObject object=new JSONObject(jsonStr);
                if (object.getString("code").equals("0")){
                    //原始密码验证成功，发送修改支付密码 请求
                    RequestParams params2=new RequestParams();
                    params2.put("phone", BamsApplication.getInstance().getUser().getULoginID());
                    params2.put("oldpwd",pass1);
                    params2.put("newpwd",pass2);
                    postDialog(Constant.SET_PAY_PASSWORD,params2,2,false);

                }else {
                    showToast("原始密码输入不正确,请重新输入");
                    et_password_1.setText("");
                    et_password_2.setText("");
                    et_password_3.setText("");
                }
            }

            if (requestCode==2){
                JSONObject object=new JSONObject(jsonStr);
                if (object.getString("code").equals("0")){
                    //修改成功
                    showToast("修改成功");
                    finish();
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
