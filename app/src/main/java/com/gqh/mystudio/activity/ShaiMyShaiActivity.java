package com.gqh.mystudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.ShaiDetailAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.ShaiDetaiShowEntity;
import com.gqh.mystudio.entity.ShaiDetailResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class ShaiMyShaiActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.listView_friend)
    private ListView listView;
    private List<ShaiDetaiShowEntity> dataShai;


    @Override
    protected int getContentView() {
        return R.layout.activity_my_hao_you;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            //晒详情
            MyLog.i("json++++", jsonStr);
            ShaiDetailResult result = GsonUtils.getSingleBean(jsonStr, ShaiDetailResult.class);
            if (result.isSuccess()){
                setDataShai(result.data.getShow());
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        RequestParams params=new RequestParams();
        params.put("UID", BamsApplication.getInstance().getUser().getUID());
        postDialog(Constant.SHAI_DETAIL,params);
    }

    public void setDataShai(List<ShaiDetaiShowEntity> dataShai) {
        this.dataShai = dataShai;

        ShaiDetailAdapter adapter=new ShaiDetailAdapter(dataShai,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ShaiMyShaiActivity.this,ShaiDetailActivity.class);
                intent.putExtra("position",position);
                toActivity(intent);
            }
        });
    }
}
