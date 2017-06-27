package cn.myapp.entity;

import java.io.Serializable;


public class User implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String UConstellation;

	private boolean UPassWord;

	private String UNickName;

	private String RoomNo;

	private String Area;

	private String ApartmentName;

	private String UBirthday;

	private String UContactInfo;

	private int CustID;

	private String UHobby;

	private String USignaTure;

	private String USTATE;

	private String UHome;

	private int RoomID;

	private int UID;

	private String UHeadPortrait;

	private int ApartmentID;

	private String ULoginID;

	private String USex;

	private String UAge;

	public void setUConstellation(String UConstellation){
		this.UConstellation = UConstellation;
	}
	public String getUConstellation(){
		return this.UConstellation;
	}
	public void setUPassWord(boolean UPassWord){
		this.UPassWord = UPassWord;
	}
	public boolean getUPassWord(){
		return this.UPassWord;
	}
	public void setUNickName(String UNickName){
		this.UNickName = UNickName;
	}
	public String getUNickName(){
		return this.UNickName;
	}
	public void setRoomNo(String RoomNo){
		this.RoomNo = RoomNo;
	}
	public String getRoomNo(){
		return this.RoomNo;
	}
	public void setArea(String Area){
		this.Area = Area;
	}
	public String getArea(){
		return this.Area;
	}
	public void setApartmentName(String ApartmentName){
		this.ApartmentName = ApartmentName;
	}
	public String getApartmentName(){
		return this.ApartmentName;
	}
	public void setUBirthday(String UBirthday){
		this.UBirthday = UBirthday;
	}
	public String getUBirthday(){
		return this.UBirthday;
	}
	public void setUContactInfo(String UContactInfo){
		this.UContactInfo = UContactInfo;
	}
	public String getUContactInfo(){
		return this.UContactInfo;
	}
	public void setCustID(int CustID){
		this.CustID = CustID;
	}
	public int getCustID(){
		return this.CustID;
	}
	public void setUHobby(String UHobby){
		this.UHobby = UHobby;
	}
	public String getUHobby(){
		return this.UHobby;
	}
	public void setUSignaTure(String USignaTure){
		this.USignaTure = USignaTure;
	}
	public String getUSignaTure(){
		return this.USignaTure;
	}
	public void setUSTATE(String USTATE){
		this.USTATE = USTATE;
	}
	public String getUSTATE(){
		return this.USTATE;
	}
	public void setUHome(String UHome){
		this.UHome = UHome;
	}
	public String getUHome(){
		return this.UHome;
	}
	public void setRoomID(int RoomID){
		this.RoomID = RoomID;
	}
	public int getRoomID(){
		return this.RoomID;
	}
	public void setUID(int UID){
		this.UID = UID;
	}
	public int getUID(){
		return this.UID;
	}
	public void setUHeadPortrait(String UHeadPortrait){
		this.UHeadPortrait = UHeadPortrait;
	}
	public String getUHeadPortrait(){
		return this.UHeadPortrait;
	}
	public void setApartmentID(int ApartmentID){
		this.ApartmentID = ApartmentID;
	}
	public int getApartmentID(){
		return this.ApartmentID;
	}
	public void setULoginID(String ULoginID){
		this.ULoginID = ULoginID;
	}
	public String getULoginID(){
		return this.ULoginID;
	}
	public void setUSex(String USex){
		this.USex = USex;
	}
	public String getUSex(){
		return this.USex;
	}
	public void setUAge(String UAge){
		this.UAge = UAge;
	}
	public String getUAge(){
		return this.UAge;
	}

}
