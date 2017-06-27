package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/7
 * desc:
 */
public class Person implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String ULOGINID;

    private int UID;

    private String UHeadPortrait;

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
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
}
