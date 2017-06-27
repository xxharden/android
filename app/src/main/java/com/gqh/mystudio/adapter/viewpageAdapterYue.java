package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.ShaiGerenXinxiActivity;
import com.gqh.mystudio.activity.YueHuodongDetailActivity;
import com.gqh.mystudio.activity.YueJiaruHongdongOkActivity;
import com.gqh.mystudio.entity.ShaiDongtaiEntity;
import com.gqh.mystudio.entity.YueMoreEntity;
import com.gqh.mystudio.fragment.ZuoLinYouSheFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;

public class viewpageAdapterYue extends PagerAdapter {
    ZuoLinYouSheFragment context;
    List<YueMoreEntity> dataYue;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();


    public viewpageAdapterYue(ZuoLinYouSheFragment context, List<YueMoreEntity> dataYue) {
        this.context = context;
        this.dataYue = dataYue;

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
        return dataYue.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final YueMoreEntity entity = dataYue.get(position);
        View convertView = LayoutInflater.from(context.getActivity()).inflate(R.layout.item_listview_yue_shouye, null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_faqiren = (TextView) convertView.findViewById(R.id.tv_faqiren);
        TextView yibaoming = (TextView) convertView.findViewById(R.id.yibaoming);

        Button bt_jiaru = (Button) convertView.findViewById(R.id.bt_jiaru);
        ImageView iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);

        tv_name.setText(entity.getName());
        tv_address.setText(entity.getArea());
        tv_time.setText(entity.getBeginDate());
        tv_faqiren.setText("发起人："+entity.getAdmin());
        yibaoming.setText("已报名：" + entity.getPersonNum());
        MyLog.i("picid=", entity.getPicID());
        if (entity.getPicID().indexOf(";")==-1){
            imageLoader.displayImage(Constant.IMAGE_URL+entity.getPicID(),iv_photo,options);
        }else {
            String picStr = entity.getPicID().substring(0, entity.getPicID().indexOf(";"));
            MyLog.i("pic=",picStr);
            imageLoader.displayImage(Constant.IMAGE_URL+picStr,iv_photo,options);
        }

        bt_jiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //加入活动
                context.sendRequest(entity.getGroupID());
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = dataYue.get(position).getState();
                int groupID = dataYue.get(position).getGroupID();
                Intent intent = new Intent(context.getActivity(), YueHuodongDetailActivity.class);
                intent.putExtra("GroupID", groupID);
                intent.putExtra("type", type);
                context.startActivity(intent);
            }
        });
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
} 
