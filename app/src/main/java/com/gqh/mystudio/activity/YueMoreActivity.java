package com.gqh.mystudio.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.YueMoreAdapter;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.gqh.mystudio.entity.YueMoreResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 左邻右舍  约： 更多活动
 */
public class YueMoreActivity extends BaseHttpActivity {



    @ViewInject(R.id.radioButton1)
    private RadioButton rb_1;
    @ViewInject(R.id.radioButton2)
    private RadioButton rb_2;

    @ViewInject(R.id.listView_yue_more)
    private ListView lv;
    private YueMoreAdapter adapter;
    private List<YueMoreEntity> data;
    private YueMoreResult result1;
    private YueMoreResult result2;


    @OnClick({R.id.image_fanhui,R.id.radioButton1,R.id.radioButton2})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                case R.id.radioButton1:
                    //约 最新列表
                    RequestParams params=new RequestParams();
                    params.put("type",0);//最新
                    postDialog(Constant.YUE_LIST, params, 1);
                    break;
                case R.id.radioButton2:
                    //约 以往列表
                    RequestParams params2=new RequestParams();
                    params2.put("type",1);//以往
                    postDialog(Constant.YUE_LIST, params2, 2);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }


    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            if (requestCode==1){
                result1= GsonUtils.getSingleBean(jsonStr, YueMoreResult.class);
                if (result1.isSuccess()){
                    setData(result1.data);
                }
            }

            if (requestCode==2){
                result2= GsonUtils.getSingleBean(jsonStr,YueMoreResult.class);
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

        //约 最新列表
        RequestParams params=new RequestParams();
        params.put("type",0);//最新
        postDialog(Constant.YUE_LIST, params,1);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yue_more;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);

    }

    public void setData(List<YueMoreEntity> data) {

        this.data = data;

        adapter=new YueMoreAdapter(data,this);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int type;
                int groupID;
                if (rb_1.isChecked()){
                    type = result1.data.get(position).getState();
                    groupID = result1.data.get(position).getGroupID();
                }else {
                    type = result2.data.get(position).getState();
                    groupID = result2.data.get(position).getGroupID();
                }

                Intent intent=new Intent(YueMoreActivity.this,YueHuodongDetailActivity.class);
                intent.putExtra("GroupID",groupID);
                intent.putExtra("type",type);
                toActivity(intent);
            }
        });
    }
}
