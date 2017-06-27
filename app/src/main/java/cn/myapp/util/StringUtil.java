package cn.myapp.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author xiaoyang
 */
public class StringUtil {

	/**
	 * 判断字符数是否由字母，数字组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPw( String str ) {
		String pipeRegex = "^[0-9A-Za-z]{6,10}$";
		Pattern pipePattern = Pattern.compile( pipeRegex, Pattern.CASE_INSENSITIVE );
		return pipePattern.matcher( str ).matches( );
	}

	/**
	 * 判断字符串数是否url
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isURL( String str ) {
		String pipeRegex = "^(http://)?[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_\\-]+){1,8}(:[0-9]+)?(/[^/\\\\:<>\\*\\?\\|\"]*)*(\\?.*)*$";
		Pattern pipePattern = Pattern.compile( pipeRegex, Pattern.CASE_INSENSITIVE );
		return pipePattern.matcher( str ).matches( );
	}

	/**
	 * 是否是空的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty( String str ) {
		if( str == null || "".equals( str )||"null".equals(str) ) {
			MyLog.i("isempty", "返回"+"true");
			return true;
		}
		MyLog.i("isempty", "返回"+"false");
		return false;
	}

	/**
	 * 是否是非空的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty( String str ) {
		if( str != null && !"".equals( str ) ) {
			return true;
		}
		return false;
	}

	/**
	 * 获得汉语拼音首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getAlpha( String str ) {
		if( str == null ) {
			return "#";
		}

		if( str.trim( ).length( ) == 0 ) {
			return "#";
		}

		char c = str.trim( ).substring( 0, 1 ).charAt( 0 );
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile( "^[A-Za-z]+$" );
		if( pattern.matcher( c + "" ).matches( ) ) {
			return ( c + "" ).toUpperCase( );
		} else {
			return "#";
		}
	}

	/**
	 * 根据日期解析出当前星期几
	 * 
	 * @param data
	 * @return
	 */
	public static String dayForWeek( Date data ) {
		Calendar c = Calendar.getInstance( );
		c.setTime( data );
		String dayForWeek = null;
		switch( c.get( Calendar.DAY_OF_WEEK ) ){
			case 1 :
				dayForWeek = "日";
				break;
			case 2 :
				dayForWeek = "一";
				break;
			case 3 :
				dayForWeek = "二";
				break;
			case 4 :
				dayForWeek = "三";
				break;
			case 5 :
				dayForWeek = "四";
				break;
			case 6 :
				dayForWeek = "五";
				break;
			case 7 :
				dayForWeek = "六";
				break;
			default:
				break;
		}
		return dayForWeek;
	}

