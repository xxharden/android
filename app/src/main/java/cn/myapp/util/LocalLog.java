package cn.myapp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalLog {

	public static final SimpleDateFormat yearFormat = new SimpleDateFormat(
			"yyyy年M月d日 HH:mm");

	public static void write(String str) {
		// 写入文件中
		FileOutputStream fos = null;
		try {
			// 文件路径与文件名称
			File file = new File(FileUtil.getPathDir(), "hangxin.txt");
			if (!file.exists()) {// 判断此文件或者目录是否存在
				file.createNewFile();
			}
			fos = new FileOutputStream(file, true);
			str = yearFormat.format(new Date()) + ":" + str + "\n";
			fos.write(str.getBytes());// 记录错误信息
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			StreamUtil.release(fos);
		}
	}
}
