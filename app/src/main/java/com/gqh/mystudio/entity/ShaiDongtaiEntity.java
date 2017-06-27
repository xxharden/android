package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc:左邻右舍 晒最新 实体类
 */
public class ShaiDongtaiEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String AreaID;

    private String UNickName;

    private int ShowID;

    private String Area;

    private String ApartmentName;

    private int knockNum;

    private String USignaTure;

    private String ShowCont;

    private int UID;

    private String CreateTime;

    private String UHeadPortrait;

    private String ShowPic;

    private String USex;

    private int knockedNum;

    public void setAreaID(String AreaID){
        this.AreaID = AreaID;
    }
    public String getAreaID(){
        return this.AreaID;
    }
    public void setUNickName(String UNickName){
        this.UNickName = UNickName;
    }
    public String getUNickName(){
        return this.UNickName;
    }
    public void setShowID(int ShowID){
        this.ShowID = ShowID;
    }
    public int getShowID(){
        return this.ShowID;
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
    public void setKnockNum(int knockNum){
        this.knockNum = knockNum;
    }
    public int getKnockNum(){
        return this.knockNum;
    }
    public void setUSignaTure(String USignaTure){
        this.USignaTure = USignaTure;
    }
    public String getUSignaTure(){
        return this.USignaTure;
    }
    public void setShowCont(String ShowCont){
        this.ShowCont = ShowCont;
    }
    public String getShowCont(){
        return this.ShowCont;
    }
    public void setUID(int UID){
        this.UID = UID;
    }
    public int getUID(){
        return this.UID;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
    public void setShowPic(String ShowPic){
        this.ShowPic = ShowPic;
    }
    public String getShowPic(){
        return this.ShowPic;
    }
    public void setUSex(String USex){
        this.USex = USex;
    }
    public String getUSex(){
        return this.USex;
    }
    public void setKnockedNum(int knockedNum){
        this.knockedNum = knockedNum;
    }
    public int getKnockedNum(){
        return this.knockedNum;
    }
}
