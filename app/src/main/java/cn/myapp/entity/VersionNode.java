package cn.myapp.entity;

/**
 * 版本信息
 * 
 * @author moco
 * 
 */
public class VersionNode {
	private long id;

	/**
	 * 版本编号
	 */
	private String vcode = "";

	/**
	 * 下载地址
	 */
	private String vurl = "";

	/**
	 * 修改日期
	 */
	private String vdate = "";

	/**
	 * 版本更新信息说明
	 */
	private String verimprint = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getVurl() {
		return vurl;
	}

	public void setVurl(String vurl) {
		this.vurl = vurl;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getVerimprint() {
		return verimprint;
	}

	public void setVerimprint(String verimprint) {
		this.verimprint = verimprint;
	}

}
