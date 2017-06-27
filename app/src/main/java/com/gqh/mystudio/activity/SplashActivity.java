package com.gqh.mystudio.activity;

import android.content.Intent;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;

import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;
import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.entity.UserResult;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.StringUtil;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 欢迎界面
 */
public class SplashActivity extends BaseHttpActivity{

    private Timer timer;
    private ACache aCache;
    private String phone=null;
    private User uSer;
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        aCache = ACache.get(context);
        User user = (User) aCache.getAsObject("user");
        if (user!=null){
            phone = user.getULoginID();
        }


        try {
            //定时器5秒跳转界面
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (uSer!= null) {
                        Intent intent;
                        if (!StringUtil.isEmpty(uSer.getUHeadPortrait())) {
                            intent = new Intent(SplashActivity.this, MainActivity.class);
                            if (uSer.getUSTATE().equals("退租")) {//退租公民
                                intent.putExtra("quanxian", Constant.STYLE_GONGMIN_OLD);
                            } else {//在租公民
                                intent.putExtra("quanxian", Constant.STYLE_GONGMIN);
                            }
                            intent.putExtra("phone", uSer.getULoginID());
                            toActivity(intent);
                            finish();

                        } else {
                            toActivityAfterFinishThis(ChooseEnterActivity.class);
                        }
                    } else {
                        toActivityAfterFinishThis(ChooseEnterActivity.class);
                    }
                }
            }, 2000);
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            if (requestCode==5){
                UserResult result = GsonUtils.getSingleBean(jsonStr, UserResult.class);
                if (result.isSuccess()){
                    uSer = result.data;
                    BamsApplication.getInstance().setUser(uSer);//存全局
                    aCache.put("user",uSer);//缓存
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {

        if (phone!=null){
            //发送获取公民信息
            RequestParams params5=new RequestParams();
            params5.put("phone",phone);
            postDialog(Constant.GET_USER_INFO,params5,5,false);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
