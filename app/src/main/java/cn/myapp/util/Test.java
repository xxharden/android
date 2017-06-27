package cn.myapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 访问http
 * @author wg
 *
 */
public class Test {
//	public static void main(String[] args) {
//		String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=";
//		String r1="13716192025";
//		String fanhui=getURLContent(url+r1);
//		System.out.print("fanhui"+fanhui);
//		String s=fanhui.substring(fanhui.indexOf("carrier:'")+9,fanhui.length()-2);
//		System.out.print(s);
//	}

	/**
	 * 程序中访问http数据接口
	 */
	public static String getURLContent(String urlStr) {
		MyLog.i("ad","usrstr=="+urlStr);
		/** 网络的url地址 */
		URL url = null;
		/** http连接 */
		HttpURLConnection httpConn = null;
		/**//** 输入流 */
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlStr);
			MyLog.i("da","进来了0.1"+url);
			in = new BufferedReader(new InputStreamReader(url.openStream(),
					"utf-8"));
			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		String result = sb.toString();
		System.out.println("1"+result+"");
		return result;
	}
}
