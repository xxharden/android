package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.QiaoMenTongxinActivity;
import com.gqh.mystudio.activity.ShaiGerenXinxiActivity;
import com.gqh.mystudio.entity.QiaoMoreEntity;
import com.gqh.mystudio.entity.QiaoPageListEntity;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.util.Constant;

public class viewpageAdapterQiao extends PagerAdapter {
    Context context;
    List<QiaoPageListEntity> dataQiao;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();


    public viewpageAdapterQiao(Context context, List<QiaoPageListEntity> dataQiao) {
        this.context = context;
        this.dataQiao = dataQiao;

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
        return dataQiao.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        QiaoPageListEntity list4Entity = dataQiao.get(position);
        final List<QiaoMoreEntity> list = list4Entity.getData();
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_qiaoliebiao, null);

        LinearLayout ll_1 = (LinearLayout) convertView.findViewById(R.id.ll_1);
        final LinearLayout ll_2 = (LinearLayout) convertView.findViewById(R.id.ll_2);
        LinearLayout ll_3 = (LinearLayout) convertView.findViewById(R.id.ll_3);
        LinearLayout ll_4 = (LinearLayout) convertView.findViewById(R.id.ll_4);

        final ImageCircleView  iv_photo_1= (ImageCircleView) convertView.findViewById(R.id.iv_photo_1);
        ImageCircleView  iv_photo_2= (ImageCircleView) convertView.findViewById(R.id.iv_photo_2);
        ImageCircleView  iv_photo_3= (ImageCircleView) convertView.findViewById(R.id.iv_photo_3);
        final ImageCircleView iv_photo_4 = (ImageCircleView) convertView.findViewById(R.id.iv_photo_4);

        TextView tv_name_1 = (TextView) convertView.findViewById(R.id.tv_name_1);
        TextView  tv_name_2= (TextView) convertView.findViewById(R.id.tv_name_2);
        final TextView  tv_name_3= (TextView) convertView.findViewById(R.id.tv_name_3);
        final TextView  tv_name_4= (TextView) convertView.findViewById(R.id.tv_name_4);

        final TextView tv_qianming_1 = (TextView) convertView.findViewById(R.id.tv_qianming_1);
        final TextView  tv_qianming_2= (TextView) convertView.findViewById(R.id.tv_qianming_2);
        TextView  tv_qianming_3= (TextView) convertView.findViewById(R.id.tv_qianming_3);
        TextView  tv_qianming_4= (TextView) convertView.findViewById(R.id.tv_qianming_4);

        ImageView iv_xingbie_1 = (ImageView) convertView.findViewById(R.id.iv_xingbie_1);
        ImageView  iv_xingbie_2= (ImageView) convertView.findViewById(R.id.iv_xingbie_2);
        ImageView  iv_xingbie_3= (ImageView) convertView.findViewById(R.id.iv_xingbie_3);
        ImageView  iv_xingbie_4= (ImageView) convertView.findViewById(R.id.iv_xingbie_4);

        Button bt_qiaomen_1 = (Button) convertView.findViewById(R.id.bt_qiaomen_1);
        Button  bt_qiaomen_2= (Button) convertView.findViewById(R.id.bt_qiaomen_2);
        Button  bt_qiaomen_3= (Button) convertView.findViewById(R.id.bt_qiaomen_3);
        Button  bt_qiaomen_4= (Button) convertView.findViewById(R.id.bt_qiaomen_4);


        if (list.size()>0){
            ll_1.setVisibility(View.VISIBLE);
            tv_name_1.setText(list.get(0).getUNickName());
            tv_qianming_1.setText(list.get(0).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL + list.get(0).getUHeadPortrait(),iv_photo_1, options);
            if (list.get(0).getUSex().equals("00_011_1")){
                iv_xingbie_1.setImageResource(R.drawable.sex_nan);
            }else {
                iv_xingbie_1.setImageResource(R.drawable.sex_nv);
            }

            bt_qiaomen_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(0).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);
                }
            });
            ll_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(0).getUID();
                    Intent intent=new Intent(context,ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID",uId);
                    context.startActivity(intent);
                }
            });
        }

        if (list.size()>1){
            if (list.get(1).getUSex().equals("00_011_1")){
                iv_xingbie_2.setImageResource(R.drawable.sex_nan);
            }else {
                iv_xingbie_2.setImageResource(R.drawable.sex_nv);
            }
            ll_2.setVisibility(View.VISIBLE);
            tv_name_2.setText(list.get(1).getUNickName());
            tv_qianming_2.setText(list.get(1).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL+list.get(1).getUHeadPortrait(),iv_photo_2, options);
            bt_qiaomen_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(1).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);

                }
            });

            ll_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(1).getUID();
                    Intent intent=new Intent(context,ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID",uId);
                    context.startActivity(intent);
                }
            });
        }


        if(list.size()>2){
            if (list.get(2).getUSex().equals("00_011_1")){
                iv_xingbie_3.setImageResource(R.drawable.sex_nan);
            }else {
                iv_xingbie_3.setImageResource(R.drawable.sex_nv);
            }
            ll_3.setVisibility(View.VISIBLE);
            tv_name_3.setText(list.get(2).getUNickName());
            tv_qianming_3.setText(list.get(2).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL+list.get(2).getUHeadPortrait(),iv_photo_3, options);
            bt_qiaomen_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(2).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);

                }
            });
            ll_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(2).getUID();
                    Intent intent=new Intent(context,ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID",uId);
                    context.startActivity(intent);
                }
            });
        }

        if (list.size()>3) {
            if (list.get(3).getUSex().equals("00_011_1")) {
                iv_xingbie_4.setImageResource(R.drawable.sex_nan);
            } else {
                iv_xingbie_4.setImageResource(R.drawable.sex_nv);
            }
            ll_4.setVisibility(View.VISIBLE);
            tv_name_4.setText(list.get(3).getUNickName());
            tv_qianming_4.setText(list.get(3).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL + list.get(3).getUHeadPortrait(), iv_photo_4, options);
            bt_qiaomen_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(3).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);

                }
            });
            ll_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(3).getUID();
                    Intent intent = new Intent(context, ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID", uId);
                    context.startActivity(intent);
                }
            });
        }
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
} 
