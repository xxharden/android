package com.gqh.mystudio.activity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gqh.mystudio.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class SetNewPasswordActivity extends BaseHttpActivity {

    @ViewInject(R.id.fanhui)
    private ImageView iv_back;
    @ViewInject(R.id.et_password_1)
    private EditText et_pass1;
    @ViewInject(R.id.et_password_2)
    private EditText et_pass2;
    @ViewInject(R.id.bt_queren)
    private Button bt_ok;
    private String phone;
    private String code;

    @OnClick({R.id.fanhui, R.id.bt_queren})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.fanhui:
                    finish();
                    break;
                case R.id.bt_queren:
                    String pa1 = et_pass1.getText().toString();
                    String pa2=et_pass2.getText().toString();
                    if (pa1.equals(pa2)){

                        RequestParams params = new RequestParams();
                        params.put("phone", phone);
                        params.put("newpwd",pa1);
                        params.put("code",code);
                        postDialog(Constant.WANGJI_LOIN_PASSWORD,params,false);

                    }else {
                        showToast("两次输入有误,请重新输入");
                        et_pass1.setText("");
                        et_pass2.setText("");
                    }
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_set_new_password;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        phone=getIntent().getStringExtra("phone");
        code=getIntent().getStringExtra("code");
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        MyLog.i(jsonStr, jsonStr);
        try{
            JSONObject object=new JSONObject(jsonStr);
            String mCode = object.getString("code");
            if (mCode=="0"|mCode.equals("0")){
                showToast("修改成功，请重新登陆");
                toActivityAfterFinishThis(XiuGaiMimaOkActivity.class);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

    }
}
