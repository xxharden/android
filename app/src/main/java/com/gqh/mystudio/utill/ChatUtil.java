package com.gqh.mystudio.utill;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ChatUtil {
	public static final String TAG_TEXT = "<text>";
	public static final String TAG_FACE = "<face>";

	public static final int TYPE_TEXT = 1;
	public static final int TYPE_FACE = 2;



	public static String addFaceTag(String faceFileName) {
		return TAG_FACE + faceFileName;
	}

	public static int getType(String body) {
		if (body.startsWith(TAG_FACE)) {
			return TYPE_FACE;
		} else {
			return TYPE_TEXT;
		}


	}

	public static String getFaceFileName(String body) {
		int index = TAG_FACE.length();
		return body.substring(index);
	}

}
