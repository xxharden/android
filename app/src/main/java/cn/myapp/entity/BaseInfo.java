package cn.myapp.entity;

import java.util.List;

public class BaseInfo {

	/**
	 * 错误码(非0)<br>
	 * rrorCode标签,非0表现出现错误。 为0时表示处理成功，并可获取相应的返回对象信息
	 */
	private String errorCode;

	/**
	 * 错误信息
	 */
	private String errorMsg;

	/**
	 * 当前第几页
	 */
	private int currPage;

	/**
	 * 总页数
	 */
	private int pageSize;


	private List<TenderNode> tenderNodes;
	/**
	 *竞争信息
	 */

	


	public List<TenderNode> getTenderNodes() {
		return tenderNodes;
	}

	public void setTenderNodes(List<TenderNode> tenderNodes) {
		this.tenderNodes = tenderNodes;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
