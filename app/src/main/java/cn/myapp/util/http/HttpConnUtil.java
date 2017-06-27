package cn.myapp.util.http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class HttpConnUtil {

	public static final int CONNECTION_TIMEOUT = 1000 * 60;
	public static final int SOCKET_TIMEOUT = CONNECTION_TIMEOUT;

	/**
	 * 检测网络状态
	 */
	public static boolean checkNetworkConnectionState(Context context) {
		boolean flag = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null) {
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
			connectivityManager = null;
			if (networkInfo != null) {
				for (int i = 0; i < networkInfo.length; i++) {
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return flag = true;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 检测wifi网络是否可用
	 */
	public static boolean isWifiAvailable(Activity activity) {
		Context context = activity.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param activity
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isWifiOr3GAvaiable(Activity activity) {
		ConnectivityManager connectManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager telephonyManager = (TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE);

		NetworkInfo info = connectManager.getActiveNetworkInfo();

		if (null == info || connectManager.getBackgroundDataSetting()) {
			return false;
		}

		int netType = info.getType();
		int netSubType = info.getSubtype();

		if (netType == ConnectivityManager.TYPE_WIFI) {
			return info.isConnected();
		} else if (netType == ConnectivityManager.TYPE_MOBILE
				&& netSubType == TelephonyManager.NETWORK_TYPE_UMTS
				&& !telephonyManager.isNetworkRoaming()) {
			return info.isConnected();
		} else {
			return false;
		}
	}

	/**
	 * 获取本地的IP地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}

	/**
	 * get请求。 传递URL，并且得到服务器返回的数据 联网失败，返回的数据为空，直接提示网络连接异常就可以了
	 * 如果联网成功，但获取的数据是错误的话，直接从返回的数据中解析异常信息
	 */
	public static String getHttpContent(String urlString) {
		StringBuffer stringBuffer = new StringBuffer();
		HttpURLConnection conn = null;
		InputStreamReader input = null;
		try {
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setReadTimeout(SOCKET_TIMEOUT);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				input = new InputStreamReader(conn.getInputStream());
				BufferedReader buffer = new BufferedReader(input, 8192);
				String inputLine = "";
				while (((inputLine = buffer.readLine()) != null)) {
					stringBuffer.append(inputLine);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * post请求。 传递URL，并且得到服务器返回的数据
	 */
	public static String postHttpContent(String urlStr, Map<String, Object> map) {
		StringBuffer stringBuffer = new StringBuffer();
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setReadTimeout(SOCKET_TIMEOUT);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			OutputStream os = conn.getOutputStream();
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String str = it.next();
				os.write(("&" + str + "=" + map.get(str)).getBytes());
			}
			os.flush();
			os.close();
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				// 得到读取的内容(流)
				InputStreamReader in = new InputStreamReader(
						conn.getInputStream());
				// 为输出创建BufferedReader
				BufferedReader buffer = new BufferedReader(in, 8192);
				String inputLine = "";
				// 使用循环来读取获得的数据
				while (((inputLine = buffer.readLine()) != null)) {
					stringBuffer.append(inputLine);
				}
				buffer.close();
				in.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			if (conn != null) {
				conn.disconnect();// 关闭http连接
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * 获取Drawable
	 * 
	 * @param url
	 * @return Drawable
	 * @since v 1.0
	 */
	@SuppressWarnings("deprecation")
	public static Drawable getDrawableFromWeb(String url) {
		InputStream inputStream = null;
		try {
			inputStream = (InputStream) new URL(url).getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		return bd;
	}

	/**
	 * 从网络上直接保存文件到本地
	 * 
	 * @param urlString
	 *            链接地址
	 * @param filePath
	 *            文件路径
	 * @return 下载结果：0-失败，1-成功
	 */
	public static Integer saveFileFromWeb(String urlString, String filePath) {
		Integer success = 0;
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				URL url = new URL(urlString);
				FileOutputStream fOut = new FileOutputStream(filePath);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(CONNECTION_TIMEOUT);
				conn.setDoInput(true);
				if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
					InputStream is = conn.getInputStream();
					byte buffer[] = new byte[1024];
					int count = 0;
					while ((count = is.read(buffer)) > 0) {
						fOut.write(buffer, 0, count);
					}
				}
				fOut.close();
				conn.disconnect();
				success = 1;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (success == 0 && file.exists()) {
			file.delete();
		}
		return success;
	}

	/**
	 * 获取网络图片保存在sd卡
	 * 
	 * @param urlString
	 * @param name
	 */
	public static Bitmap getImageFromWebAndSave(String urlString, String name) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(urlString);
			FileOutputStream fos = new FileOutputStream(name);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setDoInput(true);
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				InputStream is = conn.getInputStream();
				byte buffer[] = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
			}
			fos.flush();
			fos.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(name);
			bitmap = BitmapFactory.decodeStream(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/*
	 * 读取本地图片返回Drawable
	 */
	@SuppressWarnings("deprecation")
	public static Drawable readSD(String filePath) {
		Drawable drawable = null;
		File file = new File(filePath);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file.getAbsolutePath());
			drawable = new BitmapDrawable(BitmapFactory.decodeStream(fis));
			fis.close();
			file = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return drawable;
	}

	/**
	 * 传递URL，获取网页新闻源代码
	 * */
	public static String getOfflineData(String urlPath) {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECTION_TIMEOUT);
			conn.setReadTimeout(SOCKET_TIMEOUT);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				InputStreamReader input = new InputStreamReader(
						conn.getInputStream());
				BufferedReader buffer = new BufferedReader(input);
				String inputLine = "";
				while (((inputLine = buffer.readLine()) != null)) {
					stringBuffer.append(inputLine);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return stringBuffer.toString();
	}

}
