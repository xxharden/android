package com.gqh.mystudio.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.LinkListEntity;
import com.gqh.mystudio.entity.LinkListResult;
import com.gqh.mystudio.view.SlideShowView2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/23
 * desc: 新派link
 */

@SuppressLint("InflateParams")
public class XinpaiLinkActivity extends BaseHttpActivity {

    @ViewInject(R.id.autoGallery)
    private SlideShowView2 mSlideShowView2;
    @ViewInject(R.id.image_fanhui)
    private ImageView iv_back;
    @ViewInject(R.id.tv_ruzhu)
    private TextView tv_ruzhu;
    @ViewInject(R.id.image_1)
    private ImageView iv_1;
    @ViewInject(R.id.image_2)
    private ImageView iv_2;
    @ViewInject(R.id.image_3)
    private ImageView iv_3;
    @ViewInject(R.id.image_4)
    private ImageView iv_4;
    @ViewInject(R.id.image_5)
    private ImageView iv_5;
    @ViewInject(R.id.image_6)
    private ImageView iv_6;
    @ViewInject(R.id.image_7)
    private ImageView iv_7;
    @ViewInject(R.id.image_8)
    private ImageView iv_8;
    @ViewInject(R.id.image_9)
    private ImageView iv_9;
    @ViewInject(R.id.image_10)
    private ImageView iv_10;
    @ViewInject(R.id.meishi)
    private ImageView meishi;
    @ViewInject(R.id.dianying)
    private ImageView dianying;
    @ViewInject(R.id.tuangou)
    private ImageView tuangou;
    @ViewInject(R.id.waimai)
    private ImageView waimai;
    @ViewInject(R.id.lvxing)
    private ImageView lvxing;
    @ViewInject(R.id.liren)
    private ImageView liren;
    @ViewInject(R.id.gouwu)
    private ImageView gouwu;
    @ViewInject(R.id.chuxing)
    private ImageView chuxing;
    @ViewInject(R.id.fuwu)
    private ImageView fuwu;

