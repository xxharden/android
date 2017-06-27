package cn.myapp.entity;

public class MessageItem {

	// Text
	public static final int MESSAGE_TYPE_TEXT = 1;
	// image
	public static final int MESSAGE_TYPE_IMG = 2;
	// file
	public static final int MESSAGE_TYPE_FILE = 3;

	private String userid = "";
	private String msgType;// 消息类型 1.单聊 2.群聊 3.商机信息推送 4.招标信息推送 5.欠费信息推送
	private String name = "";// 消息来自
	private long time;// 消息日期
	private String message = "";// 消息内容
	private String headImg = "";
	private String group = "";
	private boolean isComMeg = true;// 是否为收到的消息

	private int isNew;

	public MessageItem() {
	}

	public MessageItem(String msgType, String userid, String name, long date,
					   String message, String headImg, boolean isComMeg, int isNew) {
		super();
		this.msgType = msgType;
		this.userid = userid;
		this.name = name;
		this.time = date;
		this.message = message;
		this.headImg = headImg;
		this.isComMeg = isComMeg;
		this.isNew = isNew;
	}

	public MessageItem(String msgType, String name, long date, String message,
					   String headImg, boolean isComMeg, int isNew) {
		super();
		this.msgType = msgType;
		this.name = name;
		this.time = date;
		this.message = message;
		this.headImg = headImg;
		this.isComMeg = isComMeg;
		this.isNew = isNew;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDate() {
		return time;
	}

	public void setDate(long date) {
		this.time = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public boolean isComMeg() {
		return isComMeg;
	}

	public void setComMeg(boolean isComMeg) {
		this.isComMeg = isComMeg;
	}

	public static int getMessageTypeText() {
		return MESSAGE_TYPE_TEXT;
	}

	public static int getMessageTypeImg() {
		return MESSAGE_TYPE_IMG;
	}

	public static int getMessageTypeFile() {
		return MESSAGE_TYPE_FILE;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
