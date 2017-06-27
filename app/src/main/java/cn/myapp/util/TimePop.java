package cn.myapp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;


import com.gqh.mystudio.R;

import java.util.Calendar;

import cn.myapp.wheel.ScreenInfo;
import cn.myapp.wheel.WheelMain;


public class TimePop implements OnClickListener {

	private WheelMain wheelMain;
	private PopupWindow time1PopupMenu;
	private int code;
	private DatePop.DatePopEnterListener listener;

	public TimePop( Context context ) {

		LayoutInflater lay = LayoutInflater.from( context );
		View timepickerview = lay.inflate( R.layout.layout_date_wheel, null );

		View layout_pay = timepickerview.findViewById( R.id.layout_pay );
		View pay_on = timepickerview.findViewById( R.id.pay_on );
		layout_pay.setOnClickListener( this );
		pay_on.setOnClickListener( this );

		ScreenInfo screenInfo = new ScreenInfo( (Activity) context );
		wheelMain = new WheelMain( timepickerview, true, false );
		wheelMain.screenheight = screenInfo.getHeight( );
		Calendar calendar = Calendar.getInstance( );
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH );
		int day = calendar.get( Calendar.DAY_OF_MONTH );
		wheelMain.initDateTimePicker( year, month, day );

		time1PopupMenu = new PopupWindow( timepickerview, context.getResources( ).getDisplayMetrics( ).widthPixels, context.getResources( ).getDisplayMetrics( ).heightPixels / 3,
				true );

		// 点击屏幕其他区域，浮框消失
		time1PopupMenu.setTouchInterceptor( new OnTouchListener( ) {
			@Override
			public boolean onTouch( View v, MotionEvent event ) {
				if( event.getAction( ) == MotionEvent.ACTION_OUTSIDE ) {
					time1PopupMenu.dismiss( );
					return true;
				}

				return false;
			}
		} );

		// 设置整个popupwindow的样式。
		time1PopupMenu.setBackgroundDrawable( new BitmapDrawable( ) );
		// 使窗口里面的空间显示其相应的效果，比较点击button时背景颜色改变。
		// 如果为false点击相关的空间表面上没有反应，但事件是可以监听到的,listview的话就没有了作用。
		time1PopupMenu.setFocusable( true );
		time1PopupMenu.setOutsideTouchable( true );

	}

	public TimePop show( View view ) {
		time1PopupMenu.update( );
		time1PopupMenu.showAtLocation( view, Gravity.BOTTOM, 0, 0 );
		return this;
	}

	public TimePop setCode( int code ) {
		this.code = code;
		return this;
	}

	public TimePop setEnter( DatePop.DatePopEnterListener listener ) {
		this.listener = listener;
		return this;
	}

	@Override
	public void onClick( View v ) {
		switch( v.getId( ) ){
			case R.id.layout_pay :
				break;
			case R.id.pay_on :
				time1PopupMenu.dismiss( );
				if( this.listener != null ) {
					this.listener.onEnterListener( code, wheelMain.getTime( ) );
				}
				break;

			default:
				break;
		}
	}
}
