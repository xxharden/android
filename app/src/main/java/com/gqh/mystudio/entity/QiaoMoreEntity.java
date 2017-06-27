package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/5
 * desc:
 */
public class QiaoMoreEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String AreaID;

    private String UConstellation;

    private String UNickName;

    private String ULOGINID;

    public String getUSignaTure() {
        return USignaTure;
    }

    public void setUSignaTure(String USignaTure) {
        this.USignaTure = USignaTure;
    }

    private String USignaTure;

    private int UID;

    private String Area;

    private String UHeadPortrait;

    private String ApartmentName;

    private int KnockedNum;

    private long UCreatetime;

    private String USex;

    private String UAge;

    public long getUCreatetime() {
        return UCreatetime;
    }
    public void setUCreatetime(long UCreatetime) {
        this.UCreatetime = UCreatetime;
    }
    public void setAreaID(String AreaID){
        this.AreaID = AreaID;
    }
    public String getAreaID(){
        return this.AreaID;
    }
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
    public void setULOGINID(String ULOGINID){
        this.ULOGINID = ULOGINID;
    }
    public String getULOGINID(){
        return this.ULOGINID;
    }
    public void setUID(int UID){
        this.UID = UID;
    }
    public int getUID(){
        return this.UID;
    }
    public void setArea(String Area){
        this.Area = Area;
    }
    public String getArea(){
        return this.Area;
    }
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
    public void setApartmentName(String ApartmentName){
        this.ApartmentName = ApartmentName;
    }
    public String getApartmentName(){
        return this.ApartmentName;
    }
    public void setKnockedNum(int KnockedNum){
        this.KnockedNum = KnockedNum;
    }
    public int getKnockedNum(){
        return this.KnockedNum;
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
