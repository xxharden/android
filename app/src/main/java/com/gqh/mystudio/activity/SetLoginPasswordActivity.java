package com.gqh.mystudio.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class SetLoginPasswordActivity extends BaseHttpActivity {

    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;
    @ViewInject(R.id.et_pass1)
    private EditText et_pass1;
    @ViewInject(R.id.et_password_1)
    private EditText et_password_1;
    @ViewInject(R.id.et_password_2)
    private EditText et_password_2;
    private String phone;

    @OnClick({R.id.image_fanhui,R.id.bt_ok})
    private void onClick(View v){
        try {

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_ok:
                    //非空验证
                    String oldPass = et_pass1.getText().toString();
                    String newPass1 = et_password_1.getText().toString();
                    String newPass2 = et_password_2.getText().toString();

                    if (StringUtil.isEmpty(oldPass)){
                        showToast("请输入原始密码");
                        break;
                    }

                    if (StringUtil.isEmpty(newPass1)|StringUtil.isEmpty(newPass2)){
                        showToast("请输入新密码");
                        break;
                    }

                    if (!newPass1.equals(newPass2)){
                        showToast("两次新密码输入不一致请重新输入");
                        et_pass1.setText("");
                        et_password_1.setText("");
                        et_password_2.setText("");
                        break;
                    }

                    //验证原始密码
                    //提交修改密码 请求
                    RequestParams params=new RequestParams();
                    params.put("phone",phone);
                    params.put("oldpwd",oldPass);
                    params.put("newpwd", newPass2);
                    postDialog(Constant.SET_LOIN_PASSWORD,params,1,false);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_set_login_password;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        phone=BamsApplication.getInstance().getUser().getULoginID();
        tv_phone.setText(phone);
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {

            if (requestCode==1){
                JSONObject object=new JSONObject(jsonStr);
                if (object.getString("code").equals("0")){
                    showToast("修改成功");
                    toActivityAfterFinishThis(ChooseEnterActivity.class);
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
