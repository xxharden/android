package cn.myapp.entity;

import java.io.Serializable;

public class HttpResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误消息
	 */
	public String message="服务器错误";
	/**
	 * 状态码 0 成功
	 */
	public String code;

	
	public int currPage;
	public int pageSize;
	
	public boolean isSuccess() {
		return code.equals("0");
	}
	public int co;
}
