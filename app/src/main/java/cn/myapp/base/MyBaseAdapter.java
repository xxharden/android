package cn.myapp.base;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gqh.mystudio.application.BamsApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyang
 * @param <E>
 */
public abstract class MyBaseAdapter<E> extends BaseAdapter {

	public List<E> mList = new ArrayList<E>( );
	protected LayoutInflater mInflater;
	protected Context context;
	protected Resources mResources;
	public DisplayImageOptions options = BamsApplication.getInstance().options;


	private void init( Context context ) {
		this.context = context;
		mInflater = LayoutInflater.from( context );
		mResources = context.getResources( );
	}

	public MyBaseAdapter( List<E> mList, Context context ) {
		init( context );
		this.mList = mList;
	}


	public void remove( int position ) {
		if( position >= 0 && position < mList.size( ) ) {
			mList.remove( position );
			notifyDataSetChanged( );
		}
	}

	@Override
	public int getCount( ) {
		return mList.size( );
	}

	@Override
	public E getItem( int position ) {
		return mList.get( position );
	}

	@Override
	public long getItemId( int position ) {
		return position;
	}

	public void removeAll( ) {
		mList.clear( );
		notifyDataSetChanged( );
	}

	public void replaceList( final List<E> list ) {
		mList = list;
		notifyDataSetChanged( );
	}

	public List<E> getList( ) {
		return mList;
	}

	public void addToListHead( final List<E> pItemList ) {
		if( pItemList == null || pItemList.size( ) <= 0 ) {
			return;
		}
		for( int i = pItemList.size( ) - 1; i >= 0; i-- ) {
			mList.add( 0, pItemList.get( i ) );
		}
		notifyDataSetChanged( );
	}

	public void addToListHead( final E pItem ) {
		mList.add( 0, pItem );
		notifyDataSetChanged( );
	}

	public void addToListBack( final List<E> list ) {
		if( list == null || list.size( ) <= 0 ) {
			return;
		}
		mList.addAll( list );
		notifyDataSetChanged( );
	}

	public void addToListBack( final E e ) {
		mList.add( e );
		notifyDataSetChanged( );
	}
	@Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
            if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }
	
	/**
	 * 加载图片，默认图片是头像的
	 * @param imageView
	 * @param imageUrl
	 */
	protected void loadImage( final ImageView imageView, final String imageUrl) {
		ImageLoader.getInstance().displayImage( imageUrl, imageView, options );
	}
	/**
   	 * 加载图片
   	 * @param imageView
   	 * @param imageUrl
   	 * @param imageId 默认图片的id
   	 */
	protected void loadImageCustom( final ImageView imageView, final String imageUrl,final int imageId) {
		DisplayImageOptions options = new DisplayImageOptions.Builder( ).showImageOnLoading(imageId).showImageForEmptyUri(imageId)
				.showImageOnFail(imageId).cacheInMemory( true ).imageScaleType( ImageScaleType.EXACTLY )
				.bitmapConfig( Bitmap.Config.RGB_565 ).build( );
		ImageLoader.getInstance().displayImage( imageUrl, imageView, options );
	}

}
