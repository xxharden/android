package com.gqh.mystudio.activity;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.FuwuJinduEntity;
import com.gqh.mystudio.entity.FuwuJinduResult;
import com.gqh.mystudio.entity.FuwuResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.entity.User;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class GuanjiaJinduChaxunActivity extends BaseHttpActivity {

    @ViewInject(R.id.chuli)
    private TextView tv_chuli;
    @ViewInject(R.id.image_fanhui)
    private ImageView back;
    @ViewInject(R.id.photo)
    private ImageView photo;
    @ViewInject(R.id.name)
    private TextView tv_name;
    @ViewInject(R.id.room)
    private TextView tv_room;
    @ViewInject(R.id.time)
    private TextView tv_time;
    @ViewInject(R.id.style)
    private TextView tv_style;
    @ViewInject(R.id.yaoqiu)
    private TextView tv_yaoqiu;
    @ViewInject(R.id.photo_1)
    private ImageView iv_photo;
    @ViewInject(R.id.kefu)
    private TextView tv_kefu;
    private User user;
    private int workId;
    private FuwuJinduEntity data;

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private String orderType;


    @OnClick({R.id.image_fanhui, R.id.photo, R.id.photo_1, R.id.kefu})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.photo:

                    break;
                case R.id.photo_1:

                    break;
                case R.id.kefu:
                    //用intent启动拨打电话

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constant.KEFU));
                    startActivity(intent);

                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try {
            MyLog.i("ifaaaaa==",jsonStr);
            FuwuJinduResult result= GsonUtils.getSingleBean(jsonStr, FuwuJinduResult.class);
            if(result.isSuccess()){
                setData(result.data);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        //进度请求
        RequestParams params=new RequestParams();
        params.put("WorkOrderID",workId);
        postDialog(Constant.FUWU_JINDU,params,false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_guanjia_jindu_chaxun;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
//				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//				.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .build();

        workId=getIntent().getIntExtra("workId", 0);
        orderType=getIntent().getStringExtra("orderType");
        user= BamsApplication.getInstance().getUser();
        imageLoader.displayImage(Constant.IMAGE_URL+user.getUHeadPortrait(),photo,options);
        tv_name.setText(user.getUNickName());
    }

    public void setData(FuwuJinduEntity data) {
        this.data = data;

        tv_room.setText(user.getRoomNo());
        tv_style.setText(data.getOrderType());
        tv_yaoqiu.setText(data.getORDERDESC());
        tv_chuli.setText(data.getSTATUS());
        String time = data.getWorkTime();
        if (orderType.equals("投诉")){
            tv_time.setText(time);
        }else {
            tv_time.setText(time.substring(0,time.indexOf(","))+"至"+time.substring(time.indexOf(",")+1,time.length()));
        }

    }
}
