package com.gqh.mystudio.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.CodeResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.entity.UserResult;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/20
 * desc: 访客入口
 */
public class FangKeLoginActivity extends BaseHttpActivity {

    private EditText et_phone;
    private EditText et_code;
    private String code;
    private String phone;
    private Button bt_code;

    @OnClick(R.id.image_back)
    private void onClick(View view){
        try {
            switch (view.getId()){
                case R.id.image_back:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            //验证是否为公民是的话从公民入口登录
            if (requestCode==5){
                MyLog.i("json====",jsonStr);
                JSONObject object=new JSONObject(jsonStr);
                if (!object.getString("code").equals("0")){
                    //发送获取验证码请求
                    RequestParams params = new RequestParams();
                    params.put("phone", phone);
                    postDialog(Constant.GET_CODE, params, 1,false);
                }else {
                    showToast("您为公民，请从公民入口登录！");
                }
            }


            // 验证码
            if (requestCode == 1){
                MyLog.i("jsonStr:",jsonStr);

                CodeResult result = GsonUtils.getSingleBean(jsonStr, CodeResult.class);
                if(result.isSuccess()){
                    showToast("发送成功");
                }else {
                    showToast("服务器异常");
                }

            }
            //登录
            if (requestCode == 2){
                MyLog.i("jsonStr",jsonStr);

                // 将json字符串转换为json对象
                JSONObject jsonObj = new JSONObject(jsonStr);
                String pass = jsonObj.getString("code");

                if(pass=="0"|pass.equals("0")){
                    showToast("登录成功");
                    Intent intent=new Intent(this,MainActivity.class);
                    intent.putExtra("quanxian",Constant.STYLE_FANGKE);
                    toActivity(intent);
                    finish();
                }else {
                    showToast("验证码输入错误，请重新输入");
                }
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
        return R.layout.activity_fang_ke_login;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ViewUtils.inject(this);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_code= (EditText) findViewById(R.id.et_code);
        bt_code= (Button) findViewById(R.id.bt_code);

    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    public void doClick(View v){
        try{
            switch (v.getId()){
                case R.id.bt_code:

                    phone = et_phone.getText().toString();
                    //判断手机号是否为空
                    if (StringUtil.isEmpty(phone)) {
                        showToast("请输入手机号");
                        break;
                    }
                    //验证手机号格式
                    if(!isMobileNO(phone)){
                        showToast("手机号格式不正确");
                        break;
                    }

                    new TimeCount(120000, 1000).start();

                    //发送获取公民信息
                    RequestParams params5=new RequestParams();
                    params5.put("phone",phone);
                    postDialog(Constant.GET_USER_INFO,params5,5,false);


                    break;
                case R.id.bt_login:
                    code = et_code.getText().toString();

                    //判断验证码是否为空
                    if (StringUtil.isEmpty(code)) {
                        showToast("请输入验证码");
                        break;
                    }


                    //发送登录请求
                    RequestParams params2 = new RequestParams();
                    params2.put("phone", phone);
                    params2.put("password", code);
                    postDialog(Constant.FANGKE_RENZHENG_LOGION, params2,2);

                    break;
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            bt_code.setText("获取验证码");
            bt_code.setClickable(true);
            bt_code.setBackgroundResource(R.drawable.shape_huodong_more_btn);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            bt_code.setClickable(false);//防止重复点击
            bt_code.setText(millisUntilFinished / 1000 +"s重新获取");
            bt_code.setBackgroundResource(R.drawable.shape_huodong_more_btn_huise);
        }
    }
}
