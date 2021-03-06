/**
 * Generated by SFJ 1.0.0.558
 * Copyright (C) 2014 imoco.org
 */
package cn.myapp.util.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.concurrent.Future;


public abstract class Task {
	public static final int TASK_CANCEL = 0x5;
	public static final int TASK_END = 0x3;
	public static final int TASK_FAILED = 0x4;
	public static final int TASK_PROGRESS = 0x2;
	public static final int TASK_START = 0x1;
	public HttpCallBack mCallback;
	private Future<?> mFuture;
	public HttpHandler mainHandler;
	public String loadingMsg = "";

	public ProgressDialog progressDialog;
	public Context mContext;

	public abstract void runTask();

	class HttpHandler extends Handler {

		public void handleMessage(Message msg) {
			if (mCallback != null) {
				switch (msg.what) {
				case 1: {
					// mCallback.onRequestStart();
					closeLoading();
					break;
				}
				case 5: {
					// mCallback.onRequestCanceled();
					closeLoading();
					break;
				}
				case 3: {
					mCallback.onRequestEnd(UnicodeUtil
							.decodeUnicode((String) msg.obj));
					mFuture = null;

					closeLoading();
					break;
				}
				case 4: {
					mCallback.onRequestFailed();
					mFuture = null;

					closeLoading();
					break;
				}
				case 2: {
					// mCallback.onProgress((Integer) msg.obj);

					closeLoading();
					break;
				}
				case 6: {
					showProgress();
					break;
				}
				case 7: {
					Toast.makeText(mContext, "网络连接失败，请确认网络连接",
							Toast.LENGTH_SHORT).show();
					break;
				}
				}
			}
		}
	}

	class FetcherThread implements Runnable {

		public void run() {
			runTask();
		}
	}

	public void load() {
		if (mFuture == null) {
			mFuture = HttpManager.getInstance().getHttpService()
					.submit(new FetcherThread());
		}
	}

	public void cancel() {
		if (!isCancelled()) {
			if (mFuture != null) {
				mFuture.cancel(false);
			}
			// if (mCallback != null) {
			// mCallback.onRequestCanceled();
			// }
		}
	}

	public final boolean isCancelled() {
		if (mFuture == null) {
			return true;
		}
		return mFuture.isCancelled();
	}

	public void initLoading() {

		if (mCallback == null) {
			return;
		}
		if ("".equals(loadingMsg)) {
			return;
		}

		if (progressDialog == null) {
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setTitle("");
			progressDialog.setMessage(loadingMsg);
			progressDialog.setIndeterminate(true);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setCancelable(false);

			progressDialog.setOnKeyListener(new OnKeyListener() {

				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK
							&& event.getAction() == KeyEvent.ACTION_UP) {
						dialog.dismiss();
						cancel();
					}
					return false;
				}
			});

		}

	}

	private void showProgress() {
		if (progressDialog != null && !progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	private void closeLoading() {
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
