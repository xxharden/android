package com.gqh.mystudio.activity;

/**
 * @author : 红仔
 * @date : 2016/3/20
 * desc:
 */
public class MyOrderEntity  {

    private long OrderTime;

    private double TotalFee;

    private String UNickName;

    private String State;

    private int OrderID;

    private String Remark;

    public void setOrderTime(int OrderTime){
        this.OrderTime = OrderTime;
    }
    public long getOrderTime(){
        return this.OrderTime;
    }
    public void setTotalFee(double TotalFee){
        this.TotalFee = TotalFee;
    }
    public double getTotalFee(){
        return this.TotalFee;
    }
    public void setUNickName(String UNickName){
        this.UNickName = UNickName;
    }
    public String getUNickName(){
        return this.UNickName;
    }
    public void setState(String State){
        this.State = State;
    }
    public String getState(){
        return this.State;
    }
    public void setOrderID(int OrderID){
        this.OrderID = OrderID;
    }
    public int getOrderID(){
        return this.OrderID;
    }
    public void setRemark(String Remark){
        this.Remark = Remark;
    }
    public String getRemark(){
        return this.Remark;
    }
}
