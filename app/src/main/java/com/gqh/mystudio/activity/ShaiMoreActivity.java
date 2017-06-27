package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.ShaiMoreAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.gqh.mystudio.entity.ShaiDongtaiResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class ShaiMoreActivity extends BaseHttpActivity {

    private ShaiMoreAdapter adapter;

    @ViewInject(R.id.radioButton1)
    private RadioButton rb_1;
    @ViewInject(R.id.radioButton2)
    private RadioButton rb_2;

    @ViewInject(R.id.listView_yue_more)
    private ListView lv;
    private ShaiDongtaiResult result1;
    private ShaiDongtaiResult result2;
    private int apartmentId;


    @OnClick({R.id.image_fanhui,R.id.radioButton1,R.id.radioButton2})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                case R.id.radioButton1:
                    //邻居动态
                    RequestParams params=new RequestParams();
                    params.put("ApartmentID", apartmentId);
                    postDialog(Constant.SHAI_LINJU_DONGTAI, params, 1);
                    break;
                case R.id.radioButton2:
                    //公民动态
                    postDialog(Constant.SHAI_GONGMIN_DONGTAI, null, 2);

                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yue_more;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        rb_1.setText("所有邻居");
        rb_2.setText("所有公民");
        apartmentId= BamsApplication.getInstance().getUser().getApartmentID();

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            if (requestCode==1){
                result1= GsonUtils.getSingleBean(jsonStr,ShaiDongtaiResult.class);
                if (result1.isSuccess()){
                    setData(result1.data);
                }

            }

            if (requestCode==2){
                result2= GsonUtils.getSingleBean(jsonStr,ShaiDongtaiResult.class);
                if (result2.isSuccess()){
                    setData(result2.data);
                }
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
    @Override
    protected void request() {

        //邻居动态
        RequestParams params=new RequestParams();
        params.put("ApartmentID", apartmentId);
        postDialog(Constant.SHAI_LINJU_DONGTAI, params, 1);
    }

    public void setData(List<ShaiDongtaiEntity> data) {

        adapter=new ShaiMoreAdapter(data,this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ShaiMoreActivity.this,ShaiDetailActivity.class);
                intent.putExtra("position",position);
                if (rb_2.isChecked()){
                    intent.putExtra("ApartmentID",apartmentId);//公寓id
                }
                toActivity(intent);
            }
        });
    }
}
