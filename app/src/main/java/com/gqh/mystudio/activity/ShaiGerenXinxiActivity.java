package com.gqh.mystudio.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.ShaiDetailAdapter;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.ShaiDetailEntity;
import com.gqh.mystudio.entity.ShaiDetailResult;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tarena.utils.ImageCircleView;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class ShaiGerenXinxiActivity extends BaseHttpActivity {

    @ViewInject(R.id.sv)
    private ScrollView sv;
    @ViewInject(R.id.ll_background)
    private LinearLayout ll_background;
    @ViewInject(R.id.bt_qiaomen)
    private Button bt_qiaomen;
    @ViewInject(R.id.iv_photo)
    private ImageCircleView iv_photo;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_age)
    private TextView tv_age;
    @ViewInject(R.id.tv_xingzuo)
    private TextView tv_xingzuo;
    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;

    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_qianming)
    private TextView tv_qianming;
    @ViewInject(R.id.tv_jiaxiang)
    private TextView tv_jiaxiang;
    @ViewInject(R.id.tv_aihao)
    private TextView tv_aihao;
    @ViewInject(R.id.listview)
    private ListView listview;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private int fId;

    @OnClick({R.id.image_fanhui,R.id.bt_qiaomen})
    private void onClick(View v){
        try{

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
                case R.id.bt_qiaomen:
                    Intent intent=new Intent(this,QiaoMenTongxinActivity.class);
                    intent.putExtra("fId",fId);
                    toActivity(intent);
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shai_geren_xinxi;
    }

    @Override
    protected void initView() {

        ViewUtils.inject(this);
        sv.smoothScrollTo(0, 0);
        fId=getIntent().getIntExtra("UID", 0);
        int uId = BamsApplication.getInstance().getUser().getUID();

        if (uId==fId){
            bt_qiaomen.setVisibility(View.GONE);
        }
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

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {
        try{

            //晒详情
            MyLog.i("json++++",jsonStr);
            ShaiDetailResult result = GsonUtils.getSingleBean(jsonStr, ShaiDetailResult.class);
            if (result.isSuccess()){
                setDataShai(result.data);
            }

        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected void request() {

        if (fId!=0){
            //晒详情
            RequestParams params=new RequestParams();
            params.put("UID",fId);
            postDialog(Constant.SHAI_DETAIL, params);

        }

    }

    public void setDataShai(final ShaiDetailEntity dataShai) {

        tv_name.setText(dataShai.getUNickName());
        tv_address.setText(dataShai.getArea());
        tv_age.setText(dataShai.getUAge());
        tv_xingzuo.setText(dataShai.getUConstellation());


        if (dataShai.getUSex().equals("00_011_1")){
            iv_sex.setImageResource(R.drawable.sex_nan);
        }else {
            iv_sex.setImageResource(R.drawable.sex_nv);
        }
        tv_address.setText(dataShai.getArea());
        tv_qianming.setText(dataShai.getUSignaTure());
        tv_jiaxiang.setText(dataShai.getUHome());
        tv_aihao.setText(dataShai.getUHobby());
        imageLoader.displayImage(Constant.IMAGE_URL+dataShai.getUHeadPortrait(), iv_photo, options);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PictureShowActivity.class);
                intent.putExtra("picId", Constant.IMAGE_URL+dataShai.getUHeadPortrait());
                context.startActivity(intent);
            }
        });

        ShaiDetailAdapter adapter=new ShaiDetailAdapter(dataShai.getShow(),this);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShaiGerenXinxiActivity.this, ShaiDetailActivity.class);
                intent.putExtra("position", position);
                toActivity(intent);
            }
        });

        imageLoader.loadImage(Constant.IMAGE_URL+dataShai.getUHeadPortrait(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);

                ll_background.setBackground(new BitmapDrawable(loadedImage));
                ll_background.getBackground().setAlpha(150);//0~255透明度值
            }
        });
    }
}
