package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/14
 * desc:
 */
public class DaijiaofeiEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String FeeItem;

    private int Amount;

    private int BillID;

    private String BillDesc;

    public void setFeeItem(String FeeItem){
        this.FeeItem = FeeItem;
    }
    public String getFeeItem(){
        return this.FeeItem;
    }
    public void setAmount(int Amount){
        this.Amount = Amount;
    }
    public int getAmount(){
        return this.Amount;
    }
    public void setBillID(int BillID){
        this.BillID = BillID;
    }
    public int getBillID(){
        return this.BillID;
    }
    public void setBillDesc(String BillDesc){
        this.BillDesc = BillDesc;
    }
    public String getBillDesc(){
        return this.BillDesc;
    }
}
