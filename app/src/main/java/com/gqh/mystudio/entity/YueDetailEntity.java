package com.gqh.mystudio.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 红仔
 * @date : 2016/3/9
 * desc:
 */
public class YueDetailEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String UConstellation;

    private int PersonNum;

    private String Area;

    private int GroupID;

    private String picID;

    private String beginDate;

    private String Admin;

    private String Name;

    private int State;

    private String Notice;

    private String UHeadPortrait;

    private String Intro;

    private String USex;

    private List<Person> Person ;

    private String UAge;

    public void setUConstellation(String UConstellation){
        this.UConstellation = UConstellation;
    }
    public String getUConstellation(){
        return this.UConstellation;
    }
    public void setPersonNum(int PersonNum){
        this.PersonNum = PersonNum;
    }
    public int getPersonNum(){
        return this.PersonNum;
    }
    public void setArea(String Area){
        this.Area = Area;
    }
    public String getArea(){
        return this.Area;
    }
    public void setGroupID(int GroupID){
        this.GroupID = GroupID;
    }
    public int getGroupID(){
        return this.GroupID;
    }
    public void setPicID(String picID){
        this.picID = picID;
    }
    public String getPicID(){
        return this.picID;
    }
    public void setBeginDate(String beginDate){
        this.beginDate = beginDate;
    }
    public String getBeginDate(){
        return this.beginDate;
    }
    public void setAdmin(String Admin){
        this.Admin = Admin;
    }
    public String getAdmin(){
        return this.Admin;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return this.Name;
    }
    public void setState(int State){
        this.State = State;
    }
    public int getState(){
        return this.State;
    }
    public void setNotice(String Notice){
        this.Notice = Notice;
    }
    public String getNotice(){
        return this.Notice;
    }
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
    public void setIntro(String Intro){
        this.Intro = Intro;
    }
    public String getIntro(){
        return this.Intro;
    }
    public void setUSex(String USex){
        this.USex = USex;
    }
    public String getUSex(){
        return this.USex;
    }
    public void setPerson(List<Person> Person){
        this.Person = Person;
    }
    public List<Person> getPerson(){
        return this.Person;
    }
    public void setUAge(String UAge){
        this.UAge = UAge;
    }
    public String getUAge(){
        return this.UAge;
    }

}
