package com.gqh.mystudio.entity;

/**
 * @author : 红仔
 * @date : 2016/3/15
 * desc:
 */
public class FeiyongTongjiEntity {
    private double LifeTotal;

    private double ShopTotal;

    private double Total;

    public void setLifeTotal(double LifeTotal){
        this.LifeTotal = LifeTotal;
    }
    public double getLifeTotal(){
        return this.LifeTotal;
    }
    public void setShopTotal(double ShopTotal){
        this.ShopTotal = ShopTotal;
    }
    public double getShopTotal(){
        return this.ShopTotal;
    }
    public void setTotal(double Total){
        this.Total = Total;
    }
    public double getTotal(){
        return this.Total;
    }
}
