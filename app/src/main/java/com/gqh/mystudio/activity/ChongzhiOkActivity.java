package com.gqh.mystudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gqh.mystudio.R;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.ExceptionUtil;

public class ChongzhiOkActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi_ok);
    }

    public void doClick(View v){
        try {
            switch (v.getId()){
                case R.id.bt_x:
                    finish();
                    break;

                case R.id.bt_ok:
                    Intent intent=new Intent(this,XinpaiBiActivity.class);
                    startActivityForResult(intent,1);

                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

}
