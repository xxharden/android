package cn.myapp.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能类 <功能详细描述>
 * 
 * @author 姓名 工号
 * @version [版本号, 2014年12月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class Utility {
	/*
	 * 获取当前程序的版本号
	 */
	public static String getVersionName( Context context ) throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager( );
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo( context.getPackageName( ), 0 );
		return packInfo.versionName;
	}

	/**
	 * 手机号验证
	 * 
	 * @param mobiles
	 * @return 验证通过返回true
	 */
	public static boolean isMobile( String mobiles ) {
		boolean flag = false;
		try {
			// 13********* ,15********,18*********
			Pattern p = Pattern.compile( "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0,5-9]))\\d{8}$" );
			Matcher m = p.matcher( mobiles );
			flag = m.matches( );
		} catch( Exception e ) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone( String str ) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile( "^[0][1-9]{2,3}-[0-9]{5,10}$" ); // 验证带区号的
		p2 = Pattern.compile( "^[1-9]{1}[0-9]{5,8}$" ); // 验证没有区号的
		if( str.length( ) > 9 ) {
			m = p1.matcher( str );
			b = m.matches( );
		} else {
			m = p2.matcher( str );
			b = m.matches( );
		}
		return b;
	}

	public static boolean isEmail( String email ) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile( pattern1 );
		final Matcher mat = pattern.matcher( email );
		if( !mat.find( ) ) {
			tag = false;
		}
		return tag;
	}

	public static String getNonRepeatRandom( int len ) {
		if( len < 1 || len > 10 ) {
			return "";
		}

		Random random = new Random( );

		String str = random.nextInt( 10 ) + "";

		String formerStr = str;

		for( int index = 0; index < len - 1; ++index ) {

			while( formerStr.indexOf( str ) != -1 ) {

				str = random.nextInt( 10 ) + "";
			}

			formerStr += str;
		}

		return formerStr;
	}

	/**
	 * @return 返回一个随机数
	 */
	public static String getCoNo( ) {
		long time = System.currentTimeMillis( );
		return String.valueOf( time ) + new Random( ).nextInt( 1000 );
	}
}
