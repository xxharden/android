package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/15
 * desc:
 */
public class JiaofeiJiluEntity implements Serializable {
    private int TotalFee;

    private int BusinessID;

    private String BusinessFlow;

    private String BusiDate;

    public void setTotalFee(int TotalFee){
        this.TotalFee = TotalFee;
    }
    public int getTotalFee(){
        return this.TotalFee;
    }
    public void setBusinessID(int BusinessID){
        this.BusinessID = BusinessID;
    }
    public int getBusinessID(){
        return this.BusinessID;
    }
    public void setBusinessFlow(String BusinessFlow){
        this.BusinessFlow = BusinessFlow;
    }
    public String getBusinessFlow(){
        return this.BusinessFlow;
    }
    public void setBusiDate(String BusiDate){
        this.BusiDate = BusiDate;
    }
    public String getBusiDate(){
        return this.BusiDate;
    }
}
