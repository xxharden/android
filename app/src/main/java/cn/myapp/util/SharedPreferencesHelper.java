package cn.myapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.Map;

/**
 * 软件参数设置器 默认全部使用String类型存储数据，如有其他类型数据请自行转换。
 * 
 * @author xiaoyang
 */
public class SharedPreferencesHelper {

	/** SharedPreferences xml 名称 */
	private static final String APP_SHARED_STR = "bams";

	/**
	 * 设置参数
	 * 
	 * @param activity
	 * @param date
	 * @return
	 */
	public static synchronized boolean setMap(Context context, Map<String, String> date) {

		Editor editor = context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit();

		for (String key : date.keySet()) {
			editor.putString(key, date.get(key));
		}
		editor.commit();

		return true;
	}

	/**
	 * 设置字符串
	 * 
	 * @param activity
	 * @param date
	 * @return
	 */
	public static synchronized boolean setString(Context context, String key, String val) {
		Editor editor = context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit();
		editor.putString(key, val);
		editor.commit();
		return true;
	}

	/**
	 * 设置int
	 * 
	 * @param activity
	 * @param date
	 * @return
	 */
	public static synchronized boolean setInt(Context context, String key, int val) {
		Editor editor = context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit();
		editor.putInt(key, val);
		editor.commit();
		return true;
	}

	/**
	 * 获取int
	 * 
	 * @param activity
	 * @param date
	 * @return
	 */
	public static synchronized int getInt(Context context, String key, int val) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getInt(key, val);
	}

	/**
	 * 设置Long
	 * 
	 * @param activity
	 * @param date
	 * @return
	 */
	public static synchronized boolean setLong(Context context, String key, Long val) {
		Editor editor = context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit();
		editor.putLong(key, val);
		editor.commit();
		return true;
	}

	public static synchronized void removeValuesForKey(Context context, String key) {
		Editor editor = context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * 获取String类型参数
	 * 
	 * @param activity
	 * @param key
	 * @return
	 */
	public static String getStringValue(Context context, String key) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getString(key, "");
	}

	/**
	 * 获取Long类型参数
	 * 
	 * @param activity
	 * @param key
	 * @return
	 */
	public static Long getLongValue(Context context, String key) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getLong(key, 0L);
	}

	/**
	 * 从SharedPreferences 获取一个boolean值，默认为false
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Context context, String key) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getBoolean(key, false);
	}
	/**
	 * 从SharedPreferences 获取一个boolean值，默认为true
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBooleanTrue(Context context, String key) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getBoolean(key, true);
	}
	
	/**
	 * 从SharedPreferences 获取一个boolean值，默认为false
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,boolean boo) {
		return context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).getBoolean(key, boo);
	}

	/**
	 * 设置 一个boolean 值到SharedPreferences
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static synchronized void setBoolean(Context context, String key, boolean value) {
		context.getSharedPreferences(APP_SHARED_STR, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
	}

	/**
	 * 访问其他应用的配置 请确认其他应用允许被访问，
	 * 
	 * @param activity
	 * @param key
	 * @param packageName
	 *            要访问程序的包名
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws NameNotFoundException
	 */
	public static String getValueFromOtherApp(Activity activity, String key, String packageName, String fileName) throws NameNotFoundException {
		Context context = activity.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
		return context.getSharedPreferences(fileName, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE).getString(key, null);
	}
}
