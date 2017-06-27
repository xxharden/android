package com.gqh.mystudio.entity;

/**
 * @author : 红仔
 * @date : 2016/3/16
 * desc:
 */
public class MyYueEntity {
    private String Name;

    private int PersonCount;

    private String BeginDate;

    private String UHeadPortrait;

    private String Area;

    private String PicID;

    private String Intro;

    private int GroupID;

    private String Admin;

    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setPersonCount(int PersonCount){
        this.PersonCount = PersonCount;
    }
    public int getPersonCount(){
        return this.PersonCount;
    }
    public void setBeginDate(String BeginDate){
        this.BeginDate = BeginDate;
    }
    public String getBeginDate(){
        return this.BeginDate;
    }
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
    public void setArea(String Area){
        this.Area = Area;
    }
    public String getArea(){
        return this.Area;
    }
    public void setPicID(String PicID){
        this.PicID = PicID;
    }
    public String getPicID(){
        return this.PicID;
    }
    public void setIntro(String Intro){
        this.Intro = Intro;
    }
    public String getIntro(){
        return this.Intro;
    }
    public void setGroupID(int GroupID){
        this.GroupID = GroupID;
    }
    public int getGroupID(){
        return this.GroupID;
    }
    public void setAdmin(String Admin){
        this.Admin = Admin;
    }
    public String getAdmin(){
        return this.Admin;
    }
}
