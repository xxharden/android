package cn.myapp.entity;


import com.gqh.mystudio.application.BamsApplication;

import cn.myapp.util.SharedPreferencesHelper;

public class UserInfo {
	
	public static void setLog(String log) {
		SharedPreferencesHelper.setString(BamsApplication.getInstance(),
				"log", log);
	}

	public static String getLog() {
		return SharedPreferencesHelper.getStringValue(
				BamsApplication.getInstance(), "log");
	}
	
	public static void setStatus(String status) {
		SharedPreferencesHelper.setString(BamsApplication.getInstance(),
				"status", status);
	}

	public static String getStatus() {
		return SharedPreferencesHelper.getStringValue(
				BamsApplication.getInstance(), "status");
	}
	
	public static void setAccount(String acconunt) {
		SharedPreferencesHelper.setString(BamsApplication.getInstance(),
				"acconunt", acconunt);
	}

	public static String getAccount() {
		return SharedPreferencesHelper.getStringValue(
				BamsApplication.getInstance(), "acconunt");
	}

	public static void setPassword(String password) {
		SharedPreferencesHelper.setString(BamsApplication.getInstance(),
				"password", password);
	}

	public static String getPassword() {
		return SharedPreferencesHelper.getStringValue(
				BamsApplication.getInstance(), "password");
	}

	public static void setRemember(boolean remember) {
		SharedPreferencesHelper.setBoolean(BamsApplication.getInstance(),
				"remember", remember);
	}

	public static boolean isRemember() {
		return SharedPreferencesHelper.getBoolean(
				BamsApplication.getInstance(), "remember",true);
	}

	public static void setAutoLogin(boolean autologin) {
		SharedPreferencesHelper.setBoolean(BamsApplication.getInstance(),
				"autologin", autologin);
	}

	public static boolean isAutoLogin() {
		return SharedPreferencesHelper.getBoolean(
				BamsApplication.getInstance(), "autologin");
	}
}
