package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/2/27
 * desc:
 */
public class FuwuJinduEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String orderType;

    private int RoomNo;

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    private String workTime;



    private String ORDERDESC;

    private String STATUS;

    private String Score;

    public void setOrderType(String orderType){
        this.orderType = orderType;
    }
    public String getOrderType(){
        return this.orderType;
    }
    public void setRoomNo(int RoomNo){
        this.RoomNo = RoomNo;
    }
    public int getRoomNo(){
        return this.RoomNo;
    }
    public void setORDERDESC(String ORDERDESC){
        this.ORDERDESC = ORDERDESC;
    }
    public String getORDERDESC(){
        return this.ORDERDESC;
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
