package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;

import cn.jpush.android.api.JPushInterface;
import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;
/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 入口选择
 */
public class ChooseEnterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enter);
    }

    public void doClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fangke:
                    toActivity(FangKeLoginActivity.class);

                    break;
                case R.id.image_gongmin:
                    toActivity(GongMinLoginActivity.class);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    long temptime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN)) {
            if(System.currentTimeMillis() - temptime >2000) // 2s内再次选择back键有效
            {
                showToast("再按一下将退出程序");
                temptime = System.currentTimeMillis();
            }
            else {
                finish();
                BamsApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
