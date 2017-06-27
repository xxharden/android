package com.gqh.mystudio.activity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.view.MyRatingBar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class Guanjia_PingjiaActivity extends BaseHttpActivity implements MyRatingBar.OnStarChecked {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.et_pingjia)
    private EditText et_pingjia;
    @ViewInject(R.id.bt_queren)
    private Button bt_quren;
    private int numSpeed=0;
    private int numStandard=0;
    private int numattitude=0;
    private int WorkOrderID;

    @OnClick({R.id.image_fanhui,R.id.bt_queren})
    private void onClick(View view){
        try{
            switch (view.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_queren:
                    String text = et_pingjia.getText().toString();
                    //非空验证
                    if (StringUtil.isEmpty(text)){
                        showToast("请对我们的服务进行评价");
                    }

                    //评价请求
                    RequestParams params =new RequestParams();
                    params.put("WorkOrderID",WorkOrderID);
                    params.put("Score",numSpeed+","+numattitude+","+numStandard);
                    params.put("opinion",text);
                    postDialog(Constant.FUWU_PINGJIA,params,false);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            if (jsonObject.getString("code").equals("0")){
                showToast("完成评价");
                finish();
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
        return R.layout.activity_guanjia__pingjia;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        WorkOrderID= getIntent().getIntExtra("WorkOrderID",0);

        MyRatingBar rb_speed = (MyRatingBar) findViewById(R.id.rb_speed);
        MyRatingBar rb_standard = (MyRatingBar) findViewById(R.id.rb_standard);
        MyRatingBar rb_attitude = (MyRatingBar) findViewById(R.id.rb_attitude);

        rb_speed.setOnStarChecked(this);
        rb_standard.setOnStarChecked(this);
        rb_attitude.setOnStarChecked(this);
    }

    @Override
    public void getStarNum(int num, View view) {

        switch (view.getId()) {
            case R.id.rb_speed://速度
                numSpeed=num;
                break;
            case R.id.rb_standard://质量
                numStandard=num;
                break;
            case R.id.rb_attitude://态度
                numattitude=num;
                break;
        }
    }
}
