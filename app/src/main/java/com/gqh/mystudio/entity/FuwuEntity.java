package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/2/25
 * desc:
 */
public class FuwuEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int WorkOrderID;

    private String orderType;

    private String CREATETime;

    private String STATUS;

    private String Score;

    public void setWorkOrderID(int WorkOrderID){
        this.WorkOrderID = WorkOrderID;
    }
    public int getWorkOrderID(){
        return this.WorkOrderID;
    }
    public void setOrderType(String orderType){
        this.orderType = orderType;
    }
    public String getOrderType(){
        return this.orderType;
    }
    public void setCREATETime(String CREATETime){
        this.CREATETime = CREATETime;
    }
    public String getCREATETime(){
        return this.CREATETime;
    }
    public void setSTATUS(String STATUS){
        this.STATUS = STATUS;
    }
    public String getSTATUS(){
        return this.STATUS;
    }
    public void setScore(String Score){
        this.Score = Score;
    }
    public String getScore(){
        return this.Score;
    }
}
