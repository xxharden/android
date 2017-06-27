package com.gqh.mystudio.entity;

/**
 * @author : 红仔
 * @date : 2016/3/20
 * desc:
 */
public class MyOrderProductEntity {
    private String PicPath;

    private int Number;

    private double Price;

    private int ProductID;

    private String ProName;

    private double RealPrice;

    private String Unit;

    public void setPicPath(String PicPath){
        this.PicPath = PicPath;
    }
    public String getPicPath(){
        return this.PicPath;
    }
    public void setNumber(int Number){
        this.Number = Number;
    }
    public int getNumber(){
        return this.Number;
    }
    public void setPrice(double Price){
        this.Price = Price;
    }
    public double getPrice(){
        return this.Price;
    }
    public void setProductID(int ProductID){
        this.ProductID = ProductID;
    }
    public int getProductID(){
        return this.ProductID;
    }
    public void setProName(String ProName){
        this.ProName = ProName;
    }
    public String getProName(){
        return this.ProName;
    }
    public void setRealPrice(int RealPrice){
        this.RealPrice = RealPrice;
    }
    public double getRealPrice(){
        return this.RealPrice;
    }
    public void setUnit(String Unit){
        this.Unit = Unit;
    }
    public String getUnit(){
        return this.Unit;
    }
}
