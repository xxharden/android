package com.gqh.mystudio.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 红仔
 * @date : 2016/4/6
 * desc:
 */
public class Shops implements Serializable{

    private int shop_id;

    private String shop_name;

    private double longitude;

    private double latitude;

    private int distance;

    private int deal_num;

    private String shop_url;

    private String shop_murl;


    private List<Deals> deals ;

    public void setShop_id(int shop_id){
        this.shop_id = shop_id;
    }
    public int getShop_id(){
        return this.shop_id;
    }
    public void setShop_name(String shop_name){
        this.shop_name = shop_name;
    }
    public String getShop_name(){
        return this.shop_name;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public int getDistance(){
        return this.distance;
    }
    public void setDeal_num(int deal_num){
        this.deal_num = deal_num;
    }
    public int getDeal_num(){
        return this.deal_num;
    }
    public void setShop_url(String shop_url){
        this.shop_url = shop_url;
    }
    public String getShop_url(){
        return this.shop_url;
    }
    public void setShop_murl(String shop_murl){
        this.shop_murl = shop_murl;
    }
    public String getShop_murl(){
        return this.shop_murl;
    }
    public void setDeals(List<Deals> deals){
        this.deals = deals;
    }
    public List<Deals> getDeals(){
        return this.deals;
    }

}
