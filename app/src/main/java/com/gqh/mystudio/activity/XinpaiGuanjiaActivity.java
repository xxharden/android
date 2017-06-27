package com.gqh.mystudio.activity;



import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.AdapterFuwuJilu_listView;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FuwuEntity;
import com.gqh.mystudio.entity.FuwuResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import org.json.JSONObject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class XinpaiGuanjiaActivity extends BaseHttpActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.iv_baojie)
    private ImageView iv_baojie;
    @ViewInject(R.id.iv_weixiu)
    private ImageView iv_weixiu;
    @ViewInject(R.id.iv_tousu)
    private ImageView iv_tousu;

    @ViewInject(R.id.tv_jilu)
    private TextView tv_jilu;

    @ViewInject(R.id.listview)
    private ListView lv;
    private List<FuwuEntity> data;
    private User user;

    @OnClick({R.id.iv_baojie,
            R.id.iv_weixiu,
            R.id.iv_tousu,
            R.id.tv_jilu,
            R.id.image_fanhui})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.iv_baojie:
                    Intent intent=new Intent(this,Guanjia_XinzengFuwuActivity.class);
                    intent.putExtra("style", "室内清洁");
                    toActivity(intent);
                    break;
                case  R.id.iv_weixiu:

                    Intent intent2=new Intent(this,Guanjia_XinzengFuwuActivity.class);
                    intent2.putExtra("style", "室内维修");
                    toActivity(intent2);
                    break;
                case R.id.iv_tousu:
                    Intent intent3=new Intent(this,Guanjia_XinzengFuwuActivity.class);
                    intent3.putExtra("style", "建议投诉");
                    toActivity(intent3);
                    break;

                case R.id.tv_jilu:
                    toActivity(Guanjia_FuwuJiluActivity.class);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xinpai__guanjia;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        user= BamsApplication.getInstance().getUser();
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            MyLog.i("json=====", jsonStr);
            FuwuResult result = GsonUtils.getSingleBean(jsonStr, FuwuResult.class);
            if(result.isSuccess()){
                setData(result.data);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        if (!BamsApplication.getInstance().getUserStyle().equals(Constant.STYLE_FANGKE)){
            RequestParams params=new RequestParams();
            params.put("CustId",user.getCustID());
            postDialog(Constant.FUWU_LIEBIAO,params,false);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!BamsApplication.getInstance().getUserStyle().equals(Constant.STYLE_FANGKE)){
            RequestParams params=new RequestParams();
            params.put("CustId",user.getCustID());
            postDialog(Constant.FUWU_LIEBIAO,params,false);
        }
    }

    public void setData(List<FuwuEntity> data) {

        this.data = data;

        AdapterFuwuJilu_listView adapter=new AdapterFuwuJilu_listView(data,this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int workId = data.get(position).getWorkOrderID();
        Intent intent=new Intent(this,GuanjiaJinduChaxunActivity.class);
        intent.putExtra("workId",workId);
        intent.putExtra("orderType",data.get(position).getOrderType());
        toActivity(intent);

    }
}
