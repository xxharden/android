package com.gqh.mystudio.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.adapter.FabuPhotoGrideviewAdapter;
import com.gqh.mystudio.adapter.GrideviewAdapter;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.gqh.mystudio.entity.ShaiDongtaiResult;
import com.gqh.mystudio.utill.BitmapUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.myapp.base.BaseHttpActivity;
import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.GsonUtils;
import cn.myapp.util.MyLog;
import http.RequestParams;

public class ShaiDetailActivity extends BaseHttpActivity {

    @ViewInject(R.id.iv_photo)
    private ImageCircleView iv_photo;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_dongtai)
    private TextView tv_dongtai;
    @ViewInject(R.id.gridview)
    private GridView gridview;
    private int position;
    private int apartmentID;
    private List<ShaiDongtaiEntity> data;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();

    @OnClick({R.id.image_fanhui})
    private void onClick(View v){
        try{

            switch (v.getId()){
                case R.id.image_fanhui:
                    finish();
                    break;
            }
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shai_detail;
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this);

        position=getIntent().getIntExtra("position",0);
        apartmentID=getIntent().getIntExtra("ApartmentID",0);
    }

    @Override
    protected void onSuccess(int requestCode, String jsonStr) {

        try{

            ShaiDongtaiResult result = GsonUtils.getSingleBean(jsonStr, ShaiDongtaiResult.class);
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
        if(apartmentID!=0){
            params.put("ApartmentID",apartmentID);
            postDialog(Constant.SHAI_LINJU_DONGTAI,params);
        }else {
            postDialog(Constant.SHAI_GONGMIN_DONGTAI,null);
        }



    }

    public void setData(List<ShaiDongtaiEntity> data) {

        this.data = data;

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

        final ShaiDongtaiEntity entity = data.get(position);

        tv_name.setText(entity.getUNickName());
        tv_address.setText(entity.getArea());
        tv_dongtai.setText(entity.getShowCont());
        imageLoader.displayImage(Constant.IMAGE_URL + entity.getUHeadPortrait(), iv_photo, options);

        String picId = entity.getShowPic();
        MyLog.i("picId=", picId.toString());
        final List<String> List=new ArrayList<String>();
        if (picId.indexOf(";")==-1){
            List.add(Constant.IMAGE_URL+picId);
        }else {
            String[] list = picId.split(";");
            for (int i=0;i<list.length;i++){
                List.add(Constant.IMAGE_URL+list[i]);
            }
        }
        MyLog.i("mList=", List.toString());
        GrideviewAdapter gvAdapter=new GrideviewAdapter(List,this,Constant.STYLE_ADAPTER_OK);
        gridview.setAdapter(gvAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PictureShowActivity.class);
                intent.putExtra("picId", List.get(position));
                context.startActivity(intent);
            }
        });
    }
}
