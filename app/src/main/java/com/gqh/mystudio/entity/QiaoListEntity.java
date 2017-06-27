package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/4
 * desc: 敲 列表实体类
 */
public class QiaoListEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int FID;

    private String UNickName;

    private String ULOGINID;

    private String UHeadPortrait;

    private int knockedNum;

    private String USignaTure;

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
    public void setULOGINID(String ULOGINID){
        this.ULOGINID = ULOGINID;
    }
    public String getULOGINID(){
        return this.ULOGINID;
    }
    public void setUHeadPortrait(String UHeadPortrait){
        this.UHeadPortrait = UHeadPortrait;
    }
    public String getUHeadPortrait(){
        return this.UHeadPortrait;
    }
    public void setKnockedNum(int knockedNum){
        this.knockedNum = knockedNum;
    }
    public int getKnockedNum(){
        return this.knockedNum;
    }
    public void setUSignaTure(String USignaTure){
        this.USignaTure = USignaTure;
    }
    public String getUSignaTure(){
        return this.USignaTure;
    }
}
