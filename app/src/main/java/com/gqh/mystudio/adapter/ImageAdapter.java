package com.gqh.mystudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.gqh.mystudio.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import cn.myapp.util.Constant;

/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: 轮播图adapter
 */
public class ImageAdapter extends BaseAdapter {

	private Context context;
	private List<String> drawables;
	private LayoutInflater mInflater;

	private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
	private ImageLoader imageLoader=ImageLoader.getInstance();

	public ImageAdapter(Context context, List<String> drawables) {
		this.context = context;
		this.drawables = drawables;
		this.mInflater=LayoutInflater.from(context);

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

	public Context getContext(){
		return context;
	}

	public List<String> getDrawables() {
		return drawables;
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return drawables.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return drawables.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
//	ImageView iv_lunbo;
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = new ImageView(context);
//			((ImageView) convertView).setScaleType(ScaleType.FIT_XY);
//			convertView=mInflater.inflate(R.layout.item_lunbotu,null);
//			iv_lunbo= (ImageView) convertView.findViewById(R.id.iv_lunbo);
		}

		if (drawables.size() > 0) {
			String item = drawables.get(position);
//			((ImageView) convertView).setImageDrawable(item);

			/**
			 * 显示图片
			 * 参数1：图片url
			 * 参数2：显示图片的控件
			 * 参数3：显示图片的设置
			 * 参数4：监听器
			 */
//			Toast.makeText(context,item,Toast.LENGTH_SHORT).show();
//			imageLoader.displayImage(item, (ImageView)convertView, options);

			final View finalConvertView = convertView;
			imageLoader.loadImage(item,options,new SimpleImageLoadingListener() {
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					super.onLoadingComplete(imageUri, view, loadedImage);

					finalConvertView.setBackground(new BitmapDrawable(loadedImage));
//					iv_lunbo.getBackground().setAlpha(100);//0~255透明度值
				}
			});
		}
		return convertView;
	}
}
