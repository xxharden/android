package cn.myapp.util;

import android.os.Environment;


import com.gqh.mystudio.application.BamsApplication;

import java.io.File;

public class FileUtil {

	
	public static String APPDIR = "bams_";
	/**
	 * 应用缓存根目录
	 * 
	 * @return
	 */
	public static File getCacheDir( ) {
		String paht = getPathDir( ) + File.separator +APPDIR;
		File file = new File( paht );
		if( !file.exists( ) ) {
			file.mkdirs( );
		}
		return file;
	}

	/**
	 * 返回根目录，如果有SD卡就返回SD卡的根目录，如果没有就返回应用的的缓存目录
	 * 
	 * @return
	 */
	public static String getPathDir( ) {
		if( isCanUseSD( ) ) {
			return Environment.getExternalStorageDirectory( ).getAbsolutePath( );
		} else {
			return BamsApplication.getInstance().getFilesDir( ).getAbsolutePath( );
		}
	}
	

	/**
	 * 描述：SD卡是否能用.
	 * 
	 * @return true 可用,false不可用
	 */
	public static boolean isCanUseSD( ) {
		try {
			return Environment.getExternalStorageState( ).equals( Environment.MEDIA_MOUNTED );
		} catch( Exception e ) {
			e.printStackTrace( );
		}
		return false;
	}
	
	public static String FILES = "files_";
	/**
	 * 文件的缓存目录
	 * 
	 * @return
	 */
	public static String getFilesCacheDir( ) {
		String paht = getCacheDir( ) + File.separator + FILES;
		File file = new File( paht );
		if( !file.exists( ) ) {
			file.mkdirs( );
		}
		return file.getAbsolutePath( );
	}
}
