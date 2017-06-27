package cn.myapp.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.TypedValue;

import com.gqh.mystudio.application.BamsApplication;


public class PhoneUtil {
	private static final TelephonyManager tm = (TelephonyManager) BamsApplication.getInstance()
			.getSystemService(Context.TELEPHONY_SERVICE);

	/**
	 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID. Return null if device ID is not
	 * available.
	 */
	public static String getDeviceId() {
		return tm.getDeviceId();
	}

	/**
	 * 获取手机的mac地址
	 * @return
	 */
	public static String getMacAddress() {
		WifiManager wifi = (WifiManager) BamsApplication.getInstance( )
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE
	 */
	public static String getSubscriberId() {
		String subscriberId = getDeviceId();
		if (StringUtil.isEmpty(subscriberId)) {
			subscriberId = getMacAddress();
		}
		if (StringUtil.isEmpty(subscriberId)) {
			subscriberId = tm.getSubscriberId();
		}
		return subscriberId;
	}

	/**
	 * 获取当前应用的版本号
	 * 
	 * @return
	 */
	public static String getVersionName() {
		// 获取packagemanager的实例
		PackageManager packageManager = BamsApplication.getInstance( )
				.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(
					BamsApplication.getInstance( ).getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String version = packInfo.versionName;
		return version;
	}

	/**
	 * 获取当前系统的版本号
	 * @return
	 */
	public static String getSDKVersion() {
		return android.os.Build.VERSION.RELEASE;
	}
	
	/**
	 * 获取手机的型号
	 * @return
	 */
	public static String getModel() {
		return android.os.Build.MANUFACTURER+" "+android.os.Build.MODEL;
	}
	
	/**
	 * 获得状态栏的高度
	 * @return
	 */
	public static int getStatusBarHeight(Context context ) {
		Rect rect = new Rect( );
		((Activity)context).getWindow( ).getDecorView( ).getWindowVisibleDisplayFrame( rect );
		return rect.top;
	}
	
	/**
	 * dp转像素
	 * @param dp
	 * @param context
	 * @return
	 */
	public static int dp2px( int dp ,Context context) {
		return (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources( ).getDisplayMetrics( ) );
	}
	
	public static void call(Context context, String number) {
		// 用intent启动拨打电话
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ number));
		context.startActivity(intent);
	}
	
	public static void sendMessage(Context context, String number) {
		Uri uri = Uri.parse("smsto:"+number);            
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);            
		context.startActivity(it); 
	}	
	public static void sendEmail(Context context, String email) {
		Intent data=new Intent(Intent.ACTION_SENDTO);
		data.setData(Uri.parse("mailto:way.ping.li@gmail.com"));
		data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
		data.putExtra(Intent.EXTRA_TEXT, "这是内容");
		context.startActivity(data); 
	}
}
