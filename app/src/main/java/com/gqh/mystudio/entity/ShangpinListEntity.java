package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/18
 * desc:
 */
public class ShangpinListEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int Count;

    public int getNumber() {
        return Number;
    }

    private int myNumber=1;

    public int getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(int myNumber) {
        this.myNumber = myNumber;
    }

    public void setNumber(int number) {
        Number = number;
    }

    private int Number;

    private String Desc;//详情
    private String PicPath;

    private String DetailPic;

    private double Price;

    private int ProductID;

    private double RealPrice;

    private String ProName;

    private String Unit;

    public String getDetailPic() {
        return DetailPic;
    }

    public void setDetailPic(String detailPic) {
        DetailPic = detailPic;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setCount(int Count){
        this.Count = Count;
    }
    public int getCount(){
        return this.Count;
    }
    public void setPicPath(String PicPath){
        this.PicPath = PicPath;
    }
    public String getPicPath(){
        return this.PicPath;
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
    public void setRealPrice(double RealPrice){
        this.RealPrice = RealPrice;
    }
    public double getRealPrice(){
        return this.RealPrice;
    }
    public void setProName(String ProName){
        this.ProName = ProName;
    }
    public String getProName(){
        return this.ProName;
    }
    public void setUnit(String Unit){
        this.Unit = Unit;
    }
    public String getUnit(){
        return this.Unit;
    }
}
