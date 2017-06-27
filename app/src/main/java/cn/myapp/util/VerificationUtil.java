package cn.myapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationUtil {

	/**
	 * 是否为手机号
	 * @param number
	 * @return
	 */
	public static boolean isMoblie( String number ) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile( "^[1][3,4,5,7,8][0-9]{9}$" ); // 验证手机号
		m = p.matcher( number );
		b = m.matches( );
		return b;
	}
}
