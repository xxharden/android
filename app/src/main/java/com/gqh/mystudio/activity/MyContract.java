package com.gqh.mystudio.activity;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.GrideviewAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyContractEntity;
import com.gqh.mystudio.entity.MyContractResult;
import com.gqh.mystudio.view.GridViewForScrollView;
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
import cn.myapp.util.StringUtil;
import http.RequestParams;

public class MyContract extends BaseHttpActivity {

    @ViewInject(R.id.tv_myroom)
    private TextView tv_myroom;
    @ViewInject(R.id.tv_hetong_qixian)
    private TextView tv_hetong_qixian;
    @ViewInject(R.id.tv_endTime)
    private TextView tv_endTime;
    @ViewInject(R.id.tv_room_pay)
    private TextView tv_room_pay;
    @ViewInject(R.id.tv_beizhu)
    private TextView tv_beizhu;
    @ViewInject(R.id.gridview_beizhu)
    private GridViewForScrollView gridview_beizhu;
    @ViewInject(R.id.gridview_hetong)
    private GridViewForScrollView gridview_hetong;
    private MyContractEntity data;

    @OnClick({R.id.image_fanhui})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                /*case R.id.iv_hetong_1:
                    Intent intent1=new Intent(this,PictureShowActivity.class);
                    intent1.putExtra("picId",hetong1_url);
                    toActivity(intent1);
                    break;
                case R.id.iv_hetong_2:
                    Intent intent2=new Intent(this,PictureShowActivity.class);
                    intent2.putExtra("picId", hetong2_url);
                    toActivity(intent2);
                    break;*/
            }
        }catch (Exception e){

        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_contract;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try {

            if (requestCode==1){
                MyContractResult result= GsonUtils.getSingleBean(jsonStr,MyContractResult.class);
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
        params.put("CustID", BamsApplication.getInstance().getUser().getCustID());
        postDialog(Constant.MY_CONTRACT,params,1);
    }

    public void setData(MyContractEntity data) {
        this.data = data;

        tv_myroom.setText(data.getApartmentName()+" "+data.getRoomNo());
        tv_hetong_qixian.setText(data.getBeginDate()+"至"+data.getEndDate());
        if (StringUtil.isEmpty(data.getFreeEndDate())){
            tv_endTime.setText(data.getEndDate());
        }else {
            tv_endTime.setText(data.getFreeEndDate());
        }

        tv_room_pay.setText(data.getRent() + "");
        tv_beizhu.setText("备注：" + data.getRoomRemark());

        String beizhuPic = data.getRoomRemarkPic();
        List<String> mListBeizhu=new ArrayList<String>();
        if (beizhuPic.indexOf(";")==-1){
            mListBeizhu.add(Constant.IMAGE_URL+beizhuPic);
        }else {
            String[] list = beizhuPic.split(";");
            for (int i=0;i<list.length;i++){
                mListBeizhu.add(Constant.IMAGE_URL + list[i]);
            }
        }
        GrideviewAdapter adapterBeizhu=new GrideviewAdapter(mListBeizhu,this,Constant.STYLE_ADAPTER_OK);
        gridview_beizhu.setAdapter(adapterBeizhu);


        String hetongPic=data.getContractPic();
        List<String> mListHetong=new ArrayList<String>();
        if (hetongPic.indexOf(";")==-1){
            mListHetong.add(Constant.IMAGE_URL+beizhuPic);
        }else {
            String[] list = hetongPic.split(";");
            for (int i=0;i<list.length;i++){
                mListHetong.add(Constant.IMAGE_URL + list[i]);
            }
        }
        GrideviewAdapter adapterHetong=new GrideviewAdapter(mListHetong,this,Constant.STYLE_ADAPTER_OK);
        gridview_hetong.setAdapter(adapterHetong);
    }
}
