package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/10
 * desc:
 */
public class ShouyeQiaoAdapter extends MyBaseAdapter<QiaoPageListEntity> {

    private Context context;
    private List<QiaoPageListEntity>mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;
    public ShouyeQiaoAdapter(List<QiaoPageListEntity> mList, Context context) {
        super(mList, context);
        mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mList=mList;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        QiaoPageListEntity list4Entity = mList.get(position);
        final List<QiaoMoreEntity> list = list4Entity.getData();
        HoldView holder = null;
        if (convertView == null) {
            holder = new HoldView();
            convertView = mInflater.inflate(R.layout.item_listview_qiaoliebiao, null);

            holder.ll_1= (LinearLayout) convertView.findViewById(R.id.ll_1);
            holder.ll_2= (LinearLayout) convertView.findViewById(R.id.ll_2);
            holder.ll_3= (LinearLayout) convertView.findViewById(R.id.ll_3);
            holder.ll_4= (LinearLayout) convertView.findViewById(R.id.ll_4);

            holder.iv_photo_1= (ImageCircleView) convertView.findViewById(R.id.iv_photo_1);
            holder.iv_photo_2= (ImageCircleView) convertView.findViewById(R.id.iv_photo_2);
            holder.iv_photo_3= (ImageCircleView) convertView.findViewById(R.id.iv_photo_3);
            holder.iv_photo_4= (ImageCircleView) convertView.findViewById(R.id.iv_photo_4);

            holder.tv_name_1= (TextView) convertView.findViewById(R.id.tv_name_1);
            holder.tv_name_2= (TextView) convertView.findViewById(R.id.tv_name_2);
            holder.tv_name_3= (TextView) convertView.findViewById(R.id.tv_name_3);
            holder.tv_name_4= (TextView) convertView.findViewById(R.id.tv_name_4);

            holder.tv_qianming_1= (TextView) convertView.findViewById(R.id.tv_qianming_1);
            holder.tv_qianming_2= (TextView) convertView.findViewById(R.id.tv_qianming_2);
            holder.tv_qianming_3= (TextView) convertView.findViewById(R.id.tv_qianming_3);
            holder.tv_qianming_4= (TextView) convertView.findViewById(R.id.tv_qianming_4);

            holder.iv_xingbie_1= (ImageView) convertView.findViewById(R.id.iv_xingbie_1);
            holder.iv_xingbie_2= (ImageView) convertView.findViewById(R.id.iv_xingbie_2);
            holder.iv_xingbie_3= (ImageView) convertView.findViewById(R.id.iv_xingbie_3);
            holder.iv_xingbie_4= (ImageView) convertView.findViewById(R.id.iv_xingbie_4);

            holder.bt_qiaomen_1= (Button) convertView.findViewById(R.id.bt_qiaomen_1);
            holder.bt_qiaomen_2= (Button) convertView.findViewById(R.id.bt_qiaomen_2);
            holder.bt_qiaomen_3= (Button) convertView.findViewById(R.id.bt_qiaomen_3);
            holder.bt_qiaomen_4= (Button) convertView.findViewById(R.id.bt_qiaomen_4);

            convertView.setTag(holder);
        }else {

            holder=(HoldView)convertView.getTag();
        }

        if (list.size()>0){
            holder.ll_1.setVisibility(View.VISIBLE);
            holder.tv_name_1.setText(list.get(0).getUNickName());
            holder.tv_qianming_1.setText(list.get(0).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL + list.get(0).getUHeadPortrait(), holder.iv_photo_1, options);
            if (list.get(0).getUSex().equals("00_011_1")){
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nan);
            }else {
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nv);
            }

            holder.bt_qiaomen_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(0).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);
                }
            });
            holder.ll_1.setOnClickListener(new View.OnClickListener() {
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
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nan);
            }else {
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nv);
            }
            holder.ll_2.setVisibility(View.VISIBLE);
            holder.tv_name_2.setText(list.get(1).getUNickName());
            holder.tv_qianming_2.setText(list.get(1).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL+list.get(1).getUHeadPortrait(), holder.iv_photo_2, options);
            holder.bt_qiaomen_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(1).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);

                }
            });

            holder.ll_2.setOnClickListener(new View.OnClickListener() {
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
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nan);
            }else {
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nv);
            }
            holder.ll_3.setVisibility(View.VISIBLE);
            holder.tv_name_3.setText(list.get(2).getUNickName());
            holder.tv_qianming_3.setText(list.get(2).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL+list.get(2).getUHeadPortrait(), holder.iv_photo_3, options);
            holder.bt_qiaomen_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(2).getUID();
                    Intent intent = new Intent(context, QiaoMenTongxinActivity.class);
                    intent.putExtra("fId", uId);
                    context.startActivity(intent);

                }
            });
            holder.ll_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(2).getUID();
                    Intent intent=new Intent(context,ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID",uId);
                    context.startActivity(intent);
                }
            });
        }

        if (list.size()>3){
            if (list.get(3).getUSex().equals("00_011_1")){
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nan);
            }else {
                holder.iv_xingbie_1.setImageResource(R.drawable.sex_nv);
            }
            holder.ll_4.setVisibility(View.VISIBLE);
            holder.tv_name_4.setText(list.get(3).getUNickName());
            holder.tv_qianming_4.setText(list.get(3).getUSignaTure());
            imageLoader.displayImage(Constant.IMAGE_URL+list.get(3).getUHeadPortrait(), holder.iv_photo_4, options);
            holder.bt_qiaomen_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(3).getUID();
                    Intent intent=new Intent(context,QiaoMenTongxinActivity.class);
                    intent.putExtra("fId",uId);
                    context.startActivity(intent);

                }
            });
            holder.ll_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int uId = list.get(3).getUID();
                    Intent intent=new Intent(context,ShaiGerenXinxiActivity.class);
                    intent.putExtra("UID",uId);
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }

    private static class HoldView{
        LinearLayout ll_1;
        LinearLayout ll_2;
        LinearLayout ll_3;
        LinearLayout ll_4;

        ImageCircleView iv_photo_1;
        ImageCircleView iv_photo_2;
        ImageCircleView iv_photo_3;
        ImageCircleView iv_photo_4;
        TextView tv_name_1;
        TextView tv_name_2;
        TextView tv_name_3;
        TextView tv_name_4;
        TextView tv_qianming_1;
        TextView tv_qianming_2;
        TextView tv_qianming_3;
        TextView tv_qianming_4;
        ImageView iv_xingbie_1;
        ImageView iv_xingbie_2;
        ImageView iv_xingbie_3;
        ImageView iv_xingbie_4;
        Button bt_qiaomen_1;
        Button bt_qiaomen_2;
        Button bt_qiaomen_3;
        Button bt_qiaomen_4;
    }
}
