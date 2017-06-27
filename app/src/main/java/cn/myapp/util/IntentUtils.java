package cn.myapp.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

import java.io.File;

/**
 * Created by ren on 2015/7/30 0030.
 */
public class IntentUtils {

    /**
     * 跳转到activity
     * @param context
     * @param clazz
     */
    public static void toActivity(Context context, Class<?> clazz) {
        context.startActivity(new Intent(context,clazz));
    }
    /**
     * 跳转到activity后，finish自己
     * @param context
     * @param clazz
     */
    public static void toActivityAfterFinishThis(Context context, Class<?> clazz) {
        context.startActivity(new Intent(context,clazz));
        ((FragmentActivity)context).finish();
    }
    public static void toActivityAfterFinishThis(Context context, Intent intent) {
        context.startActivity(intent);
        ((FragmentActivity)context).finish();
    }

    /**
     * 根据intent跳转到相应的activity,intent里面可以传递参数
     * @param context
     * @param intent
     */
    public static void toActivity(Context context,Intent intent) {
        context.startActivity(intent);
    }

    public static void toActivityForResut(Context context, Intent intent, int requestCode) {
        ((FragmentActivity) context).startActivityForResult(intent, requestCode);
    }

    public static Intent getChoiceImageIntent() {
        Intent intent = new Intent();
        // 设置文件类型
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return intent;
    }

    public static Intent getCropPhoto(Uri source, String dis) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(source, "image/*");
        intent.putExtra("crop", "true");
       /* intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);*/
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(dis)));
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }
    public static Intent getCropPhotoForPhoto(Uri source, String dis) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(source, "image/*");
        intent.putExtra("crop", "true");
       /* intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);*/
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(dis)));
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }

    public static Intent getTakePictureIntent(String dis) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(dis)));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        return intent;
    }
}
