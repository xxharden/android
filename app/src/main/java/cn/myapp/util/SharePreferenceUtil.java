package cn.myapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {

	public static final String MESSAGE_NOTIFY_KEY = "message_notify";
	public static final String MESSAGE_SOUND_KEY = "message_sound";
	public static final String SHOW_HEAD_KEY = "show_head";
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	// appid
	public void setAppId(String appid) {
		editor.putString("appid", appid);
		editor.commit();
	}

	public String getAppId() {
		return sp.getString("appid", "");
	}

	// user_id
	public void setUserId(String userId) {
		editor.putString("userId", userId);
		editor.commit();
	}

	public String getUserId() {
		return sp.getString("userId", "");
	}

	// channel_id
	public void setChannelId(String ChannelId) {
		editor.putString("ChannelId", ChannelId);
		editor.commit();
	}

	public String getChannelId() {
		return sp.getString("ChannelId", "");
	}

	// nick
	public void setNick(String nick) {
		editor.putString("nick", nick);
		editor.commit();
	}

	public String getNick() {
		return sp.getString("nick", "北京移动网格管理");
	}

	// 头像图标
	public int getHeadIcon() {
		return sp.getInt("headIcon", 0);
	}

	public void setHeadIcon(int icon) {
		editor.putInt("headIcon", icon);
		editor.commit();
	}

	// 设置Tag
	public void setTag(String tag) {
		editor.putString("tag", tag);
		editor.commit();
	}

	public String getTag() {
		return sp.getString("tag", "");
	}

	// 是否通知
	public boolean getMsgNotify() {
		return sp.getBoolean(MESSAGE_NOTIFY_KEY, false);
	}

	public void setMsgNotify(boolean isChecked) {
		editor.putBoolean(MESSAGE_NOTIFY_KEY, isChecked);
		editor.commit();
	}

	// 是否有声音
	public boolean getMsgSound() {
		return sp.getBoolean(MESSAGE_SOUND_KEY, true);
	}

	public void setMsgSound(boolean isChecked) {
		editor.putBoolean(MESSAGE_SOUND_KEY, isChecked);
		editor.commit();
	}

	// 是否显示自己头像
	public boolean getShowHead() {
		return sp.getBoolean(SHOW_HEAD_KEY, true);
	}

	public void setShowHead(boolean isChecked) {
		editor.putBoolean(SHOW_HEAD_KEY, isChecked);
		editor.commit();
	}

	// 表情翻页效果
	public int getFaceEffect() {
		return sp.getInt("face_effects", 3);
	}

	public void setFaceEffect(int effect) {
		if (effect < 0 || effect > 11)
			effect = 3;
		editor.putInt("face_effects", effect);
		editor.commit();
	}

	/**
	 * 存储引导值
	 * 
	 * @param key
	 * @param value
	 */
	public void addKeyValue(String key, String value) {
		// 存入数据
		editor.putString(key, value);
		editor.commit();
	}
	
	
	/**
	 * 存储引导值
	 * 
	 * @param key
	 * @param value
	 */
	public void addKeyValue(String key, boolean value) {
		// 存入数据
		editor.putBoolean(key, value);
		editor.commit();
	}

	
	/**
	 * 根据key取值
	 * 
	 * @param key
	 * @return
	 */
	public boolean getKeyValue(String key ,boolean def) {
		return sp.getBoolean(key, def);
	}
	/**
	 * 根据key取值
	 * 
	 * @param key
	 * @return
	 */
	public String getKeyValue(String key) {
		return sp.getString(key, "");
	}

}
