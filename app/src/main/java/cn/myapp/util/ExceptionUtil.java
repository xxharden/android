package cn.myapp.util;

import com.gqh.mystudio.application.BamsApplication;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {
	public static void handleException(Exception e){
		if(BamsApplication.isRelease){
			//收集异常信息
			StringWriter stringWriter=new StringWriter();
			PrintWriter printWriter=new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String info2=stringWriter.toString();

			//发送给开发人员
			//1.http方式
			//2.发邮件
		}else{
			e.printStackTrace();
		}
	}
}
