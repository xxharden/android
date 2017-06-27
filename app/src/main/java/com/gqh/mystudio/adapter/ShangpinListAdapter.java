package com.gqh.mystudio.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.QiaoMyQiaoActivity;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.fragment.XinPaiOnlineFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/18
 * desc:
 */
public class ShangpinListAdapter extends BaseAdapter {

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private List<ShangpinListEntity> mList;
    private LayoutInflater mInflater;
    private XinPaiOnlineFragment context;
    public ShangpinListAdapter(List<ShangpinListEntity> mList, XinPaiOnlineFragment context) {
        super();
        this.context=context;
        this.mList=mList;
        mInflater=LayoutInflater.from(context.getActivity());
        imageLoader.init(ImageLoaderConfiguration.createDefault(context.getActivity()));
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
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ShangpinListEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_gridview_shangcheng,null);
            holder.image_photo= (ImageView) convertView.findViewById(R.id.image_photo);
            holder.image_hot= (ImageView) convertView.findViewById(R.id.image_hot);
            holder.iv_add= (ImageView) convertView.findViewById(R.id.iv_add);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_jiage= (TextView) convertView.findViewById(R.id.tv_jiage);
            holder.tv_danwei= (TextView) convertView.findViewById(R.id.tv_danwei);
            holder.tv_yuanjia= (TextView) convertView.findViewById(R.id.tv_yuanjia);
            convertView.setTag(holder);
        }else {

            holder= (ViewHolder) convertView.getTag();
        }

        imageLoader.displayImage(Constant.IMAGE_URL + entity.getPicPath(), holder.image_photo, options);
        if (position==0|position==1|position==2){
            holder.image_hot.setVisibility(View.VISIBLE);
        }else {
            holder.image_hot.setVisibility(View.GONE);
        }

        holder.tv_name.setText(entity.getProName());
        holder.tv_jiage.setText(entity.getRealPrice()+"");
        holder.tv_danwei.setText(entity.getUnit());
        holder.tv_yuanjia.setText(entity.getPrice() + entity.getUnit());
        holder.tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线


        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.addPic(entity);
            }
        });
        return convertView;
    }

    private static class ViewHolder{

        ImageView image_photo;
        ImageView image_hot;
        ImageView iv_add;
        TextView tv_name;
        TextView tv_jiage;
        TextView tv_danwei;
        TextView tv_yuanjia;
    }
}
