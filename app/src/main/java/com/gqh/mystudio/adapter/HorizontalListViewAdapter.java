package com.gqh.mystudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqh.mystudio.R;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.utill.BitmapUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.myapp.base.MyBaseAdapter;
import cn.myapp.util.Constant;


public class HorizontalListViewAdapter extends MyBaseAdapter<ShangpinListEntity>{

	private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
	private ImageLoader imageLoader=ImageLoader.getInstance();

	public HorizontalListViewAdapter(List<ShangpinListEntity> mList, Context context) {
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
		ShangpinListEntity entity = mList.get(position);
		String pic = Constant.IMAGE_URL+entity.getPicPath();
		ViewHolder holder=null;
		if (convertView==null){
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_listview_image_1,null);
			holder.iv_photo= (ImageView) convertView.findViewById(R.id.iv_photo);
			convertView.setTag(holder);
		}else {

			holder= (ViewHolder) convertView.getTag();
		}
		imageLoader.displayImage(pic,holder.iv_photo,options);
		return convertView;
	}
	private static class ViewHolder{
		ImageView iv_photo;
	}
}