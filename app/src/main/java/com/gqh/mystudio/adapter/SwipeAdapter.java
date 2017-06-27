package com.gqh.mystudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gqh.mystudio.R;
import com.gqh.mystudio.activity.ShangchengJiesuanActivity;
import com.gqh.mystudio.entity.ShangpinListEntity;
import com.gqh.mystudio.view.SwipeItemLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.myapp.util.Constant;


public class SwipeAdapter extends BaseAdapter {
	private Context mContext = null;
	private ArrayList<ShangpinListEntity> mList;

	private DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
	private ImageLoader imageLoader=ImageLoader.getInstance();

	public SwipeAdapter(ArrayList<ShangpinListEntity> mList,Context context) {
		this.mContext = context;
		this.mList=mList;

		//df.format(zongJia)

		// 使用DisplayImageOptions.Builder()创建DisplayImageOptions
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.zhanweitu)          // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.zhanweitu)  // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.zhanweitu)       // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//				.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
				.build();                                   // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View contentView, ViewGroup arg2) {
		final ShangpinListEntity entity = mList.get(position);
		final double price = entity.getRealPrice();
		ViewHolder holder = null;
		if(contentView==null){
			holder = new ViewHolder();
			View view01 = LayoutInflater.from(mContext).inflate(R.layout.test01, null);
			View view02 = LayoutInflater.from(mContext).inflate(R.layout.test2, null);

			holder.iv_photo = (ImageView)view02.findViewById(R.id.iv_photo);
			holder.tv_name = (TextView)view02.findViewById(R.id.tv_name);
			holder.tv_jiage = (TextView)view02.findViewById(R.id.tv_jiage);
			holder.tv_danwei = (TextView)view02.findViewById(R.id.tv_danwei);
			holder.tv_shuliang = (TextView)view02.findViewById(R.id.tv_shuliang);
			holder.tv_zongjia = (TextView)view02.findViewById(R.id.tv_zongjia);
			holder.bt_jian = (Button)view02.findViewById(R.id.bt_jian);
			holder.bt_jia = (Button)view02.findViewById(R.id.bt_jia);

			contentView = new SwipeItemLayout(view02,view01, null, null);
			contentView.setTag(holder);

			view01.setOnClickListener(new OnClickListener() {


				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((ShangchengJiesuanActivity)mContext).deleteItem(position);
				}
			});

		}else{
			holder = (ViewHolder) contentView.getTag();
		}


		holder.tv_shuliang.setText(entity.getMyNumber()+"");
		holder.tv_name.setText(entity.getProName());
		holder.tv_jiage.setText(price+"");

		final DecimalFormat df=new DecimalFormat("0.00");
		String format=df.format(price*entity.getMyNumber());
		holder.tv_zongjia.setText(format);
		/**
		 * 显示图片
		 * 参数1：图片url
		 * 参数2：显示图片的控件
		 * 参数3：显示图片的设置
		 * 参数4：监听器
		 */
		imageLoader.displayImage(Constant.IMAGE_URL + entity.getPicPath(), holder.iv_photo, options);

		final ViewHolder finalHolder = holder;
		holder.bt_jian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int count = entity.getMyNumber();
				count -= 1;
				if (count > 0) {
					finalHolder.tv_shuliang.setText("" + count);
					finalHolder.tv_zongjia.setText( df.format(price * count));
					((ShangchengJiesuanActivity) mContext).deleteZongjia(price,count,position);

				} else {
					count=1;
					finalHolder.tv_shuliang.setText("" + count);
					finalHolder.tv_zongjia.setText( df.format(price * count));
					((ShangchengJiesuanActivity)mContext).deleteZongjia(0, count, position);
				}
			}
		});

		holder.bt_jia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int count = entity.getMyNumber();
				count += 1;
				finalHolder.tv_shuliang.setText("" + count);
				finalHolder.tv_zongjia.setText( df.format(price * count));
				((ShangchengJiesuanActivity) mContext).addZongjia(price,count,position);

			}
		});


		return contentView;
	}

	private static class ViewHolder{
		Button bt_jian;
		Button bt_jia;
		TextView tv_shuliang;
		TextView tv_zongjia;
		TextView tv_jiage;
		TextView tv_danwei;
		TextView tv_name;
		ImageView iv_photo;
	}
}
