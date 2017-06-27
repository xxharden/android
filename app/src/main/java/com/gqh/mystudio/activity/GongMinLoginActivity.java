package com.gqh.mystudio.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.entity.UserResult;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import cn.myapp.util.StringUtil;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/20
 * desc: 公民入口
 */
public class GongMinLoginActivity extends BaseHttpActivity{

    private EditText et_phone;
    private EditText et_password;
    private CheckBox cb_jizhu;
    private CheckBox cb_tongyi;
    private Button bt_login;
    private String phone;
    private String password;
    private ACache aCache;
    private String userStyle;
    private User Uuer;
    private UserResult result5;

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

                result5 = GsonUtils.getSingleBean(jsonStr, UserResult.class);
                if (result5.isSuccess()){

                    BamsApplication.getInstance().setUser(result5.data);//存全局
                    aCache.put("user",result5.data);//缓存

                    MyLog.i("jsonStr=",jsonStr);
                    userStyle = result5.data.getUSTATE();

                    //发送登录请求
                    RequestParams params = new RequestParams();
                    params.put("phone",phone);
                    params.put("password", password);
                    postDialog(Constant.GONGMIN_RENZHENG_LOGION, params,1);

                }else {
                    showToast("您不是公民，请从访客入口进");
                }
            }


            if (requestCode==1){
                // 将json字符串转换为json对象
                JSONObject jsonObj = new JSONObject(jsonStr);
                String pass = jsonObj.getString("code");

                MyLog.i("jsonStr=",jsonStr);
                if(pass.equals("0")){
                    showToast("登录成功");
                    Intent intent = null;
                    Uuer= result5.data;
                    if (!StringUtil.isEmpty(Uuer.getUSignaTure())){
                        intent=new Intent(this,MainActivity.class);
                        if (userStyle.equals("退租")){//退租公民
                            intent.putExtra("quanxian",Constant.STYLE_GONGMIN_OLD);
                        }else {//在租公民

                            intent.putExtra("quanxian",Constant.STYLE_GONGMIN);
                        }

                    }else {

                        intent=new Intent(this,SetGerenXinActivity.class);
                        intent.putExtra("enter","first");
                    }

                    intent.putExtra("phone", phone);
                    toActivity(intent);
                    finish();

                }else {
                    showToast("手机号与密码不匹配，请重新输入");
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
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_password= (EditText) findViewById(R.id.et_password);
        cb_jizhu= (CheckBox) findViewById(R.id.rd_jizhu);
        cb_tongyi= (CheckBox) findViewById(R.id.rd_tongyi);
        bt_login= (Button) findViewById(R.id.bt_login);

        //从获得缓存账号密码
        aCache = ACache.get(context);
        String zh=aCache.getAsString("phone");
        String mm=aCache.getAsString("password");
        if(!StringUtil.isEmpty(zh)){
            et_phone.setText(zh);
            et_password.setText(mm);
            cb_jizhu.setChecked(true);
        }else {
            et_phone.setText("");
            et_password.setText("");
            cb_jizhu.setChecked(false);
        }
    }




   /* //倒计时重新发送
    protected void djs() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                bt_code.setText(millisUntilFinished / 1000 + "S后重新发送");
            }

            public void onFinish() {
                bt_code.setText("获取验证码");
                bt_code.setClickable(true);
            }
        }.start();
    }*/

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


    public void doClick(View v) {
        try {
            switch (v.getId()){
                case R.id.wangji:

                    //判断手机号是否为空
                    if(et_phone.length()==0){
                        showToast("请输入手机号");
                        break;
                    }

                    phone=et_phone.getText().toString();

                    //验证手机号格式
                    if(!isMobileNO(phone)){
                        showToast("手机号格式不正确");
                        break;
                    }

                    Intent intent=new Intent(this,WangJiMiMaActivity.class);
                    intent.putExtra("phone",phone);
                    toActivity(intent);
                    break;

                case R.id.bt_login:
                    phone=et_phone.getText().toString();
                    password=et_password.getText().toString();

                    //判断手机号是否为空
                    if (StringUtil.isEmpty(phone)){
                        showToast("请输入手机号");
                        break;
                    }

                    //验证手机号格式
                    if(!isMobileNO(phone)){
                        showToast("手机号格式不正确");
                        break;
                    }

                    //判断密码是否为空
                    if (StringUtil.isEmpty(password)) {
                        showToast("请输入密码");
                        break;
                    }

                    //设置如果不勾选同意，不得登录
                    if(!cb_tongyi.isChecked()){
                        showToast("请仔细阅读服务条款");
                        break;
                    }


                    //发送获取公民信息
                    RequestParams params5=new RequestParams();
                    params5.put("phone",phone);
                    postDialog(Constant.GET_USER_INFO,params5,5,false);

                   /* //发送登录请求
                    RequestParams params = new RequestParams();
                    params.put("phone",phone);
                    params.put("password",password);
                    postDialog(Constant.GONGMIN_RENZHENG_LOGION, params,1);*/

                    //如果记住密码选中，则把用户输入的账号密码缓存
                    if(cb_jizhu.isChecked()) {
                        aCache.put("phone", phone);
                        aCache.put("password", password);
                    }else {
                        aCache.put("phone", "");
                        aCache.put("password", "");
                    }

                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
}
