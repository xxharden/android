package com.gqh.mystudio.utill;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;

/**
 * Created by 红哥哥 on 2016/7/21.
 * /**
 * 震动帮助类
 * androidManifest.xml中加入 以下权限
 * <uses-permission android:name="android.permission.VIBRATE" />
*/
public class VibrateHelp {
    private static Vibrator vibrator;

    /**
     * 简单震动
     * @param context     调用震动的Context
     * @param millisecond 震动的时间，毫秒
     */
    @SuppressWarnings("static-access")
    public static void vSimple(Context context, int millisecond) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(millisecond);
    }

    /**
     * 复杂的震动
     * @param context 调用震动的Context
     * @param pattern 震动形式
     * @param repeate 震动的次数，-1不重复，非-1为从pattern的指定下标开始重复
     */
    @SuppressWarnings("static-access")
    public static void vComplicated(Context context, long[] pattern, int repeate) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeate);
    }

    /**
     * 停止震动
     */
    public static void stop() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    public static class ViewClickVibrate implements View.OnClickListener {
        /** 按钮震动时间 */
        private final int VIBRATE_TIME = 60;


        @Override
        public void onClick(View v) {
            // TODO 根据设置中的标记判断是否执行震动
            VibrateHelp.vSimple(v.getContext(), VIBRATE_TIME);
        }
    }
}
