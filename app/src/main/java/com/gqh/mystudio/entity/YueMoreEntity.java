package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc:约 最新活动
 */
public class YueMoreEntity implements Serializable {
        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;
    private String Name;

    private int PersonNum;

    private int State;

    private String Area;

    private String Intro;

    private int GroupID;

    private String beginDate;

    private String picID;

    private String Admin;

    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setPersonNum(int PersonNum){
        this.PersonNum = PersonNum;
    }
    public int getPersonNum(){
        return this.PersonNum;
    }
    public void setState(int State){
        this.State = State;
    }
    public int getState(){
        return this.State;
    }
    public void setArea(String Area){
        this.Area = Area;
    }
    public String getArea(){
        return this.Area;
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
    public void setBeginDate(String beginDate){
        this.beginDate = beginDate;
    }
    public String getBeginDate(){
        return this.beginDate;
    }
    public void setPicID(String picID){
        this.picID = picID;
    }
    public String getPicID(){
        return this.picID;
    }
    public void setAdmin(String Admin){
        this.Admin = Admin;
    }
    public String getAdmin(){
        return this.Admin;
    }


}
