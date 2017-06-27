package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.QiaoMyQiaoActivity;
import com.gqh.mystudio.application.BamsApplication;
import com.gqh.mystudio.entity.MyQiaoEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;
import http.RequestParams;

/**
 * @author : 红仔
 * @date : 2016/3/16
 * desc:
 */
public class MyQiaolistAdapter extends MyBaseAdapter<MyQiaoEntity> {

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    public MyQiaolistAdapter(List<MyQiaoEntity> mList, Context context) {
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

        final MyQiaoEntity entity = mList.get(position);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_my_friend_listview, null);
            holder.iv_photo= (ImageCircleView) convertView.findViewById(R.id.iv_photo);
            holder.iv_sex= (ImageView) convertView.findViewById(R.id.iv_sex);
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.age= (TextView) convertView.findViewById(R.id.age);
            holder.xingzuo= (TextView) convertView.findViewById(R.id.xingzuo);
            holder.address= (TextView) convertView.findViewById(R.id.address);
            holder.bt_qiaomen= (Button) convertView.findViewById(R.id.bt_qiaomen);
            holder.bt_kaimen= (Button) convertView.findViewById(R.id.bt_kaimen);
            holder.bt_jujue= (Button) convertView.findViewById(R.id.bt_jujue);
            holder.tv_state= (TextView) convertView.findViewById(R.id.tv_state);
            convertView.setTag(holder);
        }else {

            holder= (ViewHolder) convertView.getTag();
        }



        if (entity.getUSex().equals(Constant.SEX_MAN)){
            holder.iv_sex.setImageResource(R.drawable.sex_nan);
        }else {
            holder.iv_sex.setImageResource(R.drawable.sex_nv);
        }

        holder.name.setText(entity.getUNickName());
        holder.age.setText(entity.getUAge()+"岁");
        holder.xingzuo.setText(entity.getUConstellation());
        holder.address.setText(entity.getArea());
        holder.bt_qiaomen.setVisibility(View.GONE);
        imageLoader.displayImage(Constant.IMAGE_URL + entity.getUHeadPortrait(), holder.iv_photo);

        MyLog.i("entity====",entity.getState()+"----"+entity.getFtype());
        if (entity.getFtype().equals("1")){
            holder.tv_state.setVisibility(View.VISIBLE);
            holder.tv_state.setText("已开门");
        }else {
            if (entity.getState().equals("1")){
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText("已拒绝");
            }else {
                if (entity.getUID()==BamsApplication.getInstance().getUser().getUID()){
                    holder.tv_state.setVisibility(View.VISIBLE);
                    holder.tv_state.setText("等待");
                    MyLog.i("entity.getUID()==",entity.getUID()+"");
                }else {
                    holder.tv_state.setVisibility(View.GONE);
                }
                if (entity.getFID()==BamsApplication.getInstance().getUser().getUID()){
                    holder.bt_kaimen.setVisibility(View.VISIBLE);
                    holder.bt_jujue.setVisibility(View.VISIBLE);
                    MyLog.i("entity.getFID()===", entity.getFID() + "");

                    holder.bt_kaimen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            接受-1，黑名单-2，拒绝-0
                            ((QiaoMyQiaoActivity)context).sendRequest("1",entity.getUID());//友人的UID(敲门人)
                        }
                    });

                    holder.bt_jujue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((QiaoMyQiaoActivity)context).sendRequest("0", entity.getUID());//友人的UID(敲门人)
                        }
                    });
                }else {
                    holder.bt_kaimen.setVisibility(View.GONE);
                    holder.bt_jujue.setVisibility(View.GONE);
                }
            }
        }
        return convertView;
    }
    private static  class ViewHolder {
        ImageCircleView iv_photo;
        ImageView iv_sex;
        TextView name;
        TextView age;
        TextView xingzuo;
        TextView address;
        Button bt_qiaomen;
        Button bt_kaimen;
        Button bt_jujue;
        TextView tv_state;
    }
}
