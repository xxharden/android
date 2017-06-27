package com.gqh.mystudio.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.gqh.mystudio.R;

/**
 * 
 * @author lxqljc
 *
 */
public class MyRatingBar extends LinearLayout{

	private CheckBox cb1;
	private CheckBox cb2;
	private CheckBox cb3;
	private CheckBox cb4;
	private CheckBox cb5;

	private int starNum;
	private View rootView;

	public MyRatingBar(Context context) {
		super(context);
		init(context);
	}

	public MyRatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyRatingBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	/**
	 * 初始化布局
	 */
	private void init(Context context)
	{
		//加载自定义的checkbox布局
        rootView = LayoutInflater.from(context).inflate(R.layout.ratingbar, this);
		cb1 = (CheckBox)findViewById(R.id.cb1);
		cb2 = (CheckBox)findViewById(R.id.cb2);
		cb3 = (CheckBox)findViewById(R.id.cb3);
		cb4 = (CheckBox)findViewById(R.id.cb4);
		cb5 = (CheckBox)findViewById(R.id.cb5);

		//给所有的按钮设置点击事件,记住是点击按钮哦，不是监听变化的按钮，监听变化那个之前试过，貌似区分不了checkbox的id
		cb1.setOnClickListener(new MyOnClickListener());
		cb2.setOnClickListener(new MyOnClickListener());
		cb3.setOnClickListener(new MyOnClickListener());
		cb4.setOnClickListener(new MyOnClickListener());
		cb5.setOnClickListener(new MyOnClickListener());

		//设置默认选择四星
		setStarNum(0);

	}

	/**
	 * 按钮监听事件
	 */
	class MyOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.cb1:
				//选择第一个星
				setStarNum(1);
				starNum = 1;
				break;
			case R.id.cb2:
				//选择第两个星
				setStarNum(2);
				starNum = 2;
				break;
			case R.id.cb3:
				//选择第三个星
				setStarNum(3);
				starNum = 3;
				break;
			case R.id.cb4:
				//选择第四个星
				setStarNum(4);
				starNum = 4;
				break;
			case R.id.cb5:
				//选择第五个星
				setStarNum(5);
				starNum = 5;
				break;

			default:
				break;
			}
			//接口回调
			onStarChecked.getStarNum(starNum,rootView);
		}

	}

	/**
	 * 点击后设置星星数
	 */
	private void setStarNum(int num)
	{
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		boolean b4 = false;
		boolean b5 = false;

		switch (num) {
		case 1:
			b1 = true;
			break;
		case 2:
			b1 = true;
			b2 = true;
			break;
		case 3:
			b1 = true;
			b2 = true;
			b3 = true;
			break;
		case 4:
			b1 = true;
			b2 = true;
			b3 = true;
			b4 = true;
			break;
		case 5:
			b1 = true;
			b2 = true;
			b3 = true;
			b4 = true;
			b5 = true;
			break;

		default:
			break;
		}

		cb1.setChecked(b1);
		cb2.setChecked(b2);
		cb3.setChecked(b3);
		cb4.setChecked(b4);
		cb5.setChecked(b5);
	}

	private OnStarChecked onStarChecked;

	/**
	 * @param 实现接口的对象监听
	 */
	public void setOnStarChecked(OnStarChecked onStarChecked)
	{
		this.onStarChecked = onStarChecked;
	}

	/**
	 * 获得星级数量接口回调
	 */
	public interface OnStarChecked
	{
		/**
		 * 
		 * @param num 点击的星星数量
		 * @param v 点击星星的父控件
		 */
		void getStarNum(int num, View view);
	}


}
