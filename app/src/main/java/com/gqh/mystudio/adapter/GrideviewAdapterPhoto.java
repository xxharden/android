package com.gqh.mystudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gqh.mystudio.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc:
 */
public class GrideviewAdapterPhoto extends MyBaseAdapter<String> {


    List<String> mList;
    Context context;
    int style;
    private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    private ImageLoader imageLoader=ImageLoader.getInstance();

    private LayoutInflater mInflater;;
    public GrideviewAdapterPhoto(List<String> mList, Context context, int style) {//Constant.STYLE_ADAPTER_OK显示全部
        super(mList, context);
        this.context=context;
        this.mList=mList;
        this.style=style;
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
    public int getCount() {

        if (style== Constant.STYLE_ADAPTER_OK){
            return mList.size();
        }else {
            if (mList.size()<3){
                return mList.size();
            }else {
                return 3;
            }
        }


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String entity = mList.get(position);
        ImageView iv = null;
        if (convertView==null){
            convertView =mInflater.inflate(R.layout.item_gridview_photo_3_2, null) ;
//            ((ImageView) convertView).setScaleType(ImageView.ScaleType.FIT_XY);
        }
        iv = (ImageView) convertView.findViewById(R.id.iv_photo);

        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(mList.get(position),iv,options);
        return convertView;
    }
}
