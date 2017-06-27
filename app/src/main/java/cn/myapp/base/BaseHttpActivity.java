package cn.myapp.base;

import android.view.View;
import org.apache.http.Header;
import cn.myapp.view.IphoneDialog;
import http.RequestParams;
import http.TextHttpResponseHandler;

/**
 * 有http请求的界面继承次类
 * 
 * @author xiaoyang
 */
public abstract class BaseHttpActivity extends BaseHttpTitleActivity {

	protected IphoneDialog iphoneDialog;
	private boolean isShowLoading;
	private boolean isShowDialog;

	@Override
	protected void initHttp( ) {
		initDialog( );
		request( );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 */
	protected void post(  String url  ) {
		this.post( url, null );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 */
	protected void post(  String url , RequestParams params ) {
//		Log.i("ffff","发送=="+url+"----"+params);
		this.post( url, params, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void post(  String url , RequestParams params, final boolean isShowLoading ) {
		this.post( url, params, 0, isShowLoading );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void post(  String url , RequestParams params, final int requestCode, final boolean isShowLoading ) {
		this.post( url, params, requestCode, isShowLoading, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 * @param isShowDialog
	 *        是否要显示对话框
	 */
	protected void post(  String url , RequestParams params, final int requestCode, final boolean isShowLoading, final boolean isShowDialog ) {
		BaseHttpActivity.this.isShowLoading = isShowLoading;
		BaseHttpActivity.this.isShowDialog = isShowDialog;
		HttpRequest.post( url , params, new TextHttpResponseHandler( ) {
			@Override
			public void onStart( ) {
				BaseHttpActivity.this.onMyStart( );
			}
			@Override
			public void onSuccess( int statusCode, Header[] headers, String responseString ) {
//				MyLog.i("1234", responseString);
				BaseHttpActivity.this.onSuccess( requestCode, responseString );
			}
			@Override
			public void onFailure( int statusCode, Header[] headers, String responseString, Throwable throwable ) {
//				MyLog.e( "123", throwable.getMessage( ) );
				BaseHttpActivity.this.onFailure( requestCode );
			}
			@Override
			public void onFinish( ) {
				BaseHttpActivity.this.onFinish( requestCode );
			}
		} );
	}

	/**
	 * 控制加载条显示
	 */
	protected void showLoadView( ) {
		if( isShowLoading ) {
//			loadingView.setVisibility( View.VISIBLE );
		} else {
			loadingView.setVisibility( View.GONE );
		}
		if( isShowLoading ) {
			iphoneDialog.show( );
		}
	}

	/**
	 * 控制加载条隐藏
	 */
	protected void hideLoadView( ) {
		if( isShowLoading ) {
			loadingView.setVisibility( View.GONE );
		}
		if( isShowLoading ) {
			iphoneDialog.cancel( );
		}
	}

	/**
	 * get请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 */
	protected void get( String url ) {
		this.get( url, null );
	}

	/**
	 * get请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 */
	protected void get(  String url , RequestParams params ) {
//		Log.i("sss","get发送-->"+ url+"______"+params);
		this.get( url, params, true );
	}

	/**
	 * get请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void get(  String url , RequestParams params, final boolean isShowLoading ) {
		this.get( url, params, 0, isShowLoading );
	}

	/**
	 * get请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void get(  String url , RequestParams params, final int requestCode, final boolean isShowLoading ) {
		this.get( url, params, requestCode, isShowLoading, true );
	}

	/**
	 * get请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 * @param isShowDialog
	 *        是否要显示对话框
	 */
	protected void get(  String url , RequestParams params, final int requestCode, final boolean isShowLoading, final boolean isShowDialog ) {
		BaseHttpActivity.this.isShowLoading = isShowLoading;
		BaseHttpActivity.this.isShowDialog = isShowDialog;
		HttpRequest.get( url, params, new TextHttpResponseHandler( ) {
			@Override
			public void onStart( ) {
				BaseHttpActivity.this.onMyStart( );
			}
			@Override
			public void onSuccess( int statusCode, Header[] headers, String responseString ) {
				BaseHttpActivity.this.onSuccess( requestCode, responseString );
			}
			@Override
			public void onFailure( int statusCode, Header[] headers, String responseString, Throwable throwable ) {
				BaseHttpActivity.this.onFailure( requestCode );
			}
			@Override
			public void onFinish( ) {
				BaseHttpActivity.this.onFinish( requestCode );
			}
		} );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 */
	protected void postDialog(  String url  ) {
		this.postDialog( url, null );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 */
	protected void postDialog(  String url , RequestParams params ) {
		this.postDialog( url, params, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 */
	protected void postDialog(  String url , RequestParams params, int requestCode ) {
		this.postDialog( url, params, requestCode, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void postDialog(  String url , RequestParams params, final boolean isShowLoading ) {
		this.postDialog( url, params, 0, isShowLoading );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void postDialog(  String url , RequestParams params, int requestCode, final boolean isShowLoading ) {
		this.post( url, params, requestCode, isShowLoading, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 */
	protected void getDialog(  String url  ) {
		this.getDialog( url, null );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 */
	protected void getDialog(  String url , RequestParams params ) {
		this.getDialog( url, params, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 */
	protected void getDialog(  String url , RequestParams params, int requestCode ) {
		this.getDialog( url, params, requestCode, true );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void getDialog(  String url , RequestParams params, final boolean isShowLoading ) {
		this.getDialog( url, params, 0, isShowLoading );
	}

	/**
	 * post请求方法 停止加载条,失败之后显示重新加载或设置网络 这些父类都处理了 子类不需要在处理 这种是弹对话框的形式
	 * 
	 * @param url
	 *        请求地址只需要传后面的就可以了 host不需要传
	 * @param object
	 *        请求参数封装的json对象
	 * @param requestCode
	 *        请求码 用来区分一个activity中有多个请求
	 * @param isShowLoading
	 *        是否要显示加载的界面
	 */
	protected void getDialog(  String url , RequestParams params, int requestCode, final boolean isShowLoading ) {
		this.get( url, params, requestCode, isShowLoading, true );
	}

	/**
	 * 发送请求之前调用此方法，子类如有需要可以重写此方法
	 */
	protected void onMyStart( ) {
		showLoadView( );
	}

	/**
	 * 请求成功之后调用此方法
	 * 
	 * @param requestCode
	 *        请求码1个界面多个请求区分作用
	 * @param jsonStr
	 *        json串
	 */
	protected abstract void onSuccess( int requestCode, String jsonStr );

	/**
	 * 请求失败会调用此方法，子类如需处理覆盖此方法
	 * 
	 * @param requestCode
	 */
	protected void onFailure( int requestCode ) {
		showToast("亲,网络好像不给力哦!");
//		MyLog.i("requestCode","requestCode:"+requestCode);
		 /*if (isShowLoading) {// 显示加载条的时候才会出现重新加载界面
		 failureView.setVisibility(View.VISIBLE);
		 }*/
	}

	/**
	 * 请求成功和失败都会调用此方法，子类如需处理覆盖此方法
	 * 
	 * @param requestCode
	 */
	protected void onFinish( int requestCode ) {
		hideLoadView( );
	}

	/**
	 * 对话框的提示信息
	 */
	private String tipMessage = "正在加载...";

	/**
	 * 初始化请求对话框
	 */
	private void initDialog( ) {
		iphoneDialog = new IphoneDialog( this );
	}

	/**
	 * 设置对话框上要显示的提示信息
	 * 
	 * @param tip
	 */
	protected void setDialogTip( String tip ) {
		if( iphoneDialog != null ) {
			iphoneDialog.setMessage( tip );
		}
	}

	/**
	 * 异步请求
	 */
	protected abstract void request( );

}
