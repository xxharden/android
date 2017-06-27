package cn.myapp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class StreamUtil {
	private static final String TAG = "StreamUtil";

	public static void Release(InputStream in) {
		release(in, null);
	}

	public static void release(OutputStream out) {
		release(null, out);
	}

	public static void release(InputStream in, OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				// Logger.e(TAG, e);
			}
			out = null;
		}
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				// Logger.e(TAG, e);
			}
			in = null;
		}
	}

	public static void release(Reader reader) {
		release(reader, null);
	}

	public static void release(Writer writer) {
		release(null, writer);
	}

	public static void release(Reader reader, Writer writer) {
		if (reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				// Logger.e(TAG, e);
			}
			reader = null;
		}
		if (writer != null) {
			try {
				writer.close();
			} catch (Exception e) {
				// Logger.e(TAG, e);
			}
			writer = null;
		}
	}

	public static String StreamToString(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} finally {
			isr.close();
		}
		return sb.toString();
	}

	public static void copy(String src, String target) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(target);
		try {
			bufferedInToOutStream(in, out);
		} finally {
			release(in, out);
		}
	}

	public static void inputStreamToOutputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[81920];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.flush();
	}

	public static void bufferedInToOutStream(InputStream in, OutputStream out) throws IOException {
		InputStream nin = new BufferedInputStream(in, 8192);
		OutputStream nout = new BufferedOutputStream(out, 8192);
		inputStreamToOutputStream(nin, nout);
	}
}
