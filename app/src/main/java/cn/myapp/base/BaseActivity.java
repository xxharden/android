package cn.myapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.gqh.mystudio.application.BamsApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Stack;

import cn.myapp.util.ExceptionUtil;
import retrofit2.Call;
import retrofit2.Callback;


public abstract class BaseActivity extends FragmentActivity {

	protected LayoutInflater inflater;
	private InputMethodManager imm;
	protected  BaseActivity context;
	private Stack<Call> calls = new Stack<>();

	@Override
	protected void onCreate( Bundle arg0 ) {
		super.onCreate( arg0 );
//		SDKInitializer.initialize(getApplicationContext());//百度sdk
		initParameter( );
		try {
			//this 调用这个方法的对象（类型可以是当前类或子类）
			BamsApplication.getInstance().addActivity(this);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
	}


	private void initParameter( ) {
		context = this;
		inflater = LayoutInflater.from( this );
		imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
	}

	
	/**
	 * 隐藏软键盘
	 */
	protected void hideKeyboard() {
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	/**
	 * 显示软键盘
	 * @param view
	 */
	protected void showKeyboard() {
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
			if (getCurrentFocus() != null)
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
		}
	}


	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param text
	 *        文本
	 */
	public void showToast( String text ) {
		Toast.makeText( this, text, Toast.LENGTH_SHORT ).show( );
	}

	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param mesageId
	 *        文本id
	 */
	public void showToast( int mesageId ) {
		Toast.makeText(this, getString(mesageId), Toast.LENGTH_SHORT).show();
	}
	/**
	 * 加载图片，默认图片是头像的
	 * @param imageView
	 * @param imageUrl
	 */
	protected void loadImage( final ImageView imageView, final String imageUrl) {
		ImageLoader.getInstance().displayImage(imageUrl, imageView, BamsApplication.getInstance().options);
	}

	public void toActivity(Intent intent){
		startActivity(intent);
	}
	public void toActivity(Class<?> clazz){
		toActivity(new Intent(this, clazz));
	}
	public void toActivityAfterFinishThis(Class<?> clazz){
		toActivity(clazz);
		this.finish();
	}

	public <T> T requestNoloading(Call<T> call, Callback<T> callback) {
		calls.add(call);
		call.enqueue(callback);
		return null;
	}

}
