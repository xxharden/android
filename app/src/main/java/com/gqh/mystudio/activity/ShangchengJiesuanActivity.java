package com.gqh.mystudio.activity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.SwipeAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.view.SwipeListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.ACache;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class  ShangchengJiesuanActivity extends BaseHttpActivity {

    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.tv_jine)
    private TextView tv_jine;
    @ViewInject(R.id.bt_zhifu)
    private Button bt_zhifu;
    @ViewInject(R.id.lv)
    private SwipeListView lv;
    private ArrayList<ShangpinListEntity> listEntity;
    private SwipeAdapter adapter;
    private int fId;

    @OnClick({R.id.image_fanhui,R.id.bt_zhifu})
    private void onClick(View v){
        try {
            switch (v.getId()){
                case R.id.image_fanhui:
                    //数据是使用Intent返回
                    //把返回数据存入Intent
                    Intent in = new Intent();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("listEntity",(Serializable)listEntity);
                    MyLog.i("list111111============", listEntity.toString());
                    in.putExtras(bundle);
                    //设置返回数据
                    setResult(RESULT_OK, in);
                    //关闭Activity
                    finish();
                    break;
                case R.id.bt_zhifu:
                    DecimalFormat df   =new DecimalFormat("#.00");
                    //df.format(zongJia)
                    int uId = BamsApplication.getInstance().getUser().getUID();

//                    “ProductID-Number-RealPrice-Price,
                    String product=null;
                    for (int i=0;i<listEntity.size();i++){
                        ShangpinListEntity entity = listEntity.get(i);
                        String pro =entity.getProductID() + "-" + entity.getMyNumber() + "-" + entity.getRealPrice() + "-"+entity.getPrice();
                        if (product==null){
                            product=pro;
                        }else {
                            product+=","+pro;
                        }
                    }

                    MyLog.i("==================================================================","===========================");
                    MyLog.i("product=======",product);
//
//
                    boolean isUpass = BamsApplication.getInstance().getUser().getUPassWord();
                    if(!isUpass){
                        Intent intent=new Intent(this,PhoneYanzhengActivity.class);
                        intent.putExtra("style",2);
                        toActivity(intent);
                    }else {
                        // 跳转验证支付密码
                        //判段有没有设置支付密码
                        boolean isSet = BamsApplication.getInstance().getUser().getUPassWord();
                        if (isSet){
                            Intent intent=new Intent(this,CodePayPassWordActivity.class);
                            if (fId!=0){
                                intent.putExtra("FID",fId);
                            }else {
                                intent.putExtra("FID",uId);
                            }
                            intent.putExtra("Product",product);
                            intent.putExtra("TotalFee",df.format(zongJia));
                            intent.putExtra("style","toMyDingdan");
                            toActivity(intent);
                        }else {
                            Intent intent=new Intent(this,PhoneYanzhengActivity.class);
                            intent.putExtra("style","aa");
                            toActivity(intent);
                        }
                    }

                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shangcheng_jiesuan;
    }

    double zongJia;
    @Override
    protected void initView() {

        ViewUtils.inject(this);
        fId=getIntent().getIntExtra("FID",0);
        listEntity= (ArrayList<ShangpinListEntity>) getIntent().getSerializableExtra("listEntity");
        adapter = new SwipeAdapter(listEntity,this);
        lv.setAdapter(adapter);

        for (int i=0;i<listEntity.size();i++){
            ShangpinListEntity entity = listEntity.get(i);
            double zj = (entity.getRealPrice()) * (entity.getMyNumber());
            zongJia+=zj;
        }

        DecimalFormat df   =new DecimalFormat("#.00");
        tv_jine.setText(df.format(zongJia) + "");

    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

    }

    @Override
    protected void request() {

    }

    public void deleteItem(int position){
        showToast("已删除");
        zongJia-=(listEntity.get(position).getRealPrice())*(listEntity.get(position).getMyNumber());
        listEntity.remove(position);

        if (listEntity.size()==0){
            tv_jine.setText("0");
        }else {
            DecimalFormat df   =new DecimalFormat("#.00");
            tv_jine.setText(df.format(zongJia));
        }

        adapter.notifyDataSetChanged();

    }


    public void addZongjia(double zongjia, int count, int position){
        zongJia+=zongjia;
        DecimalFormat df   =new DecimalFormat("#.00");
        tv_jine.setText(df.format(zongJia) + "");
        listEntity.get(position).setMyNumber(count);
    }

    public void deleteZongjia(double zongjia, int count, int position){
        zongJia-=zongjia;
        DecimalFormat df   =new DecimalFormat("#.00");
//        df.format(zongJia)
//        df.format(你要格式化的数字);
        tv_jine.setText(df.format(zongJia) + "");
        listEntity.get(position).setMyNumber(count);
    }

//    private ACache aCache;
    @Override
    public void onBackPressed() {
//        aCache = ACache.get(context);
//        aCache.put("listEntity",listEntity);

        //数据是使用Intent返回
        //把返回数据存入Intent
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable("listEntity",(Serializable)listEntity);
        MyLog.i("list111111============", listEntity.toString());
        intent.putExtras(bundle);
        //设置返回数据
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }
}
