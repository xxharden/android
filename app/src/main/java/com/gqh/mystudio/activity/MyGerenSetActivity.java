package com.gqh.mystudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;

import cn.myapp.base.BaseActivity;

public class MyGerenSetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_geren_set);

//        phone

        TextView tv_set_myinfo= (TextView) findViewById(R.id.tv_set_myinfo);
        TextView tv_set_password= (TextView) findViewById(R.id.tv_set_password);
        findViewById(R.id.image_fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_set_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyGerenSetActivity.this,SetGerenXinActivity.class);
                intent.putExtra("phone", BamsApplication.getInstance().getUser().getULoginID());
                intent.putExtra("enter","no");
                toActivity(intent);
            }
        });
        tv_set_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(SetLoginPasswordActivity.class);
            }
        });
    }
}
