package com.gqh.mystudio.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gqh.mystudio.utill.KeyboardEnum.ActionEnum;
import com.gqh.mystudio.R;
import com.gqh.mystudio.utill.KeyboardEnum;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnLongClick;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;
import http.RequestParams;

@SuppressLint("InflateParams")
public class SetPayPasswordActivity extends BaseHttpActivity {

    private ArrayList<String> mList=new ArrayList<String>();

    @ViewInject(R.id.pay_keyboard_del)
    private ImageView del;
    @ViewInject(R.id.pay_keyboard_zero)
    private ImageView zero;
    @ViewInject(R.id.pay_keyboard_one)
    private ImageView one;
    @ViewInject(R.id.pay_keyboard_two)
    private ImageView two;
    @ViewInject(R.id.pay_keyboard_three)
    private ImageView three;
    @ViewInject(R.id.pay_keyboard_four)
    private ImageView four;
    @ViewInject(R.id.pay_keyboard_five)
    private ImageView five;
    @ViewInject(R.id.pay_keyboard_sex)
    private ImageView sex;
    @ViewInject(R.id.pay_keyboard_seven)
    private ImageView seven;
    @ViewInject(R.id.pay_keyboard_eight)
    private ImageView eight;
    @ViewInject(R.id.pay_keyboard_nine)
    private ImageView nine;
    @ViewInject(R.id.pay_box1)
    private TextView box1;
    @ViewInject(R.id.pay_box2)
    private TextView box2;
    @ViewInject(R.id.pay_box3)
    private TextView box3;
    @ViewInject(R.id.pay_box4)
    private TextView box4;
    @ViewInject(R.id.pay_box5)
    private TextView box5;
    @ViewInject(R.id.pay_box6)
    private TextView box6;
    private String code;
    private String phone;
    private int style;


    @OnClick({R.id.pay_keyboard_space,R.id.fanhui,R.id.pay_keyboard_del,R.id.pay_keyboard_zero,
            R.id.pay_keyboard_one,R.id.pay_keyboard_two,
            R.id.pay_keyboard_three,R.id.pay_keyboard_four,
            R.id.pay_keyboard_five,R.id.pay_keyboard_sex,
            R.id.pay_keyboard_seven,R.id.pay_keyboard_eight,R.id.pay_keyboard_nine})
    private void onClick(View v){
        if (v.getId()==R.id.fanhui){
            finish();
        }
        if(v==zero){
            parseActionType(KeyboardEnum.zero);
        }else if(v==one){
            parseActionType(KeyboardEnum.one);
        }else if(v==two){
            parseActionType(KeyboardEnum.two);
        }else if(v==three){
            parseActionType(KeyboardEnum.three);
        }else if(v==four){
            parseActionType(KeyboardEnum.four);
        }else if(v==five){
            parseActionType(KeyboardEnum.five);
        }else if(v==sex){
            parseActionType(KeyboardEnum.sex);
        }else if(v==seven){
            parseActionType(KeyboardEnum.seven);
        }else if(v==eight){
            parseActionType(KeyboardEnum.eight);
        }else if(v==nine){
            parseActionType(KeyboardEnum.nine);
        }else if(v==del){
            parseActionType(KeyboardEnum.del);
        }else if (v.getId()==R.id.pay_keyboard_space&&mList.size()==6){
            String payValue = "";
            for (int i = 0; i < mList.size(); i++) {
                payValue += mList.get(i);
            }

            //跳转确认密码界面
            Intent intent=new Intent(this,SetPayPasswordQuerenActivity.class);
            intent.putExtra("password",payValue);
            intent.putExtra("code",code);
            intent.putExtra("phone",phone);
            intent.putExtra("style",style);
            toActivity(intent);
        }
    }

    @OnLongClick(R.id.pay_keyboard_del)
    public boolean onLongClick(View v) {
        // TODO Auto-generated method stub
        parseActionType(KeyboardEnum.longdel);
        return false;
    }

    private void parseActionType(KeyboardEnum type) {

        if(type.getType()==ActionEnum.add){
            if(mList.size()<6){
                mList.add(type.getValue());
                updateUi();
            }
            //点击删除键
        }else if(type.getType()==ActionEnum.delete){
            if(mList.size()>0){
                mList.remove(mList.get(mList.size()-1));
                updateUi();
            }

            //长按删除键
        }else if(type.getType()==ActionEnum.longClick){
            mList.clear();
            updateUi();

        }
    }
    private void updateUi() {
        // TODO Auto-generated method stub
        if(mList.size()==0){
            box1.setText("");
            box2.setText("");
            box3.setText("");
            box4.setText("");
            box5.setText("");
            box6.setText("");
        }else if(mList.size()==1){
            box1.setText(mList.get(0));
            box2.setText("");
            box3.setText("");
            box4.setText("");
            box5.setText("");
            box6.setText("");
        }else if(mList.size()==2){
            box1.setText(mList.get(0));
            box2.setText(mList.get(1));
            box3.setText("");
            box4.setText("");
            box5.setText("");
            box6.setText("");
        }else if(mList.size()==3){
            box1.setText(mList.get(0));
            box2.setText(mList.get(1));
            box3.setText(mList.get(2));
            box4.setText("");
            box5.setText("");
            box6.setText("");
        }else if(mList.size()==4){
            box1.setText(mList.get(0));
            box2.setText(mList.get(1));
            box3.setText(mList.get(2));
            box4.setText(mList.get(3));
            box5.setText("");
            box6.setText("");
        }else if(mList.size()==5){
            box1.setText(mList.get(0));
            box2.setText(mList.get(1));
            box3.setText(mList.get(2));
            box4.setText(mList.get(3));
            box5.setText(mList.get(4));
            box6.setText("");
        }else if(mList.size()==6){
            box1.setText(mList.get(0));
            box2.setText(mList.get(1));
            box3.setText(mList.get(2));
            box4.setText(mList.get(3));
            box5.setText(mList.get(4));
            box6.setText(mList.get(5));
        }
    }



    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

    }

    @Override
    protected void request() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_set_pay_password;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        code=getIntent().getStringExtra("code");
        phone=getIntent().getStringExtra("phone");
        style=getIntent().getIntExtra("style",0);
    }
}
