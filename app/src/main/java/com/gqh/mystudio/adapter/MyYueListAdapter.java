package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.YueMyYueActivity;
import com.gqh.mystudio.entity.MyYueEntity;
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
 * @date : 2016/3/16
 * desc:
 */
public class MyYueListAdapter extends MyBaseAdapter<MyYueEntity> {

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    String Type;
    public MyYueListAdapter(List<MyYueEntity> mList, Context context,String Type) {
        super(mList, context);
        this.Type=Type;
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

        final MyYueEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_my_huodong_listview,null);
            holder.image_huodong= (ImageView) convertView.findViewById(R.id.image_huodong);
            holder.name_huodong= (TextView) convertView.findViewById(R.id.name_huodong);
            holder.iv_photo= (ImageCircleView) convertView.findViewById(R.id.iv_photo);
//            holder.sex_lv_image_1= (ImageView) convertView.findViewById(R.id.sex_lv_image_1);
            holder.name_faqiren= (TextView) convertView.findViewById(R.id.name_faqiren);
            holder.tv_huodong_style= (TextView) convertView.findViewById(R.id.tv_huodong_style);
            holder.renshu_huodong= (TextView) convertView.findViewById(R.id.renshu_huodong);
            holder.time_huodong= (TextView) convertView.findViewById(R.id.time_huodong);
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            holder.dengdai_kaishi= (TextView) convertView.findViewById(R.id.dengdai_kaishi);
//            holder.bt_pingjia= (Button) convertView.findViewById(R.id.bt_pingjia);
            holder.bt_jieshou= (Button) convertView.findViewById(R.id.bt_jieshou);
            holder.huodong_jieshu= (TextView) convertView.findViewById(R.id.huodong_jieshu);
            holder.bt_hulue= (Button) convertView.findViewById(R.id.bt_hulue);
            convertView.setTag(holder);
        }else {

            holder= (ViewHolder) convertView.getTag();
        }

        if (entity.getPicID().indexOf(";")==-1){
            imageLoader.displayImage(Constant.IMAGE_URL+entity.getPicID(),holder.image_huodong,options);
        }else {
            String picStr = entity.getPicID().substring(0, entity.getPicID().indexOf(";"));
            MyLog.i("pic=", picStr);
            imageLoader.displayImage(Constant.IMAGE_URL+picStr,holder.image_huodong,options);
        }

        holder.name_huodong.setText(entity.getName());
        imageLoader.displayImage(Constant.IMAGE_URL + entity.getUHeadPortrait(), holder.iv_photo, options);
//        holder.sex_lv_image_1
        holder.name_faqiren.setText(entity.getAdmin());
//        活动类型， 0-受邀请，1-等待开始，2-已结束
        if (Type.equals("0")){
            holder.tv_huodong_style.setText("邀请您参加");
            holder.bt_hulue.setVisibility(View.VISIBLE);
            holder.bt_jieshou.setVisibility(View.VISIBLE);

            holder.bt_jieshou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    0表示拒绝，1表示接受
                    ((YueMyYueActivity) context).sendRequest(entity.getGroupID(), "1");
                }
            });
            holder.bt_hulue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((YueMyYueActivity) context).sendRequest(entity.getGroupID(), "0");
                }
            });
        }else if (Type.equals("1")){
            holder.tv_huodong_style.setText("发起了活动");
            holder.dengdai_kaishi.setVisibility(View.VISIBLE);
        } else if (Type.equals("2")){
            holder.tv_huodong_style.setText("发起了活动");
//            holder.bt_pingjia.setVisibility(View.VISIBLE);
            holder.huodong_jieshu.setVisibility(View.VISIBLE);
        }




        holder.renshu_huodong.setText("已参加 "+entity.getPersonCount()+" 人");
        holder.time_huodong.setText(entity.getBeginDate());
        holder.tv_address.setText("集合地点  "+entity.getArea());
//        holder.dengdai_kaishi
//        holder.bt_pingjia
//        holder.bt_jieshou
//        holder.huodong_jieshu
//        holder.bt_hulue

        return convertView;
    }

    private static class ViewHolder{
        ImageView image_huodong;
        TextView name_huodong;
        ImageCircleView iv_photo;
//        ImageView sex_lv_image_1;
        TextView name_faqiren;
        TextView tv_huodong_style;
        TextView renshu_huodong;
        TextView time_huodong;
        TextView tv_address;
        TextView dengdai_kaishi;
//        Button bt_pingjia;
        Button bt_jieshou;
        TextView huodong_jieshu;
        Button bt_hulue;

    }
}
