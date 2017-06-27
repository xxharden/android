package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.QiaoMoreAdapter;
import com.gqh.mystudio.adapter.ShaiMoreAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.QiaoMoreEntity;
import com.gqh.mystudio.entity.QiaoMoreResult;
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
import cn.myapp.util.MyLog;
import http.RequestParams;

public class QiaoMoreActivity extends BaseHttpActivity {

    private QiaoMoreAdapter adapter;
    private QiaoMoreResult result1;
    private QiaoMoreResult result2;

    @ViewInject(R.id.radioButton1)
    private RadioButton rb_1;
    @ViewInject(R.id.radioButton2)
    private RadioButton rb_2;

    @ViewInject(R.id.listView_yue_more)
    private ListView lv;
    private int uId;


    @OnClick({R.id.image_fanhui,R.id.radioButton1,R.id.radioButton2})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                case R.id.radioButton1:
                    //敲最新
                    RequestParams params1=new RequestParams();
                    params1.put("UID",uId);//用户ID
                    params1.put("Type",0);//0-最新公民 1最热公民
                    postDialog(Constant.QIAO_LIEBIAO, params1, 1);
                    break;
                case R.id.radioButton2:
                    //敲最热
                    RequestParams params2=new RequestParams();
                    params2.put("UID",uId);//用户ID
                    params2.put("Type",1);//0-最新公民 1最热公民
                    postDialog(Constant.QIAO_LIEBIAO,params2,2);

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

        rb_1.setText("最新邻居");
        rb_2.setText("最热邻居");

        uId= BamsApplication.getInstance().getUser().getUID();


    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{
            if (requestCode==1){////敲最新
                result1= GsonUtils.getSingleBean(jsonStr,QiaoMoreResult.class);
                if (result1.isSuccess()){
                    setData(result1.data);
                }
            }

            if (requestCode==2){ //敲最热

                result2= GsonUtils.getSingleBean(jsonStr,QiaoMoreResult.class);
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

        //敲最新
        RequestParams params1=new RequestParams();
        params1.put("UID",uId);//用户ID
        params1.put("Type",0);//0-最新公民 1最热公民
        postDialog(Constant.QIAO_LIEBIAO,params1,1);
    }

    public void setData(List<QiaoMoreEntity> data) {

        adapter=new QiaoMoreAdapter(data,this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int uuId;
                if (rb_1.isChecked()) {
                    uuId = result1.data.get(position).getUID();
                } else {
                    uuId = result2.data.get(position).getUID();
                }
                MyLog.i("UID", uuId + "");
                Intent intent = new Intent(QiaoMoreActivity.this, ShaiGerenXinxiActivity.class);
                intent.putExtra("UID", uuId);
                toActivity(intent);
            }
        });
    }
}
