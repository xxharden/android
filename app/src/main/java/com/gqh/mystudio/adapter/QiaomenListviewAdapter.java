package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.QiaoListEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tarena.utils.ImageCircleView;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc: 敲门列表adapter
 */
public class QiaomenListviewAdapter extends MyBaseAdapter<QiaoListEntity> {


    private Context context;
    private List<QiaoListEntity> mList;

    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private LayoutInflater mInflater;;


    public QiaomenListviewAdapter(Context context, List<QiaoListEntity> mList) {
        super(mList,context);
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
        QiaoListEntity entity = mList.get(position);
        HoldView holder = null;
        if (convertView == null) {
            holder = new HoldView();
            convertView = mInflater.inflate(R.layout.item_listview_qiaoliebiao, null);
//            holder.tv_name=convertView.findViewById(R.id.);

            convertView.setTag(holder);
        }else {
            holder= (HoldView) convertView.getTag();
        }
            return convertView;
    }

        private static class HoldView{
            ImageCircleView image_photo;
            ImageView iv_xingbie;
            TextView tv_name;
            TextView tv_qianming;
        }
}
