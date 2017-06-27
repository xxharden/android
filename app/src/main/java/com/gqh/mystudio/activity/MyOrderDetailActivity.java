package com.gqh.mystudio.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.MyOrderDetailAdapter;
import com.gqh.mystudio.entity.MyOrderProductEntity;
import com.gqh.mystudio.entity.MyOrderProductResult;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.view.SwipeListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.DecimalFormat;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class MyOrderDetailActivity extends BaseHttpActivity {

    @ViewInject(R.id.bt_zhifu)
    private Button bt_zhifu;
    @ViewInject(R.id.tv_state)
    private TextView tv_state;
    @ViewInject(R.id.tv_jine)
    private TextView tv_jine;
    @ViewInject(R.id.lv)
    private SwipeListView lv;
    @ViewInject(R.id.listview)
    private ListView listview;
    @OnClick({R.id.image_fanhui})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    private int orderId;
    private List<MyOrderProductEntity> data;

    @Override
    protected int getContentView() {
        return R.layout.activity_shangcheng_jiesuan;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        bt_zhifu.setVisibility(View.GONE);
        lv.setVisibility(View.GONE);
        listview.setVisibility(View.VISIBLE);
        tv_state.setVisibility(View.VISIBLE);
        orderId=getIntent().getIntExtra("OrderID",0);
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {

            if (requestCode==1){
                MyOrderProductResult result= GsonUtils.getSingleBean(jsonStr,MyOrderProductResult.class);
                if (result.isSuccess()){
                    setData(result.data);
                }
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        if (orderId!=0){
            RequestParams params= new RequestParams();
            params.put("OrderID",orderId);
            postDialog(Constant.LIST_ORDER_PRODUCT,params,1);
        }

    }

    private double zongJia;
    public void setData(List<MyOrderProductEntity> data) {
        this.data = data;

        MyOrderDetailAdapter adapter=new MyOrderDetailAdapter(data,this);
        listview.setAdapter(adapter);


        for (int i=0;i<data.size();i++){
            MyOrderProductEntity entity = data.get(i);
            double zj = (entity.getRealPrice()) * (entity.getNumber());
            zongJia+=zj;
        }

        DecimalFormat df   =new DecimalFormat("#.00");
        tv_jine.setText(df.format(zongJia) + "");
    }
}
