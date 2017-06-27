package cn.myapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

	public static final SimpleDateFormat FORMAT = new SimpleDateFormat( "yyyy-MM-dd" );
	
	/**
	 * 获得当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		Calendar calendar = Calendar.getInstance( );
		
		return FORMAT.format( calendar.getTime( ) );
	}
}
