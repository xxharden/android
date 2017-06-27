package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
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
 * @date : 2016/3/4
 * desc: 晒 公民动态和晒最新adapter
 */
public class ShaiGongminDongtaiListviewApapter extends MyBaseAdapter<ShaiDongtaiEntity> {


    private Context context;
    private List<ShaiDongtaiEntity>mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;

    public ShaiGongminDongtaiListviewApapter(Context context,List<ShaiDongtaiEntity>mList) {
        super(mList,context);

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
    public View getView(int position, View convertView, ViewGroup parent) {

        ShaiDongtaiEntity entity = mList.get(position);
        HoldView holder = null;
        if (convertView == null) {
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_listview_shai_gongmin_dongtaiandshaizuixin,null);

//            // 获取屏幕宽高（方法）
//            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            int screenWidth = windowManager.getDefaultDisplay().getWidth();
//            int screenHeight = windowManager.getDefaultDisplay().getHeight();
//            //设置宽度
//            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) convertView.getLayoutParams();
//            // 取控件aaa当前的布局参数
//            linearParams.height =screenWidth; // 当控件的高强制设成屏幕宽度
//            convertView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件aaa

            holder.image_photo= (ImageCircleView) convertView.findViewById(R.id.image_photo);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_qiaomen=(TextView)convertView.findViewById(R.id.tv_qiaomen);
            holder.tv_beiqiao=(TextView)convertView.findViewById(R.id.tv_beiqiao);
            holder.tv_qianming=(TextView)convertView.findViewById(R.id.tv_qianming);

            convertView.setTag(holder);
        }else {

            holder=(HoldView)convertView.getTag();
        }


        holder.tv_name.setText(entity.getUNickName());
        holder.tv_qiaomen.setText("敲门   "+entity.getKnockNum()+"");
        holder.tv_beiqiao.setText("被敲门  "+entity.getKnockedNum()+"");
        holder.tv_qianming.setText(entity.getUSignaTure());


        /**
         * 显示图片
         * 参数1：图片url
         * 参数2：显示图片的控件
         * 参数3：显示图片的设置
         * 参数4：监听器
         */
            imageLoader.displayImage(Constant.IMAGE_URL+entity.getUHeadPortrait(), holder.image_photo, options);

        return convertView;
    }

    private static class HoldView{
        ImageCircleView image_photo;
        TextView tv_name;
        TextView tv_qiaomen;
        TextView tv_beiqiao;
        TextView tv_qianming;
    }
}
