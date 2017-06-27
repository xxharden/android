package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * Created by 红哥哥 on 2016/7/19.
 * 开锁历史 实体
 */
public class UnlockHisturyEntity implements Serializable {
    private String DoorType;//公寓门-1，房间门-2

    private String USER_ID;//登陆ID

    private String CreateTime;//开门时间

    private int ID;
    private int CustID;//客户ID
    private String Phone;//手机号

    private String DoorName;//公寓（房间）

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setDoorType(String DoorType){
        this.DoorType = DoorType;
    }
    public String getDoorType(){
        return this.DoorType;
    }
    public void setUSER_ID(String USER_ID){
        this.USER_ID = USER_ID;
    }
    public String getUSER_ID(){
        return this.USER_ID;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return this.ID;
    }
    public void setDoorName(String DoorName){
        this.DoorName = DoorName;
    }
    public String getDoorName(){
        return this.DoorName;
    }
}
