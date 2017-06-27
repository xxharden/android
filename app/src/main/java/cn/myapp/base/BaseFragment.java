package cn.myapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.gqh.mystudio.application.BamsApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseFragment extends Fragment {

	protected LayoutInflater inflater;
	private InputMethodManager imm;
	protected Activity context;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		context = this.getActivity( );
		inflater = LayoutInflater.from( this.getActivity( ) );
		imm = (InputMethodManager) getActivity( ).getSystemService( Context.INPUT_METHOD_SERVICE );
	}

	/**
	 * 隐藏软键盘
	 */
	protected void hideKeyboard( ) {
		if( context.getWindow( ).getAttributes( ).softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN ) {
			if( context.getCurrentFocus( ) != null )
				imm.hideSoftInputFromWindow( context.getCurrentFocus( ).getWindowToken( ), InputMethodManager.HIDE_NOT_ALWAYS );
		}
	}

	/**
	 * 显示软键盘
	 * 
	 * @param
	 */
	protected void showKeyboard( ) {
		if( context.getWindow( ).getAttributes( ).softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE ) {
			if( context.getCurrentFocus( ) != null )
				imm.hideSoftInputFromWindow( context.getCurrentFocus( ).getWindowToken( ), InputMethodManager.SHOW_FORCED );
		}
	}

	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param text
	 *        文本
	 */
	public void showToast( String text ) {
		Toast.makeText( this.getActivity( ), text, Toast.LENGTH_SHORT ).show( );
	}

	/**
	 * 描述：Toast提示文本.
	 * 
	 * @param mesageId
	 *        文本id
	 */
	public void showToast( int mesageId ) {
		if( isAdded( ) ) {
			Toast.makeText( this.getActivity( ), getString( mesageId ), Toast.LENGTH_SHORT ).show( );
		}
	}

	/**
	 * 加载图片，默认图片是头像的
	 * 
	 * @param imageView
	 * @param imageUrl
	 */
	protected void loadImage( final ImageView imageView, final String imageUrl ) {
		ImageLoader.getInstance().displayImage( imageUrl, imageView, BamsApplication.getInstance().options );
	}

	public void toActivity(Intent intent){
		startActivity(intent);
	}
	public void toActivity(Class<?> clazz){
		toActivity(new Intent(getActivity(), clazz));
	}

}
