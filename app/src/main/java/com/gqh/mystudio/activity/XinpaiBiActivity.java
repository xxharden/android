package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import org.json.JSONObject;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/27
 * desc: 新派币
 */
public class XinpaiBiActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.xinpaibi)
    private TextView xinpaibi;
    @ViewInject(R.id.lishi_zhangdan)
    private TextView lishi_zhangdan;
    @ViewInject(R.id.bt_chongzhi)
    private Button bt_chongzhi;
    @ViewInject(R.id.bt_jiaofei)
    private Button bt_jiaofei;
    @ViewInject(R.id.rl_pas_set)
    private RelativeLayout rl_pas_set;
    @ViewInject(R.id.rl_shop)
    private RelativeLayout rl_shop;
    private User user;

    @OnClick({R.id.image_fanhui,R.id.lishi_zhangdan,
            R.id.bt_chongzhi,R.id.bt_jiaofei,
            R.id.rl_pas_set,R.id.rl_shop})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.lishi_zhangdan:
                    toActivity(XinpaibiLishiZhangdanActivity.class);
                    break;
                case R.id.bt_chongzhi:
                    boolean isUpass = BamsApplication.getInstance().getUser().getUPassWord();
                    if(!isUpass){
                        Intent intent=new Intent(this,PhoneYanzhengActivity.class);
                        intent.putExtra("style",0);
                        toActivity(intent);
                    }else {
                        Intent intent=new Intent(this,ChongzhiActivity.class);
                        toActivity(intent);
                    }
                    break;
                case R.id.bt_jiaofei:
                    Intent intent=new Intent(this,XinpaibiJiaofeiActivity.class);
                    toActivity(intent);
                    break;
                case R.id.rl_pas_set:
                    toActivity(ChooseSetPasswordActivity.class);
                    break;

                case R.id.rl_shop:
                    BamsApplication.getInstance().setBackIndex(1);
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {
            if (requestCode==1){
                MyLog.i("aaaa==",jsonStr);
                JSONObject object=new JSONObject(jsonStr);
                if ( object.getString("code").equals("0")){
                    JSONObject obj = object.getJSONObject("data");
                    double Balance = obj.getDouble("Balance");
                    xinpaibi.setText(Balance+"");
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void request() {
        //发送获取余额 请求
        RequestParams params1=new RequestParams();
        params1.put("CustID",user.getCustID());
        postDialog(Constant.XINPAIBI_YU_E,params1, 1,false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xinpai_bi;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        user=BamsApplication.getInstance().getUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //发送获取余额 请求
        RequestParams params1=new RequestParams();
        params1.put("CustID",user.getCustID());
        postDialog(Constant.XINPAIBI_YU_E, params1, 1,false);
    }
}
