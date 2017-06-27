package com.gqh.mystudio.utill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.myapp.util.Constant;
import cn.myapp.util.ExceptionUtil;
import http.Base64;

/**
 * @author yanzi
 *关于Bitmap与byte[] Drawable互相转换
 *
 * 2.Bitmap---->Drawable

Drawable drawable =new BitmapDrawable(bmp);
 */
public class BitmapUtil {
	
	/**
	 * @param drawable
	 * drawable 转  Bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}
	
	/**
	 * @param bitmap
	 * @param roundPx
	 * 获取圆角图片
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

        /**
         * bitmap --》Base64
         * @param bitmap
         * @return
         */
        public static String bitmapToBase64(Bitmap bitmap){
                String result=null;
                byte[] bitmapBytes = new byte[0];
                ByteArrayOutputStream baos=null;
                try {

                        if (bitmap!=null){
                                baos=new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                                baos.flush();
                                baos.close();
                                bitmapBytes=baos.toByteArray();
                                result= Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
                        }
                }catch (Exception e){
                        ExceptionUtil.handleException(e);
                }finally {
                        try{
                                if (baos!=null){
                                        baos.flush();
                                        baos.close();
                                }
                        }catch (Exception e){
                                ExceptionUtil.handleException(e);
                        }
                }
                return result;
        }


//        Bitmap对象保存味图片文件
        public static File saveBitmapFile(Bitmap bitmap){
                File file=new File("/mnt/sdcard/01.jpg");//将要保存图片的路径
                try {
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        bos.flush();
                        bos.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return file;
        }

        public static void startPhotoZoom(Context context,Uri uri, int size) {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                // crop为true是设置在开启的intent中设置显示的view可以剪裁
                intent.putExtra("crop", "true");

                // aspectX aspectY 是宽高的比例
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);

                // outputX,outputY 是剪裁图片的宽高
                intent.putExtra("outputX", size);
                intent.putExtra("outputY", size);
                intent.putExtra("return-data", true);

                ((Activity)context).startActivityForResult(intent, Constant.PHOTO_REQUEST_CUT);
        }

        // 使用系统当前日期加以调整作为照片的名称
        public static String getPhotoFileName() {
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
                return dateFormat.format(date) + ".jpg";
        }
}
