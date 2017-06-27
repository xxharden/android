package com.gqh.mystudio.entity;

import java.util.List;

/**
 * @author : 红仔
 * @date : 2016/3/9
 * desc:
 */
public class ShaiDetailEntity {
    private String UConstellation;

    private String UNickName;

    private String RoomNo;

    private List<ShaiDetaiShowEntity> Show ;

    private String Area;

    private String ApartmentName;

    private String UBirthday;

    private String UHobby;

    private String USignaTure;

    private String USTATE;

    private String UHome;

    private int UID;

    private String UHeadPortrait;

    private String ULoginID;

    private String USex;

    private String UAge;

    public void setUConstellation(String UConstellation){
        this.UConstellation = UConstellation;
    }
    public String getUConstellation(){
        return this.UConstellation;
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
    public void setShow(List<ShaiDetaiShowEntity> Show){
        this.Show = Show;
    }
    public List<ShaiDetaiShowEntity> getShow(){
        return this.Show;
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
