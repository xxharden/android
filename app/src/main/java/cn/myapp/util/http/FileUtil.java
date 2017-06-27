package cn.myapp.util.http;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class FileUtil {
	private static int FILE_SIZE = 4 * 1024;
	private static String TAG = "FileUtil";

	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	public static boolean createPath(String path) {
		File f = new File(path);
		if (!f.exists()) {
			Boolean o = f.mkdirs();
			Log.i(TAG, "create dir:" + path + ":" + o.toString());
			return o;
		}
		return true;
	}

	public static boolean exists(String file) {
		return new File(file).exists();
	}

	public static File saveFile(String file, InputStream inputStream) {
		File f = null;
		OutputStream outSm = null;

		try {
			f = new File(file);
			String path = f.getParent();
			if (!createPath(path)) {
				Log.e(TAG, "can't create dir:" + path);
				return null;
			}

			if (!f.exists()) {
				f.createNewFile();
			}

			outSm = new FileOutputStream(f);
			byte[] buffer = new byte[FILE_SIZE];
			while ((inputStream.read(buffer)) != -1) {
				outSm.write(buffer);
			}
			outSm.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;

		} finally {
			try {
				if (outSm != null)
					outSm.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Log.v(TAG,
				"[FileUtil]save file:" + file + ":"
						+ Boolean.toString(f.exists()));

		return f;
	}

	public static Drawable getImageDrawable(String file) {
		if (!exists(file))
			return null;
		try {
			InputStream inp = new FileInputStream(new File(file));
			return BitmapDrawable.createFromStream(inp, "img");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static File getCacheFile(String imageUri) {
		File cacheFile = null;
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File sdCardDir = Environment.getExternalStorageDirectory();
				String fileName = getFileName(imageUri);
				File dir = new File(sdCardDir.getCanonicalPath()
						+ AsyncImageLoader.CACHE_DIR);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				cacheFile = new File(dir, fileName);
				Log.i(TAG, "exists:" + cacheFile.exists() + ",dir:" + dir
						+ ",file:" + fileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "getCacheFileError:" + e.getMessage());
		}

		return cacheFile;
	}

	public static String getFileName(String path) {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}

	/**
	 * 获取文件的大小
	 * 
	 * @param fileSize
	 *            文件的大小
	 * @return
	 */
	public static String FormetFileSize(int fileSize) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}
}