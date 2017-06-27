package com.gqh.mystudio.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.AboutActivity;
import com.gqh.mystudio.activity.MyContract;
import com.gqh.mystudio.activity.MyDongTaiActivity;
import com.gqh.mystudio.activity.MyGerenSetActivity;
import com.gqh.mystudio.activity.MyGroupMessageListActivity;
import com.gqh.mystudio.activity.MyMessageListActivity;
import com.gqh.mystudio.activity.ShangchengMydingdanActivity;
import com.gqh.mystudio.activity.XinpaiBiActivity;
import com.gqh.mystudio.activity.XinpaibiLishiZhangdanActivity;
import com.gqh.mystudio.activity.ZhuXiaoActivity;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.PersonEntity;
import com.gqh.mystudio.entity.PersonResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tarena.utils.ImageCircleView;


import cn.myapp.base.BaseHttpFragment;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: fragment 个人中心
 */

public class WoDeFangjianFragment extends BaseHttpFragment {

    @ViewInject(R.id.ll_background)
    private LinearLayout ll_background;
    @ViewInject(R.id.image_photo)
    private ImageCircleView image_photo;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_age)
    private TextView tv_age;
    private PersonEntity data;

    @OnClick({R.id.zhuxiao,R.id.image_photo,
            R.id.ll_dongtai,R.id.ll_xiaoxi,
            R.id.ll_qianbao,R.id.ll_gonggao,
            R.id.ll_zhangdan,R.id.ll_dingdan,
            R.id.ll_heyue,R.id.ll_shezhi,R.id.ll_about})
    private void onClick(View v){
        try{
            switch (v.getId()){
                case R.id.zhuxiao:
                    toActivity(ZhuXiaoActivity.class);
                    break;
                case R.id.image_photo:

                    break;
                case R.id.ll_dongtai:
                    toActivity(MyDongTaiActivity.class);
                    break;
                case R.id.ll_xiaoxi:
                    toActivity(MyMessageListActivity.class);
                    break;
                case R.id.ll_qianbao:
                    toActivity(XinpaiBiActivity.class);
                    break;
                case R.id.ll_gonggao:
                    toActivity(MyGroupMessageListActivity.class);
                    break;
                case R.id.ll_zhangdan:
                    toActivity(XinpaibiLishiZhangdanActivity.class);
                    break;
                case R.id.ll_dingdan:
                    toActivity(ShangchengMydingdanActivity.class);
                    break;
                case R.id.ll_heyue:
                    toActivity(MyContract.class);
                    break;
                case R.id.ll_shezhi:
                    toActivity(MyGerenSetActivity.class);
                    break;
                case R.id.ll_about:
                    toActivity(AboutActivity.class);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) throws Exception {
        try {
            if (requestCode==1){
                PersonResult result = GsonUtils.getSingleBean(jsonStr, PersonResult.class);
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

        //个人信息
        RequestParams params1=new RequestParams();
        params1.put("UID", BamsApplication.getInstance().getUser().getUID());
        postDialog(Constant.GET_SPEAK_USER_INFO,params1,1,true);
    }

    @Override
    public void onResume() {
        super.onResume();
        //个人信息
        RequestParams params1=new RequestParams();
        params1.put("UID", BamsApplication.getInstance().getUser().getUID());
        postDialog(Constant.GET_SPEAK_USER_INFO,params1,1,false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_wo_de_fangjian_fragment;
    }

    @Override
    protected void initView(View contentView) {

        ViewUtils.inject(this,contentView);
    }

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    public void setData(PersonEntity data) {
        this.data = data;

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
//				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
//				.cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//				.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .build();

        imageLoader.displayImage(Constant.IMAGE_URL + data.getUHeadPortrait(), image_photo, options);

        imageLoader.loadImage(Constant.IMAGE_URL + data.getUHeadPortrait(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);

                ll_background.setBackground(new BitmapDrawable(loadedImage));
                ll_background.getBackground().setAlpha(150);//0~255透明度值
            }
        });
        tv_name.setText(data.getUNickName());
        tv_age.setText(data.getUAge() + "岁");
    }
}
