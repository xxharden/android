package com.gqh.mystudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.QiaoMenTongxinActivity;
import com.gqh.mystudio.entity.QiaoMoreEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/5
 * desc:
 */
public class QiaoMoreAdapter extends MyBaseAdapter<QiaoMoreEntity> {

    private Context context;
    private List<QiaoMoreEntity> mList;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;

    public QiaoMoreAdapter(List<QiaoMoreEntity> mList, Context context) {
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
        final QiaoMoreEntity entity = mList.get(position);
        if (convertView==null){
            holder=new HoldView();
            convertView=mInflater.inflate(R.layout.item_my_friend_listview,null);
            holder.tv_name= (TextView) convertView.findViewById(R.id.name);
            holder.tv_age= (TextView) convertView.findViewById(R.id.age);
            holder.tv_xingzuo= (TextView) convertView.findViewById(R.id.xingzuo);
            holder.tv_address= (TextView) convertView.findViewById(R.id.address);
            holder.iv_photo= (ImageCircleView) convertView.findViewById(R.id.iv_photo);
            holder.iv_sex= (ImageView) convertView.findViewById(R.id.iv_sex);
            holder.bt_qiaomen= (Button) convertView.findViewById(R.id.bt_qiaomen);
            convertView.setTag(holder);

        }else{
            holder= (HoldView) convertView.getTag();

        }


        holder.tv_name.setText(entity.getUNickName());
        holder.tv_age.setText(entity.getUAge()+"岁");
        holder.tv_xingzuo.setText(entity.getUConstellation());
        holder.tv_address.setText(entity.getArea());

        imageLoader.displayImage(Constant.IMAGE_URL+entity.getUHeadPortrait(),holder.iv_photo,options);

        if (entity.getUSex().equals("00_011_1")){
            holder.iv_sex.setImageResource(R.drawable.sex_nan);
        }else {
            holder.iv_sex.setImageResource(R.drawable.sex_nv);
        }

        holder.bt_qiaomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int uId = entity.getUID();
                Intent intent=new Intent(context,QiaoMenTongxinActivity.class);
                intent.putExtra("fId",uId);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class HoldView{

        ImageView iv_photo;
        ImageView iv_sex;
        TextView tv_name;
        TextView tv_age;
        TextView tv_xingzuo;
        TextView tv_address;
        Button bt_qiaomen;

    }
}