    @OnClick({R.id.image_fanhui,R.id.tv_ruzhu,R.id.image_1,
            R.id.image_2,R.id.image_3,R.id.image_4,
            R.id.image_5,R.id.image_6,R.id.image_7,
            R.id.image_8,R.id.image_9,R.id.image_9,
            R.id.meishi,R.id.dianying,R.id.tuangou,
            R.id.waimai,R.id.lvxing,R.id.liren,
            R.id.gouwu,R.id.chuxing,R.id.fuwu})
    private void onClick(View view){
        try{
            switch (view.getId()){
                case R.id.image_fanhui:
                    //返回
                    finish();
                    break;
                case R.id.tv_ruzhu:
                    //商家入驻
                    toActivity(ShangjiaRuzhuActivity.class);
                    break;
                case R.id.image_1:
//                    https://www.starbucks.com.cn/   星巴克
                    if (linkBottomList.get(0)!=null){
                        Intent intent11=new Intent(this,WoYaoZuXinpaiActivity.class);
//                    intent11.putExtra("URL",Constant.URL_XINGBAKE);
                        intent11.putExtra("URL",linkBottomList.get(0).getUrl());
                        toActivity(intent11);
                    }
                    break;
                case R.id.image_2:
                    if (linkBottomList.get(1)!=null){
                        Intent intent12=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent12.putExtra("URL",linkBottomList.get(1).getUrl());
                        toActivity(intent12);
                    }
                    break;
                case R.id.image_3:
                    if (linkBottomList.get(2)!=null){
                        Intent intent13=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent13.putExtra("URL",linkBottomList.get(2).getUrl());
                        toActivity(intent13);
                    }
                    break;
                case R.id.image_4:
                    if (linkBottomList.get(3)!=null){
                        Intent intent14=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent14.putExtra("URL",linkBottomList.get(3).getUrl());
                        toActivity(intent14);
                    }

                    break;
                case R.id.image_5:
                    if (linkBottomList.get(4)!=null){
                        Intent intent15=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent15.putExtra("URL",linkBottomList.get(4).getUrl());
                        toActivity(intent15);
                    }

                    break;
                case R.id.image_6:
                    if (linkBottomList.get(5)!=null){
                        Intent intent16=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent16.putExtra("URL",linkBottomList.get(5).getUrl());
                        toActivity(intent16);
                    }

                    break;
                case R.id.image_7:
                    if (linkBottomList.get(6)!=null){
                        Intent intent17=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent17.putExtra("URL",linkBottomList.get(6).getUrl());
                        toActivity(intent17);
                    }

                    break;
                case R.id.image_8:
                    //                    https://www.uber.com.cn/       优步
                    if (linkBottomList.get(7)!=null){
                        Intent intent20=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent20.putExtra("URL",linkBottomList.get(7).getUrl());
                        toActivity(intent20);
                    }

                    break;
                case R.id.image_9:
                    if (linkBottomList.get(8)!=null){
                        Intent intent20=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent20.putExtra("URL",linkBottomList.get(8).getUrl());
                        toActivity(intent20);
                    }
                    break;
                case R.id.image_10:
                    if (linkBottomList.get(9)!=null){
                        Intent intent20=new Intent(this,WoYaoZuXinpaiActivity.class);
                        intent20.putExtra("URL",linkBottomList.get(9).getUrl());
                        toActivity(intent20);
                    }
                    break;
                case R.id.meishi:
                    //美食
                    Intent intent=new Intent(this,LinkFenleiDetailActivity.class);
                    intent.putExtra("fenlei","美食");
                    toActivity(intent);
                    break;
                case R.id.dianying:
                    //电影
                    Intent intent2=new Intent(this,LinkFenleiDetailActivity.class);
                    intent2.putExtra("fenlei","电影");
                    toActivity(intent2);
                    break;
                case R.id.tuangou://团购
                    Intent intent3=new Intent(this,LinkFenleiDetailActivity.class);
                    intent3.putExtra("fenlei","团购");
                    toActivity(intent3);
                    break;
                case R.id.waimai://外卖
                  /*  Intent intent4=new Intent(this,LinkFenleiDetailActivity.class);
                    intent4.putExtra("fenlei","外卖");
                    toActivity(intent4);*/
                    Intent intent4=new Intent(this,WoYaoZuXinpaiActivity.class);
                    intent4.putExtra("URL","http://waimai.baidu.com/waimai/shoplist/c63ab3051c9a6892");
                    toActivity(intent4);
                    break;
                case R.id.lvxing://旅行
                    Intent intent5=new Intent(this,LinkFenleiDetailActivity.class);
                    intent5.putExtra("fenlei","旅行");
                    toActivity(intent5);
                    break;
                case R.id.liren://丽人
                    Intent intent6=new Intent(this,LinkFenleiDetailActivity.class);
                    intent6.putExtra("fenlei","丽人");
                    toActivity(intent6);
                    break;
                case  R.id.gouwu://购物
                    Intent intent7=new Intent(this,LinkFenleiDetailActivity.class);
                    intent7.putExtra("fenlei","购物");
                    toActivity(intent7);
                    break;
                case R.id.chuxing://出行
                    Intent intent8=new Intent(this,LinkFenleiDetailActivity.class);
                    intent8.putExtra("fenlei","出行");
                    toActivity(intent8);
                    break;
                case R.id.fuwu://服务
                    Intent intent9=new Intent(this,LinkFenleiDetailActivity.class);
                    intent9.putExtra("fenlei","生活服务");
                    toActivity(intent9);
                    break;
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }
    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{
            LinkListResult result=GsonUtils.getSingleBean(jsonStr, LinkListResult.class);
            if (result.isSuccess()){
                setData(result.data);
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {
        postDialog(Constant.SUPPLIER_LIST_INDEX,null,true);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_xinpai_link;
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
    }

    List<String>linkTopList=new ArrayList<String>();
    List<LinkListEntity>linkBottomList=new ArrayList<LinkListEntity>();
    public void setData(List<LinkListEntity> data) {
        for (LinkListEntity entity:data){
            if (entity.getShowSite().equals("1")){
                linkTopList.add(Constant.IMAGE_URL+entity.getPic());
            }else if (entity.getShowSite().equals("2")){
                linkBottomList.add(entity);
            }
        }
        // 轮播图
        mSlideShowView2.setImageUrls(linkTopList);
        //中间10 个小图标
        if (linkBottomList.size()<10){
            switch (linkBottomList.size()){
                case 0:

                    break;
                case 1:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_2, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_3, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_4, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_5, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_10, options);
                    break;
                case 2:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_3, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_4, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_5, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_10, options);
                    break;
                case 3:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_4, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_5, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_10, options);
                    break;
                case 4:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_5, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_10, options);
                    break;
                case 5:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_10, options);
                    break;
                case 6:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_10, options);
                    break;
                case 7:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_7, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_10, options);
                    break;
                case 8:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_7, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_8, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_10, options);
                    break;
                case 9:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_7, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_8, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(8).getPic(), iv_9, options);
//                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(8).getPic(), iv_10, options);
                    break;
                case 10:
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_7, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_8, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(8).getPic(), iv_9, options);
                    imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(9).getPic(), iv_10, options);
                    break;
            }
        }else {
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(0).getPic(), iv_1, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(1).getPic(), iv_2, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(2).getPic(), iv_3, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(3).getPic(), iv_4, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(4).getPic(), iv_5, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(5).getPic(), iv_6, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(6).getPic(), iv_7, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(7).getPic(), iv_8, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(8).getPic(), iv_9, options);
            imageLoader.displayImage(Constant.IMAGE_URL + linkBottomList.get(9).getPic(), iv_10, options);
        }
    }
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
}
