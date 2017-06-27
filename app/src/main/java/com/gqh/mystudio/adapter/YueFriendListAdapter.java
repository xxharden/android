package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.YueYaoQingHaoyouActivity;
import com.gqh.mystudio.entity.YueFriendEntity;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/3/11
 * desc:
 */
public class YueFriendListAdapter  extends MyBaseAdapter<YueFriendEntity> {

    private Context context;
    private List<YueFriendEntity> mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;
    public YueFriendListAdapter(List<YueFriendEntity> mList, Context context) {
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
        final YueFriendEntity entity = mList.get(position);
        HoldView holder=null;
        if (convertView==null) {
            holder = new HoldView();
            convertView=mInflater.inflate(R.layout.item_lisview_haoyou_yaoqing,null);
            holder.iv_photo= (ImageCircleView) convertView.findViewById(R.id.iv_photo);
//            holder.iv_sex= (ImageView) convertView.findViewById(R.id.iv_sex);
            holder.name_lv_tv_1= (TextView) convertView.findViewById(R.id.name_lv_tv_1);
            holder.age_lv_tv_1= (TextView) convertView.findViewById(R.id.age_lv_tv_1);
            holder.tv_xingzuo= (TextView) convertView.findViewById(R.id.tv_xingzuo);
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            holder.bt_yaoqing= (Button) convertView.findViewById(R.id.bt_yaoqing);
            holder.bt_yicanjia= (Button) convertView.findViewById(R.id.bt_yicanjia);
            convertView.setTag(holder);
        }else{

            holder= (HoldView) convertView.getTag();
        }

        imageLoader.displayImage(Constant.IMAGE_URL+entity.getUHeadPortrait(),holder.iv_photo,options);
//        if (entity.getUSex().equals("00_011_1")){
//            holder.iv_sex.setImageResource(R.drawable.sex_nan);
//        }else {
//            holder.iv_sex.setImageResource(R.drawable.sex_nv);
//        }
        holder.name_lv_tv_1.setText(entity.getUNickName());
        holder.age_lv_tv_1.setText(entity.getUAge()+"岁");
        holder.tv_xingzuo.setText(entity.getUConstellation());
        holder.tv_address.setText(entity.getArea());

        if (entity.getState()==0){
            holder.bt_yaoqing.setVisibility(View.VISIBLE);
            holder.bt_yaoqing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //回调函数 发送邀请请求
                    ((YueYaoQingHaoyouActivity)context).sendPostRequest(entity.getUID());

                }
            });
        }else {
            holder.bt_yicanjia.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    private static class HoldView{

        ImageCircleView iv_photo;
//        ImageView iv_sex;
        TextView name_lv_tv_1;
        TextView age_lv_tv_1;
        TextView tv_xingzuo;
        TextView tv_address;
        Button bt_yaoqing;
        Button bt_yicanjia;
    }
}
