package com.gqh.mystudio.application;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.gqh.mystudio.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.myapp.entity.User;
import cn.myapp.util.ExceptionUtil;
import cn.myapp.util.SharePreferenceUtil;
/**
 * @author : 红仔
 * @date : 2016/2/17
 * desc: application
 * 请在AndroidManifest.xml中application标签下android:name中指定该类
 */
public class BamsApplication extends Application {

	public static int networkType;
	public static int NETWORKTYPE_NONE = 1;
	public static int NETWORKTYPE_WIFI = 2;
	public static int NETWORKTYPE_MOBILE = 3;

	public static boolean isRelease=false;
	/**
	 * 单例对象
	 */
	private static BamsApplication instance=new BamsApplication();

	private BamsApplication(){}
	/**
	 * 获取单例入口
	 *
	 * @return 单例
	 */
	public static BamsApplication getInstance( ) {
		if (null == instance){
			instance = new BamsApplication();
		}
		return instance;
	}
	/**
	 * Activity列表
	 */
	public static ArrayList<Activity> listActivity=new ArrayList<Activity>();
	/**
	 * 保存Activity到现有列表中
	 * @param activity
	 */
	public void addActivity(Activity activity){
		listActivity.add(activity);
	}
	/**
	 * 关闭保存的Activity
	 */
	//退出
	public static void exit(){
		//1.结束掉所有activity
		for(Activity activity:listActivity){
			try {
				activity.finish();
			} catch (Exception e) {
				ExceptionUtil.handleException(e);
			}
		}
		//结束进程,凡是非零都表示异常退出!0表示正常退出!
		System.exit(0);
	}

	public DisplayImageOptions options;
	private List<User> mUserList;
	private MediaPlayer mMediaPlayer;
	private NotificationManager mNotificationManager;
	private LocalBroadcastManager lbm;
	@Override
	public void onCreate() {
		super.onCreate();
		// 必须setDebugMode() 和 init()
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
//		//初始化 apistoreSdk
//		ApiStoreSDK.init(this, "f999003df3a62315f33ad5bdf0d8ec92");

		instance = this;

		lbm = LocalBroadcastManager.getInstance(this);

		initImageLoader();

		initConfig();

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.zhanweitu)
				.showImageOnFail(R.drawable.zhanweitu)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.build();
	}

	/** 初始化ImageLoader */
	public void initImageLoader() {
		// 创建默认的ImageLoader配置参数
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(480, 800) // default = device screen dimensions内存缓存文件的最大长宽
//				.discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null) // 本地缓存的详细信息(缓存的最大长宽)
				.threadPoolSize(3) // default
				.threadPriority(Thread.NORM_PRIORITY - 1) // default
				.tasksProcessingOrder(QueueProcessingType.FIFO) // default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
				.memoryCacheSizePercentage(13) // default
				.discCache(new UnlimitedDiscCache(getCacheDir())) // default  // default 可以自定义缓存路径
				.discCacheSize(50 * 1024 * 1024)   // 50 Mb sd卡(本地)缓存的最大值
				.discCacheFileCount(100)
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(this)) // default
				.defaultDisplayImageOptions(options)
//				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs()
				.build();
		ImageLoader.getInstance().init(configuration);
	}



	public void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "topnews/Cache");//获取到缓存的目录地址
		Log.d("cacheDir", cacheDir.getPath());
		//创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(context)
				//.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
				//.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
				.threadPoolSize(3)//线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
						//.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
						//.memoryCacheSize(2 * 1024 * 1024)
						///.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
						//.discCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
						//.discCacheFileCount(100) //缓存的File数量
				.discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
						//.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
						//.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.

	}

	private void initConfig( ) {
		mSpUtil = new SharePreferenceUtil( this, SP_FILE_NAME );

//		mMsgDB = new MessageDB( this );
//		mRecentDB = new RecentDB( this );
//		mMediaPlayer = MediaPlayer.create( this, R.raw.office );
		mNotificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );

	}


	private String PHPSESSID = "";
	public String getPHPSESSID( ) {
		return PHPSESSID;
	}
	public void setPHPSESSID( String pHPSESSID ) {
		PHPSESSID = pHPSESSID;
	}

	public static final String SP_FILE_NAME = "bams_push_msg_sp";
	private SharePreferenceUtil mSpUtil = null;
	public synchronized SharePreferenceUtil getSpUtil( ) {
		if( mSpUtil == null )
			mSpUtil = new SharePreferenceUtil( this, SP_FILE_NAME );
		return mSpUtil;
	}
	/**
	 * 存储引导值
	 *
	 * @param key
	 * @param value
	 */
	public void addKeyValue( String key, String value ) {
		mSpUtil.addKeyValue( key, value );
	}

	/**
	 * 根据key取值
	 *
	 * @param key
	 * @return
	 */
	public String getKeyValue( String key ) {
		return mSpUtil.getKeyValue( key );
	}

	/**
	 * 存储引导值
	 *
	 * @param key
	 * @param value
	 */
	public void addKeyValue( String key, boolean value ) {
		mSpUtil.addKeyValue( key, value );
	}
	/**
	 * 根据key取值
	 *
	 * @param key
	 * @return
	 */
	public boolean getKeyValue( String key,boolean def ) {
		return mSpUtil.getKeyValue( key ,def);
	}


	//全局用户账号
	private User user;
	public void setUser(User user){
		this.user = user;
	}
	public User getUser(){
		return user;
	}

	//全局用户状态
	private String userStyle;
	public void setUserStyle(String userStyle){
		this.userStyle = userStyle;
	}
	public String getUserStyle(){
		return userStyle;
	}


	//全局mainactivity状态
	private int backIndex;
	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}





}
