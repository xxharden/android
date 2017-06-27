package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.ShaiGerenXinxiActivity;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.util.Constant;

public class viewpageAdapterShai extends PagerAdapter {
    Context context;
    List<ShaiDongtaiEntity> dataShai;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();


    public viewpageAdapterShai(Context context, List<ShaiDongtaiEntity> dataShai) {
        this.context = context;
        this.dataShai = dataShai;

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
    public int getCount() {
        return dataShai.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ShaiDongtaiEntity entity = dataShai.get(position);
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_shai_gongmin_dongtaiandshaizuixin, null);
        ImageCircleView image_photo = (ImageCircleView) convertView.findViewById(R.id.image_photo);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_qiaomen = (TextView) convertView.findViewById(R.id.tv_qiaomen);
        TextView tv_beiqiao = (TextView) convertView.findViewById(R.id.tv_beiqiao);
        TextView tv_qianming = (TextView) convertView.findViewById(R.id.tv_qianming);
        tv_name.setText(entity.getUNickName());
        tv_qiaomen.setText("敲门   " + entity.getKnockNum() + "");
        tv_beiqiao.setText("被敲门  " + entity.getKnockedNum() + "");
        tv_qianming.setText(entity.getUSignaTure());
        imageLoader.displayImage(Constant.IMAGE_URL + entity.getUHeadPortrait(), image_photo, options);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uId = dataShai.get(position).getUID();
                Intent intent = new Intent(context, ShaiGerenXinxiActivity.class);
                intent.putExtra("UID", uId);
                context.startActivity(intent);
            }
        });
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
} 
