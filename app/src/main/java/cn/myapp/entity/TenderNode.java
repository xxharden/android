package cn.myapp.entity;

/**
 * 招标信息
 * 
 * @author SFJ
 * 
 */
public class TenderNode {

	/**
	 * 招标ID
	 */
	private String tenderid;

	/**
	 * 招标编号
	 */
	private String tenderno = "";

	/**
	 * 招标内容
	 */
	private String contents = "";

	/**
	 * 分类
	 */
	private String category = "";

	/**
	 * 采购人
	 */
	private String purchaser = "";

	/**
	 * 项目资金
	 */
	private String project_funds = "";

	/**
	 * 领取文件时间
	 */
	private String receive_file_time = "";

	/**
	 * 领取文件地点
	 */
	private String receive_file_place = "";

	/**
	 * 投标开标时间
	 */
	private String start_time = "";

	/**
	 * 投标截止时间
	 */
	private String end_time = "";

	/**
	 * 信息源
	 */
	private String info_source = "";

	/**
	 * 联系电话
	 */
	private String tel_phone = "";

	/**
	 * 中心
	 */
	private String zhongxin = "";
	
	/**
	 * 片区主管
	 */
	private String pqzhuguan ="";
	
	/**
	 * 接口人
	 */
	private String jiekouren ="";

	/**
	 * 跟进人
	 */
	private String follow_people = "";

	/**
	 * 跟进状态
	 */
	private String follow_status = "";

	/**
	 * 跟进日志
	 */
	private String follow_logs = "";

	/**
	 * 商机名称
	 */
	private String shangji_name = "";

	/**
	 * 推进难点及需要配合资源
	 */
	private String ready_resource = "";

	public String getZhongxin() {
		return zhongxin;
	}

	public void setZhongxin(String zhongxin) {
		this.zhongxin = zhongxin;
	}

	public String getPqzhuguan() {
		return pqzhuguan;
	}

	public void setPqzhuguan(String pqzhuguan) {
		this.pqzhuguan = pqzhuguan;
	}

	public String getJiekouren() {
		return jiekouren;
	}

	public void setJiekouren(String jiekouren) {
		this.jiekouren = jiekouren;
	}

	/**
	 * 终止原因
	 */
	private String stop_cause = "";

	/**
	 * 收集时间
	 */
	private String acquisition_time = "";

	public String getTenderid() {
		return tenderid;
	}

	public void setTenderid(String tenderid) {
		this.tenderid = tenderid;
	}

	public String getTenderno() {
		return tenderno;
	}

	public void setTenderno(String tenderno) {
		this.tenderno = tenderno;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getProject_funds() {
		return project_funds;
	}

	public void setProject_funds(String project_funds) {
		this.project_funds = project_funds;
	}

	public String getReceive_file_time() {
		return receive_file_time;
	}

	public void setReceive_file_time(String receive_file_time) {
		this.receive_file_time = receive_file_time;
	}

	public String getReceive_file_place() {
		return receive_file_place;
	}

	public void setReceive_file_place(String receive_file_place) {
		this.receive_file_place = receive_file_place;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getInfo_source() {
		return info_source;
	}

	public void setInfo_source(String info_source) {
		this.info_source = info_source;
	}

	public String getTel_phone() {
		return tel_phone;
	}

	public void setTel_phone(String tel_phone) {
		this.tel_phone = tel_phone;
	}

	

	public String getFollow_people() {
		return follow_people;
	}

	public void setFollow_people(String follow_people) {
		this.follow_people = follow_people;
	}

	public String getFollow_status() {
		return follow_status;
	}

	public void setFollow_status(String follow_status) {
		this.follow_status = follow_status;
	}

	public String getFollow_logs() {
		return follow_logs;
	}

	public void setFollow_logs(String follow_logs) {
		this.follow_logs = follow_logs;
	}

	public String getShangji_name() {
		return shangji_name;
	}

	public void setShangji_name(String shangji_name) {
		this.shangji_name = shangji_name;
	}

	public String getReady_resource() {
		return ready_resource;
	}

	public void setReady_resource(String ready_resource) {
		this.ready_resource = ready_resource;
	}

	public String getStop_cause() {
		return stop_cause;
	}

	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}

	public String getAcquisition_time() {
		return acquisition_time;
	}

	public void setAcquisition_time(String acquisition_time) {
		this.acquisition_time = acquisition_time;
	}

	public TenderNode() {

	}

	public TenderNode(String tenderid, String tenderno, String contents,
			String category, String purchaser, String project_funds,
			String receive_file_time, String receive_file_place,
			String start_time, String end_time, String info_source,
			String tel_phone, String zhongxin, String pqzhuguan,
			String jiekouren, String follow_people, String follow_status,
			String follow_logs, String shangji_name, String ready_resource,
			String stop_cause, String acquisition_time) {
		super();
		this.tenderid = tenderid;
		this.tenderno = tenderno;
		this.contents = contents;
		this.category = category;
		this.purchaser = purchaser;
		this.project_funds = project_funds;
		this.receive_file_time = receive_file_time;
		this.receive_file_place = receive_file_place;
		this.start_time = start_time;
		this.end_time = end_time;
		this.info_source = info_source;
		this.tel_phone = tel_phone;
		this.zhongxin = zhongxin;
		this.pqzhuguan = pqzhuguan;
		this.jiekouren = jiekouren;
		this.follow_people = follow_people;
		this.follow_status = follow_status;
		this.follow_logs = follow_logs;
		this.shangji_name = shangji_name;
		this.ready_resource = ready_resource;
		this.stop_cause = stop_cause;
		this.acquisition_time = acquisition_time;
	}

	

}
