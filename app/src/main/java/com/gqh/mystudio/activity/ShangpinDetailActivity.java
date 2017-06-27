package com.gqh.mystudio.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.event.AddToListEvent;
import com.gqh.mystudio.view.SlideShowView2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.MyLog;
import de.greenrobot.event.EventBus;

/**
 * Created by 红哥哥 on 2016/8/13.
 * 商品详情
 */
public class ShangpinDetailActivity extends BaseActivity {
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();

    @ViewInject(R.id.autoGallery)
    private SlideShowView2 mSlideShowView2;
    @ViewInject(R.id.name)
    private TextView name;
    @ViewInject(R.id.price)
    private TextView price;
    @ViewInject(R.id.detail)
    private TextView detail;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activit_shangpin_detail);
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
        ShangpinListEntity data= (ShangpinListEntity) getIntent().getSerializableExtra("shangpin");

        setData(data);
    }


    ShangpinListEntity mData;
    private void setData(ShangpinListEntity data) {
        mData=data;
        List<String> linkTopList=new ArrayList<String>();
        String detailPics= mData.getDetailPic();
        MyLog.e("图片",detailPics);
        if (detailPics!=null&&detailPics.length()>0){
            if (detailPics.indexOf(",")==-1){
                linkTopList.add(Constant.IMAGE_URL+detailPics);
            }else {
                String[] list = detailPics.split(",");
                for (int i=0;i<list.length;i++){
                    linkTopList.add(Constant.IMAGE_URL +"odm/"+ list[i]);
                    MyLog.e("图片",Constant.IMAGE_URL +"odm/"+ list[i]);
                }
            }
        }else {
            linkTopList.add(mData.getPicPath());
        }

        mSlideShowView2.setImageUrls(linkTopList);

        name.setText(data.getProName());
        price.setText("￥"+data.getRealPrice());
        detail.setText(data.getDesc());
    }

    @OnClick({R.id.btAdd,R.id.image_fanhui})
    private void onClick(View v){
        try{

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.btAdd:
                    //添加到购物车
                    AddToListEvent event = new AddToListEvent(mData);
                    event.setData(mData);
                    EventBus.getDefault().post(event);
                    showToast("已添加到购物车");
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
}
