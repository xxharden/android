package com.gqh.mystudio.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.JiaofeiDetailAdapter;
import com.gqh.mystudio.entity.DaijiaofeiEntity;
import com.gqh.mystudio.entity.DaijiaofeiResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class XinpaibiJiaofeiDetailActivity extends BaseHttpActivity {


    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.image_fanhui)
    private ImageView image_fanhui;
    @ViewInject(R.id.tv_heji)
    private TextView tv_heji;
    @ViewInject(R.id.listview)
    private ListView lv;
    private String businessFlow;
    private List<DaijiaofeiEntity> data;
    private int amount=0;
    @Override
    protected int getContentView() {
        return R.layout.activity_xinpaibi_jiaofei_detail;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);
        businessFlow=getIntent().getStringExtra("BusinessFlow");
        String busiDate = getIntent().getStringExtra("BusiDate");
        tv_time.setText(busiDate);
        image_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{

            DaijiaofeiResult result= GsonUtils.getSingleBean(jsonStr,DaijiaofeiResult.class);
            if (result.isSuccess()){
                setData(result.data);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected void request() {
        RequestParams params=new RequestParams();
        params.put("BusinessFlow",businessFlow);
        postDialog(Constant.RMB_DETAIL_BUSINESS,params,false);
    }


    public void setData(List<DaijiaofeiEntity> data) {
        this.data = data;

        JiaofeiDetailAdapter adapter=new JiaofeiDetailAdapter(data,this);
        lv.setAdapter(adapter);
        for (int i=0;i<data.size();i++){
            amount+=data.get(i).getAmount();
        }

        tv_heji.setText("合计："+amount);
    }
}
