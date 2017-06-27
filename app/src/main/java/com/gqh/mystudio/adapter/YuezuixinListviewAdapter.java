package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.YueJiaruHongdongOkActivity;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc:
 */
public class YuezuixinListviewAdapter extends MyBaseAdapter<YueMoreEntity> {

    private Context context;
    private List<YueMoreEntity>mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;;

    public YuezuixinListviewAdapter(Context context, List<YueMoreEntity> mList) {
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

        YueMoreEntity entity = mList.get(position);
        HoldView holder=null;
        if(convertView==null){
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_listview_yue_shouye,null);

//            // 获取屏幕宽高（方法）
//            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            int screenWidth = windowManager.getDefaultDisplay().getWidth();
//            int screenHeight = windowManager.getDefaultDisplay().getHeight();
//            //设置宽度
//            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) convertView.getLayoutParams();
//            // 取控件aaa当前的布局参数
//            linearParams.height =screenWidth; // 当控件的高强制设成屏幕宽度
//            convertView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件

            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_faqiren= (TextView) convertView.findViewById(R.id.tv_faqiren);
            holder.yibaoming= (TextView) convertView.findViewById(R.id.yibaoming);

            holder.bt_jiaru= (Button) convertView.findViewById(R.id.bt_jiaru);
            holder.iv_photo= (ImageView) convertView.findViewById(R.id.iv_photo);

            convertView.setTag(holder);

        }else{

            holder= (HoldView) convertView.getTag();
        }



        holder.tv_name.setText(entity.getName());
        holder.tv_address.setText(entity.getArea());
        holder.tv_time.setText(entity.getBeginDate());
        holder.tv_faqiren.setText("发起人："+entity.getAdmin());
        holder.yibaoming.setText("已报名：" + entity.getPersonNum());
        MyLog.i("picid=", entity.getPicID());
        if (entity.getPicID().indexOf(";")==-1){
            imageLoader.displayImage(Constant.IMAGE_URL+entity.getPicID(),holder.iv_photo,options);
        }else {
            String picStr = entity.getPicID().substring(0, entity.getPicID().indexOf(";"));
            MyLog.i("pic=",picStr);
            imageLoader.displayImage(Constant.IMAGE_URL+picStr,holder.iv_photo,options);
        }

       /* holder.bt_jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //加入活动
                Intent intent=new Intent(context,YueJiaruHongdongOkActivity.class);
                context.startActivity(intent);
            }
        });*/
        return convertView;
    }

    private static class HoldView{
        ImageView iv_photo;
        TextView tv_name;
        TextView tv_address;
        TextView tv_time;
        TextView tv_faqiren;
        TextView yibaoming;
        Button bt_jiaru;
    }
}
