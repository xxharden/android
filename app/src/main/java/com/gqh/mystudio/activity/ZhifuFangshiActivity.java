package com.gqh.mystudio.activity;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gqh.mystudio.R;
import com.gqh.mystudio.alipay.AlipayUtil;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.wxapi.WXPayUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class ZhifuFangshiActivity extends BaseHttpActivity{


    private String BillID;
    private User user;
    private String jine;
    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.tv_jine)
    private TextView tv_jine;
    @ViewInject(R.id.rl_xinpaibi)
    private RelativeLayout rl_xinpaibi;
    @ViewInject(R.id.rl_weixin)
    private RelativeLayout rl_weixin;
    @ViewInject(R.id.rl_zhifubao)
    private RelativeLayout rl_zhifubao;
    @ViewInject(R.id.bt_zhifu)
    private Button bt_zhifu;

    @ViewInject(R.id.tv_jine_1)
    private TextView tv_jine_1;
    @ViewInject(R.id.tv_jine_2)
    private TextView tv_jine_2;
    @ViewInject(R.id.tv_jine_3)
    private TextView tv_jine_3;

    @ViewInject(R.id.image_dui_1)
    private ImageView image_dui_1;
    @ViewInject(R.id.image_dui_2)
    private ImageView image_dui_2;
    @ViewInject(R.id.image_dui_3)
    private ImageView image_dui_3;
    String type="";
    private IWXAPI api;
    int thridPay=0;
    @OnClick({R.id.image_fanhui,R.id.rl_xinpaibi,
            R.id.rl_weixin,R.id.rl_zhifubao,R.id.bt_zhifu})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.rl_xinpaibi:

                    if(image_dui_1.getVisibility()==View.GONE){
                        image_dui_1.setVisibility(View.VISIBLE);
                        tv_jine_1.setVisibility(View.VISIBLE);

                        image_dui_2.setVisibility(View.GONE);
                        tv_jine_2.setVisibility(View.GONE);

                        image_dui_3.setVisibility(View.GONE);
                        tv_jine_3.setVisibility(View.GONE);
                    }

                    break;
                case R.id.rl_weixin:
                    if(image_dui_2.getVisibility()==View.GONE){
                        image_dui_2.setVisibility(View.VISIBLE);
                        tv_jine_2.setVisibility(View.VISIBLE);

                        image_dui_1.setVisibility(View.GONE);
                        tv_jine_1.setVisibility(View.GONE);

                        image_dui_3.setVisibility(View.GONE);
                        tv_jine_3.setVisibility(View.GONE);
                    }
                    break;
                case R.id.rl_zhifubao:
                    if(image_dui_3.getVisibility()==View.GONE){
                        image_dui_3.setVisibility(View.VISIBLE);
                        tv_jine_3.setVisibility(View.VISIBLE);

                        image_dui_2.setVisibility(View.GONE);
                        tv_jine_2.setVisibility(View.GONE);

                        image_dui_1.setVisibility(View.GONE);
                        tv_jine_1.setVisibility(View.GONE);
                    }
                    break;
                case R.id.bt_zhifu:

                    if (getIntent().getIntExtra("style",0)==100){ //充值
                        type="recharge";
                        if(image_dui_2.getVisibility()==View.VISIBLE){
                            //微信
                            if(!api.isWXAppInstalled()){
                                Toast.makeText(context,"请安装微信客户端",Toast.LENGTH_SHORT).show();
                                break;
                            }else {
                                thridPay=2;
//                                new WXPayUtil(this).startPay("新派币","0.01");
                            }
                        }
                        if(image_dui_3.getVisibility()==View.VISIBLE){
                            //支付宝
                            thridPay=1;
//                           new AlipayUtil(this).starPay("新派币", "新派币", "0.01");
                        }
                    }else { //缴费
                        type="pay";
                        if(image_dui_1.getVisibility()==View.VISIBLE){
                            //新派币
                            Intent intent=new Intent(this,CodePayPassWordActivity.class);
                            intent.putExtra("style","jiaofei");
                            intent.putExtra("Amount",jine);
                            intent.putExtra("BillID",BillID);
                            toActivity(intent);
                            finish();
                            break;
                        }

                        if(image_dui_2.getVisibility()==View.VISIBLE){
                            //微信
                            if(!api.isWXAppInstalled()){
                                Toast.makeText(context,"请安装微信客户端",Toast.LENGTH_SHORT).show();
                                break;
                            }else {
                                thridPay=2;
//                                new WXPayUtil(this).startPay("新派币","0.01");
                            }
                        }
                        if(image_dui_3.getVisibility()==View.VISIBLE){
                            //支付宝
                            thridPay=1;
//                            new AlipayUtil(this).starPay("新派缴费", BillID, "0.01");
                        }
                    }
                    RequestParams params=new RequestParams();
                    params.put("CustID", BamsApplication.getInstance().getUser().getCustID());
                    params.put("Type", type);
                    params.put("Amount",jine);
                    if (type.equals("pay")){
                        params.put("BillID",BillID);
                    }
                    postDialog(Constant.RMB_ORDER,params,1,false);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    String orderNum;
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
            MyLog.i("jsonStr====",jsonStr);
            if (requestCode==1){
                JSONObject object=new JSONObject(jsonStr);
//                if ((object.getString("code")).equals("0")){
                orderNum = object.getString("OUT_TRADE_NO");
                if (thridPay==1){
                    //支付宝
                    new AlipayUtil(this).starPay("新派产品", orderNum, jine);
                }else if (thridPay==2){
                    //微信
                    new WXPayUtil(this).startPay(orderNum,jine);
                }
//                }
            }

            if (requestCode==2){
                JSONObject object=new JSONObject(jsonStr);
                boolean isPayed = object.getBoolean("isPayed");
//                if ((object.getString("code")).equals("0")){
                if (isPayed){
                    if (type.equals("pay")){
                        //缴费
                        showToast("缴费成功");
                        toActivityAfterFinishThis(XinpaiBiActivity.class);
                    }else {
                        //充值
                        showToast("充值成功");
                        toActivityAfterFinishThis(XinpaiBiActivity.class);
                    }
                }else {
                    if (type.equals("pay")){
                        //缴费
                        showToast("缴费失败");
                    }else {
                        //充值
                        showToast("充值失败");
                    }
                }

//                }
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
        return R.layout.activity_zhifu_fangshi;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);

        user=BamsApplication.getInstance().getUser();
        jine= getIntent().getStringExtra("jine");
        BillID = getIntent().getStringExtra("BillID");
        if (getIntent().getIntExtra("style",0)==100){ //充值
            rl_xinpaibi.setVisibility(View.GONE);

            image_dui_2.setVisibility(View.VISIBLE);
            tv_jine_2.setVisibility(View.VISIBLE);

            image_dui_3.setVisibility(View.GONE);
            tv_jine_3.setVisibility(View.GONE);
        }
        tv_jine.setText(jine);
        tv_jine_1.setText(jine);
        tv_jine_2.setText(jine);
        tv_jine_3.setText(jine);
    }

    boolean isFirst=true;
    @Override
    protected void onResume() {
        super.onResume();
        MyLog.i("==========",""+isFirst);
        if (!isFirst){
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    //execute the task
                    sendCheckPay();
                }

            }, 2000);
        }
        isFirst=false;
    }

    //确认支付结果
    public void sendCheckPay(){
        MyLog.i("OUT_TRADE_NO",orderNum);
        RequestParams params=new RequestParams();
        params.put("OUT_TRADE_NO",orderNum);
        postDialog(Constant.RMB_ORDER_CHECKPAYED,params,2,false);
    }
}
