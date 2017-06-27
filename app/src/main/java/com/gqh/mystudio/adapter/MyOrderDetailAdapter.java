package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.MyOrderProductEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/20
 * desc:
 */
public class MyOrderDetailAdapter extends MyBaseAdapter<MyOrderProductEntity> {

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();

    public MyOrderDetailAdapter(List<MyOrderProductEntity> mList, Context context) {
        super(mList, context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyOrderProductEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_listview_myorder_detail,null);
            holder.iv_photo= (ImageView) convertView.findViewById(R.id.iv_photo);
            holder.tv_price= (TextView) convertView.findViewById(R.id.tv_jiage);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_unit= (TextView) convertView.findViewById(R.id.tv_danwei);
            holder.tv_number= (TextView) convertView.findViewById(R.id.tv_number);
            holder.tv_totalfee= (TextView) convertView.findViewById(R.id.tv_zongjia);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        imageLoader.displayImage(Constant.IMAGE_URL+entity.getPicPath(),holder.iv_photo,options);
        holder.tv_name.setText(entity.getProName());
        holder.tv_price.setText(entity.getRealPrice()+"");
        holder.tv_unit.setText(entity.getUnit());
        holder.tv_number.setText(entity.getNumber()+"");
        holder.tv_totalfee.setText((entity.getNumber())*(entity.getRealPrice())+"");

        return convertView;
    }

    private static class ViewHolder{
        ImageView iv_photo;
        TextView tv_price;
        TextView tv_name;
        TextView tv_unit;
        TextView tv_number;
        TextView tv_totalfee;

    }
}