	/**
	 * 截取日期
	 * 
	 * @param time
	 *        日期格式:yyyy-MM-dd HH:mm:ss
	 * @param length
	 *        从左截取个数
	 * @return
	 */
	public static String getSubTime( String time, int length ) {
		String newSubTime = "";
		if( StringUtil.isNotEmpty( time ) ) {
			newSubTime = time.substring( 0, length );
		}
		return newSubTime;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date 日期
	 * @return str 转换的格式
	 */
	public static String DateToStr( Date date, String format ) {
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		String str = sdf.format( date );
		return str;
	}

	/**
	 * 把date类型转换成yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToStr( Date date ) {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String str = sdf.format( date );
		return str;
	}

	/**
	 * 把long类型转换成yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String longToStr( long date ) {
		return DateToStr( new Date( date ) );
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate( String str, String format ) {
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		Date date = null;
		try {
			date = sdf.parse( str );
		} catch( ParseException e ) {
			e.printStackTrace( );
		}
		return date;
	}

	/**
	 * 打电话
	 * 
	 * @param context
	 * @param tel
	 */
	public static void call( Context context, String tel ) {
		if( StringUtil.isNotEmpty( tel ) ) {
			String sz = "tel:" + tel;
			Uri uri = Uri.parse( sz );
			Intent intent = new Intent( Intent.ACTION_DIAL, uri );
			context.startActivity( intent );
		}
	}

	/**
	 * 格式化价格
	 * 
	 * @return
	 */
	public static String formatPrice( String price ) {
		String newprice = "0.00";
		if( StringUtil.isNotEmpty( price ) ) {
			DecimalFormat df = new DecimalFormat( "#######0.00" );
			newprice = df.format( Double.valueOf( price ) );
		}
		return newprice;
	}

	/**
	 * 格式化价格
	 * 
	 * @return
	 */
	public static String formatPrice( Double price ) {
		String newprice = "0.00";
		DecimalFormat df = new DecimalFormat( "#######0.00" );
		newprice = df.format( price );
		return newprice;
	}

	/**
	 * 字符串转换成日期 (默认格式：yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param str
	 * @return date
	 */
	public static Date strToDate( String str ) {
		if( StringUtil.isNotEmpty( str ) ) {
			return strToDate( str, "yyyy-MM-dd HH:mm:ss" );
		}
		return null;
	}

	/**
	 * 比较时间大小
	 * 
	 * @param firsttime
	 * @param secondtime
	 * @return true:fristtime>secondtime,false:fristtime<=secondtime
	 */
	public static Boolean isgt( String firsttime, String secondtime )// is greater
																		// than
	{
		Boolean isFlag = false;
		Date firstdate = strToDate( firsttime );
		Date seconddate = strToDate( secondtime );
		if( firstdate.getTime( ) > seconddate.getTime( ) ) {
			isFlag = true;
		}
		return isFlag;

	}

	/**
	 * 截取字符串（超过一定长度加...）
	 * 
	 * @param text
	 * @param len
	 *        个数
	 * @return
	 */
	public static String getEllipsisString( String text, int len ) {
		String newString = text;
		if( StringUtil.isNotEmpty( text ) && text.length( ) > len ) {
			newString = text.subSequence( 0, len - 1 ) + "...";
		}
		return newString;
	}

	/**
	 * 不够2位用0补齐
	 * 
	 * @param str
	 * @return
	 */
	public static String LeftPad_Tow_Zero( int str ) {
		DecimalFormat format = new DecimalFormat( "00" );
		return format.format( str );

	}

	/**
	 * 转换大小写
	 * 
	 * @param str
	 * @return
	 */
	public static String getCnString( String str ) {
		Double n = StringToDouble( str );
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs( n );

		String s = "";
		for( int i = 0; i < fraction.length; i++ ) {
			s += ( digit[(int) ( Math.floor( n * 10 * Math.pow( 10, i ) ) % 10 )] + fraction[i] ).replaceAll( "(零.)+", "" );
		}
		if( s.length( ) < 1 ) {
			s = "整";
		}
		int integerPart = (int) Math.floor( n );

		for( int i = 0; i < unit[0].length && integerPart > 0; i++ ) {
			String p = "";
			for( int j = 0; j < unit[1].length && n > 0; j++ ) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll( "(零.)*零$", "" ).replaceAll( "^$", "零" ) + unit[0][i] + s;
		}
		return head + s.replaceAll( "(零.)*零元", "元" ).replaceFirst( "(零.)+", "" ).replaceAll( "(零.)+", "零" ).replaceAll( "^整$", "零元整" );
	}

	/**
	 * String转Double
	 * 
	 * @param str
	 * @return
	 */
	public static Double StringToDouble( String str ) {

		if( StringUtil.isEmpty( str ) ) {
			return 0.0;
		}
		try {
			return Double.valueOf( str );
		} catch( NumberFormatException e ) {
			e.printStackTrace( );
		}
		return 0.0;
	}

	/**
	 * 摘自commons-lang.jar
	 * 
	 * @return String
	 */
	public static String replaceEach( String text, String[] searchList, String[] replacementList ) {
		return replaceEach( text, searchList, replacementList, false, 0 );
	}

	private static String replaceEach( String text, String[] searchList, String[] replacementList, boolean repeat, int timeToLive ) {

		// mchyzer Performance note: This creates very few new objects (one
		// major goal)
		// let me know if there are performance requests, we can create a
		// harness to measure

		if( text == null || text.length( ) == 0 || searchList == null || searchList.length == 0 || replacementList == null || replacementList.length == 0 ) {
			return text;
		}

		// if recursing, this shouldn't be less than 0
		if( timeToLive < 0 ) {
			throw new IllegalStateException( "Aborting to protect against StackOverflowError - " + "output of one loop is the input of another" );
		}

		int searchLength = searchList.length;
		int replacementLength = replacementList.length;

		// make sure lengths are ok, these need to be equal
		if( searchLength != replacementLength ) {
			throw new IllegalArgumentException( "Search and Replace array lengths don't match: " + searchLength + " vs " + replacementLength );
		}

		// keep track of which still have matches
		boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

		// index on index that the match was found
		int textIndex = -1;
		int replaceIndex = -1;
		int tempIndex = -1;

		// index of replace array that will replace the search string found
		// NOTE: logic duplicated below START
		for( int i = 0; i < searchLength; i++ ) {
			if( noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length( ) == 0 || replacementList[i] == null ) {
				continue;
			}
			tempIndex = text.indexOf( searchList[i] );

			// see if we need to keep searching for this
			if( tempIndex == -1 ) {
				noMoreMatchesForReplIndex[i] = true;
			} else {
				if( textIndex == -1 || tempIndex < textIndex ) {
					textIndex = tempIndex;
					replaceIndex = i;
				}
			}
		}
		// NOTE: logic mostly below END

		// no search strings found, we are done
		if( textIndex == -1 ) {
			return text;
		}

		int start = 0;

		// get a good guess on the size of the result buffer so it doesn't have
		// to double if it goes over a bit
		int increase = 0;

		// count the replacement text elements that are larger than their
		// corresponding text being replaced
		for( int i = 0; i < searchList.length; i++ ) {
			if( searchList[i] == null || replacementList[i] == null ) {
				continue;
			}
			int greater = replacementList[i].length( ) - searchList[i].length( );
			if( greater > 0 ) {
				increase += 3 * greater; // assume 3 matches
			}
		}
		// have upper-bound at 20% increase, then let Java take over
		increase = Math.min( increase, text.length( ) / 5 );

		StringBuilder buf = new StringBuilder( text.length( ) + increase );

		while( textIndex != -1 ) {

			for( int i = start; i < textIndex; i++ ) {
				buf.append( text.charAt( i ) );
			}
			buf.append( replacementList[replaceIndex] );

			start = textIndex + searchList[replaceIndex].length( );

			textIndex = -1;
			replaceIndex = -1;
			tempIndex = -1;
			// find the next earliest match
			// NOTE: logic mostly duplicated above START
			for( int i = 0; i < searchLength; i++ ) {
				if( noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length( ) == 0 || replacementList[i] == null ) {
					continue;
				}
				tempIndex = text.indexOf( searchList[i], start );

				// see if we need to keep searching for this
				if( tempIndex == -1 ) {
					noMoreMatchesForReplIndex[i] = true;
				} else {
					if( textIndex == -1 || tempIndex < textIndex ) {
						textIndex = tempIndex;
						replaceIndex = i;
					}
				}
			}
			// NOTE: logic duplicated above END

		}
		int textLength = text.length( );
		for( int i = start; i < textLength; i++ ) {
			buf.append( text.charAt( i ) );
		}
		String result = buf.toString( );
		if( !repeat ) {
			return result;
		}

		return replaceEach( result, searchList, replacementList, repeat, timeToLive - 1 );
	}

	public static String md5( String paramString ) {
		String returnStr;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance( "MD5" );
			localMessageDigest.update( paramString.getBytes( ) );
			returnStr = byteToHexString( localMessageDigest.digest( ) );
			return returnStr;
		} catch( Exception e ) {
			return paramString;
		}
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString( byte[] b ) {
		StringBuffer hexString = new StringBuffer( );
		for( int i = 0; i < b.length; i++ ) {
			String hex = Integer.toHexString( b[i] & 0xFF );
			if( hex.length( ) == 1 ) {
				hex = '0' + hex;
			}
			hexString.append( hex.toUpperCase( ) );
		}
		return hexString.toString( );
	}
}
