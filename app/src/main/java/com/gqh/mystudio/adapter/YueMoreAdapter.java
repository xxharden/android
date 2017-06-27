package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.QiaoMoreEntity;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;

/**
 * @author : 红仔
 * @date : 2016/3/5
 * desc:
 */
public class YueMoreAdapter  extends MyBaseAdapter<YueMoreEntity> {

    private Context context;
    private List<YueMoreEntity> mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;

    public YueMoreAdapter(List<YueMoreEntity> mList, Context context) {
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

        HoldView holder=null;
        if (convertView==null){
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_yue_more_huodong,null);
            holder.iv_photo= (ImageView) convertView.findViewById(R.id.iv_photo);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_nums= (TextView) convertView.findViewById(R.id.tv_nums);
            holder.tv_jieshao= (TextView) convertView.findViewById(R.id.tv_jieshao);
            convertView.setTag(holder);

        }else {

            holder= (HoldView) convertView.getTag();
        }

        YueMoreEntity entity = mList.get(position);
        holder.tv_name.setText(entity.getName());
        holder.tv_time.setText(entity.getBeginDate());
        holder.tv_address.setText(entity.getArea());
        holder.tv_nums.setText("参加人数："+entity.getPersonNum());
        holder.tv_jieshao.setText("活动介绍："+entity.getIntro());

        if (entity.getPicID().indexOf(";")==-1){
            imageLoader.displayImage(Constant.IMAGE_URL+entity.getPicID(),holder.iv_photo,options);
        }else {
            String picStr = entity.getPicID().substring(0, entity.getPicID().indexOf(";"));
            MyLog.i("pic=", picStr);
            imageLoader.displayImage(Constant.IMAGE_URL+picStr,holder.iv_photo,options);
        }


        return convertView;
    }

    private static class HoldView{

        ImageView iv_photo;
        TextView tv_name;
        TextView tv_address;
        TextView tv_time;
        TextView tv_nums;
        TextView tv_jieshao;

    }
}
