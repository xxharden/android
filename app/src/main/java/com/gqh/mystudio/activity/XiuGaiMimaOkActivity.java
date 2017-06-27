package com.gqh.mystudio.activity;

import android.os.Bundle;
import android.view.View;

import com.gqh.mystudio.R;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 修改密码成功
 */
public class XiuGaiMimaOkActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_mima_ok);


    }
    public void doClick(View v){
        try {
            switch (v.getId()){
                case R.id.bt_x:
                    finish();
                    break;

                case R.id.bt_ok:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

}
