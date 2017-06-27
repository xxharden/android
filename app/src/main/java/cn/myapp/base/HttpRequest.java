package cn.myapp.base;



import cn.myapp.util.Constant;
import cn.myapp.util.MyLog;
import http.AsyncHttpClient;
import http.FileAsyncHttpResponseHandler;
import http.RequestParams;
import http.SyncHttpClient;
import http.TextHttpResponseHandler;

/**
 * 先用静态的吧，后期发现问题在改，AsyncHttpClient官方推荐的方式
 * 
 * @author xiaoyang
 */
public class HttpRequest {

	// 试用环境
	public static final String BASE_URL = Constant.SERVER_URL;
	private static AsyncHttpClient client = new AsyncHttpClient( );
	
	public static void postone( String url , RequestParams params, TextHttpResponseHandler responseHandler) {
		client.post( url ,params, responseHandler );
	}

	public static void get( String url ) {
		client.get( getAbsoluteUrl( url ), null );
	}

	public static void get( String url, RequestParams params ) {
		client.get( getAbsoluteUrl( url ), params, null );
	}

	public static void get( String url, RequestParams params, TextHttpResponseHandler responseHandler ) {
		client.get( getAbsoluteUrl( url ), params, responseHandler );
	}

	public static void post( String url ) {
		client.post( getAbsoluteUrl( url ), null );
	}

	public static void post( String url, RequestParams params ) {
		client.post( getAbsoluteUrl( url ), params, null );
	}

	public static void post( String url, RequestParams params, TextHttpResponseHandler responseHandler ) {
		MyLog.i("url", url + params);
		client.post( getAbsoluteUrl( url ), params, responseHandler );
	}

	private static SyncHttpClient client_s = new SyncHttpClient( );

	/**
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void download( String url, FileAsyncHttpResponseHandler responseHandler ) {
		client.get( url, responseHandler );
	}

	public static void addJsonHeader( ) {
		client.addHeader( "Content-Type", "application/json" );
	}

	private static String getAbsoluteUrl( String relativeUrl ) {
		return BASE_URL + relativeUrl;
	}

	public static void postSync( String url, RequestParams params, TextHttpResponseHandler responseHandler ) {
		client_s.post( getAbsoluteUrl( url ), params, responseHandler );
	}
}
