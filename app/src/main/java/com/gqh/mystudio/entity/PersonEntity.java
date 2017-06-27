package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/10
 * desc:
 */
public class PersonEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private String UConstellation;

    private String UNickName;

    private String UHome;

    private int UID;

    private String UHeadPortrait;

    private String UBirthday;

    private String USex;

    private String ULoginID;

    private String UHobby;

    private String USignaTure;

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
    public void setUBirthday(String UBirthday){
        this.UBirthday = UBirthday;
    }
    public String getUBirthday(){
        return this.UBirthday;
    }
    public void setUSex(String USex){
        this.USex = USex;
    }
    public String getUSex(){
        return this.USex;
    }
    public void setULoginID(String ULoginID){
        this.ULoginID = ULoginID;
    }
    public String getULoginID(){
        return this.ULoginID;
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
    public void setUAge(String UAge){
        this.UAge = UAge;
    }
    public String getUAge(){
        return this.UAge;
    }

}
