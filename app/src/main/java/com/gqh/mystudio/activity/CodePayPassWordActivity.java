package com.gqh.mystudio.activity;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.utill.KeyboardEnum;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnLongClick;

import org.json.JSONObject;

import java.util.ArrayList;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import http.RequestParams;

@SuppressLint("InflateParams")
public class CodePayPassWordActivity extends BaseHttpActivity {
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
    private int uId;
    private int fId;
    private String product;
    private String jinE;
    private String amount;
    private String BillID;
//    private int state;
    private String style;


    @OnClick({R.id.fanhui,R.id.pay_keyboard_del,R.id.pay_keyboard_zero,
            R.id.pay_keyboard_one,R.id.pay_keyboard_two,
            R.id.pay_keyboard_three,R.id.pay_keyboard_four,
            R.id.pay_keyboard_five,R.id.pay_keyboard_sex,
            R.id.pay_keyboard_seven,R.id.pay_keyboard_eight,
            R.id.pay_keyboard_space,R.id.pay_keyboard_nine})
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


            //验证 支付密码//比对6位支付密码，正确跳转支付成功界面，错误提示密码错误
            RequestParams params=new RequestParams();
            params.put("UID",uId);
            params.put("Pwd",payValue);
            postDialog(Constant.CHECK_PAY_PASSWORD,params,1,false);
        }
    }

    @OnLongClick(R.id.pay_keyboard_del)
    public boolean onLongClick(View v) {
        // TODO Auto-generated method stub
        parseActionType(KeyboardEnum.longdel);
        return false;
    }


    private void parseActionType(KeyboardEnum type) {

        if(type.getType()== KeyboardEnum.ActionEnum.add){
            if(mList.size()<6){
                mList.add(type.getValue());
                updateUi();
            }

            //点击删除键
        }else if(type.getType()== KeyboardEnum.ActionEnum.delete){
            if(mList.size()>0){
                mList.remove(mList.get(mList.size()-1));
                updateUi();
            }

            //长按删除键
        }else if(type.getType()== KeyboardEnum.ActionEnum.longClick){
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
        try {
            MyLog.i("json======",jsonStr);
            if (requestCode==1){
                JSONObject jsonObject=new JSONObject(jsonStr);
                if (jsonObject.getString("code").equals("0")){
                    /*if (state==10){//缴费
                        RequestParams params=new RequestParams();
                        params.put("CustID", BamsApplication.getInstance().getUser().getCustID());
                        params.put("PayMode", "03_003_1");
                        params.put("BillID",BillID);//账单ID，数组(集合)形式
                        params.put("TotalFee",amount);
                        postDialog(Constant.RMB_PAY,params,2,false);
                    }else if (state==11){
                        //        支付请求
                        RequestParams params=new RequestParams();
                        params.put("UID",uId);
                        params.put("FID",fId);
                        params.put("Product",product);
                        params.put("TotalFee",jinE);
                        postDialog(Constant.ORDER_PRODUCT,params,2,false);
                    }*/

                    if (style.equals("toMyDingdan")){
                        //        支付请求
                        RequestParams params=new RequestParams();
                        params.put("UID",uId);
                        params.put("FID",fId);
                        params.put("Product",product);
                        params.put("TotalFee",jinE);
                        postDialog(Constant.ORDER_PRODUCT,params,2,false);
                    }else if (style.equals("jiaofei")){//j缴费
                        RequestParams params=new RequestParams();
                        params.put("CustID", BamsApplication.getInstance().getUser().getCustID());
                        params.put("PayMode", "03_003_6");
                        params.put("BillID",BillID);//账单ID，数组(集合)形式
                        params.put("TotalFee",amount);
                        postDialog(Constant.RMB_PAY,params,2,false);
                    }
                }else {
                    showToast("密码输入错误，请重新输入");
                    mList.clear();
                    updateUi();
                }
            }

            if (requestCode==2){
                JSONObject obj=new JSONObject(jsonStr);
                if (obj.getString("code").equals("0")){

                    //跳转界面
                   /* if (state==10){
                        //缴费
                        showToast("缴费成功");
                        toActivityAfterFinishThis(XinpaiBiActivity.class);
                    }else if (state==11){
                        showToast("支付成功");
                        toActivityAfterFinishThis(ShangchengMydingdanActivity.class);
                    }*/

                    if (style.equals("toMyDingdan")){//
                     showToast("支付成功");
                        toActivityAfterFinishThis(ShangchengMydingdanActivity.class);
                    }else if (style.equals("jiaofei")){
                        //缴费
                        showToast("缴费成功");
                        toActivityAfterFinishThis(XinpaiBiActivity.class);
                    }
                }else{
                    showToast(obj.getString("message"));
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
        return R.layout.activity_code_pay_pass_word;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        uId=BamsApplication.getInstance().getUser().getUID();
//        state=getIntent().getIntExtra("state",0);
        fId=getIntent().getIntExtra("FID", 0);
        product=getIntent().getStringExtra("Product");
        style=getIntent().getStringExtra("style");
        jinE=getIntent().getStringExtra("TotalFee");
        amount=getIntent().getStringExtra("Amount");
        BillID=getIntent().getStringExtra("BillID");
    }

}
