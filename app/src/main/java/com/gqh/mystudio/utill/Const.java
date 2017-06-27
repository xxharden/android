package com.gqh.mystudio.utill;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.widget.ImageView;

public class Const {

	public static String savePicToSdcard(Bitmap bitmap, String path,
										 String fileName) {
		String filePath = "";
		if (bitmap == null) {
			return filePath;
		} else {

			filePath=path+ fileName;
			File destFile = new File(filePath);
			OutputStream os = null;
			try {
				os = new FileOutputStream(destFile);
				bitmap.compress(CompressFormat.JPEG, 100, os);
				os.flush();
				os.close();
			} catch (IOException e) {
				filePath = "";
			}
		}
		return filePath;
	}


	public static void setImage(Context context,Uri mImageCaptureUri,ImageView photo) {

		// 不管是拍照还是选择图片每张图片都有在数据中存储也存储有对应旋转角度orientation值
		// 所以我们在取出图片是把角度值取出以便能正确的显示图片,没有旋转时的效果观看

		ContentResolver cr = context.getContentResolver();
		Cursor cursor = cr.query(mImageCaptureUri, null, null, null, null);// 根据Uri从数据库中找
		if (cursor != null) {
			cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
			String filePath = cursor.getString(cursor.getColumnIndex("_data"));// 获取图片路
			String orientation = cursor.getString(cursor
					.getColumnIndex("orientation"));// 获取旋转的角度
			cursor.close();
			if (filePath != null) {
				Bitmap bitmap = BitmapFactory.decodeFile(filePath);//根据Path读取资源图片
				int angle = 0;
				if (orientation != null && !"".equals(orientation)) {
					angle = Integer.parseInt(orientation);
				}
				if (angle != 0) {
					// 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
					Matrix m = new Matrix();
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					m.setRotate(angle); // 旋转angle度
					bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
							m, true);// 从新生成图片

				}
				photo.setImageBitmap(bitmap);
			}
		}
	}

	//压缩图片  10倍
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}


//	public static boolean isValidDate(String str) {
//		boolean convertSuccess=true;
//		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
//		try {
//			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//			format.setLenient(false);
//			format.parse(str);
//		} catch (Exception e) {
//			// e.printStackTrace();
//			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
//			convertSuccess=false;
//		}
//		return convertSuccess;
//	}


}
