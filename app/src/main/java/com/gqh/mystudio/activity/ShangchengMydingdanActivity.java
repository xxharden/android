package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.MydingdanAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyOrderResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

public class ShangchengMydingdanActivity extends BaseHttpActivity {

    @ViewInject(R.id.listview)
    private ListView listview;
    private List<MyOrderEntity> data;

    @OnClick(R.id.image_fanhui)
    private void onClick(View v){
        try{

            switch (v.getId()){
                case R.id.image_fanhui:
//                    User user = BamsApplication.getInstance().getUser();
//                    BamsApplication.getInstance().setBackIndex(4);
//                    Intent intent=new Intent(this,MainActivity.class);
//                    intent.putExtra("phone",user.getULoginID());
//                    intent.putExtra("quanxian",BamsApplication.getInstance().getUserStyle());
//                    toActivity(intent);
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shangcheng_mydingdan;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            if (requestCode==1){
                MyOrderResult result= GsonUtils.getSingleBean(jsonStr,MyOrderResult.class);
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
        RequestParams params=new RequestParams();
        params.put("UID", BamsApplication.getInstance().getUser().getUID());
        postDialog(Constant.LIST_MY_ORDER,params,1);
    }

    public void setData(final List<MyOrderEntity> data) {
        this.data = data;
        MydingdanAdapter adapter=new MydingdanAdapter(data,this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ShangchengMydingdanActivity.this,MyOrderDetailActivity.class);
                intent.putExtra("OrderID",data.get(position).getOrderID());
                toActivity(intent);
            }
        });
    }

//    @Override
//    public void onBackPressed() {
////        phone=getIntent().getStringExtra("phone");
////        userStyle=getIntent().getStringExtra("quanxian");
//        BamsApplication.getInstance().setBackIndex(4);
//        Intent intent=new Intent(this,MainActivity.class);
//        intent.putExtra("phone",BamsApplication.getInstance().getUser().getULoginID());
//        intent.putExtra("quanxian",Constant.STYLE_GONGMIN);
//        toActivity(intent);
//        finish();
//        super.onBackPressed();
//    }
}
