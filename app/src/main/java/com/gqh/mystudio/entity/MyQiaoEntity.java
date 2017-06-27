package com.gqh.mystudio.entity;

/**
 * @author : 红仔
 * @date : 2016/3/16
 * desc:
 */
public class MyQiaoEntity {
    private String FName;

    private int FID;

    private String UNickName;

    private int UID;

    private String Area;

    private String UHeadPortrait;

    private String state;

    private String ApartmentName;

    private String Ftype;

    private String USex;

    private String UAge;
    private String UConstellation;
    public String getUConstellation() {
        return UConstellation;
    }

    public void setUConstellation(String UConstellation) {
        this.UConstellation = UConstellation;
    }



    public void setFName(String FName){
        this.FName = FName;
    }
    public String getFName(){
        return this.FName;
    }
    public void setFID(int FID){
        this.FID = FID;
    }
    public int getFID(){
        return this.FID;
    }
    public void setUNickName(String UNickName){
        this.UNickName = UNickName;
    }
    public String getUNickName(){
        return this.UNickName;
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
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setApartmentName(String ApartmentName){
        this.ApartmentName = ApartmentName;
    }
    public String getApartmentName(){
        return this.ApartmentName;
    }
    public void setFtype(String Ftype){
        this.Ftype = Ftype;
    }
    public String getFtype(){
        return this.Ftype;
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
