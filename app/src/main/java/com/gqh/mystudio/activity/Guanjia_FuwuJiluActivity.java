package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.AdapterFuwuJilu_2_listView;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FuwuEntity;
import com.gqh.mystudio.entity.FuwuResult;


import org.json.JSONObject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class Guanjia_FuwuJiluActivity extends BaseHttpActivity implements AdapterView.OnItemClickListener {

    private ImageView back;
    private ListView lv;
    private User user;
    private List<FuwuEntity> data;

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            MyLog.i("json=====",jsonStr);
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

        //服务列表
        RequestParams params=new RequestParams();
        params.put("CustId",user.getCustID());
        postDialog(Constant.FUWU_LIEBIAO,params,false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_guanjia__fuwu_jilu;
    }

    @Override
    protected void initView() {

        user= BamsApplication.getInstance().getUser();
        back= (ImageView) findViewById(R.id.image_fanhui);
        lv= (ListView) findViewById(R.id.listview);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setData(List<FuwuEntity> data) {

        this.data = data;

        AdapterFuwuJilu_2_listView adapter=new AdapterFuwuJilu_2_listView(data,this);
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
