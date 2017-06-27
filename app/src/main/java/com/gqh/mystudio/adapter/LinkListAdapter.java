package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.LinkEntity;
import com.gqh.mystudio.entity.LinkListEntity;
import com.gqh.mystudio.entity.Shops;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/15
 * desc:
 */
public class LinkListAdapter extends MyBaseAdapter<Shops> {

    private Context context;
    private List<Shops> mList;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    public LinkListAdapter(List<Shops> mList, Context context) {
        super(mList, context);
        this.context=context;
        this.mList=mList;
        mInflater=LayoutInflater.from(context);

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
        HoldView holder = null;
        Shops entity = mList.get(position);
        if (convertView==null){
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_link_fenlei_detail, null);
            holder.image_photo_fenlei_detail= (ImageView) convertView.findViewById(R.id.image_photo_fenlei_detail);
            holder.tv_name_fenlei_detail= (TextView) convertView.findViewById(R.id.tv_name_fenlei_detail);
            holder.tv_jieshao_fenlei_detail= (TextView) convertView.findViewById(R.id.tv_jieshao_fenlei_detail);
            holder.tv_phone= (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(holder);
        }else {
            holder= (HoldView) convertView.getTag();
        }

        holder.tv_name_fenlei_detail.setText(entity.getShop_name());
        holder.tv_jieshao_fenlei_detail.setText(entity.getDeals().get(0).getDescription());
        holder.tv_phone.setText("已售出："+entity.getDeals().get(0).getSale_num()+"");
        imageLoader.displayImage(entity.getDeals().get(0).getImage(),holder.image_photo_fenlei_detail,options);
        return convertView;
    }

    private static class HoldView{
        ImageView image_photo_fenlei_detail;
        TextView tv_name_fenlei_detail;
        TextView tv_jieshao_fenlei_detail;
        TextView tv_phone;
    }
}
