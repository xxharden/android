package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/4/6
 * desc:
 */
public class Deals  implements Serializable {
    private int deal_id;

    private String image;

    private String tiny_image;

    private String title;

    private String min_title;

    private String description;

    private int market_price;

    private int current_price;

    private int promotion_price;

    private int sale_num;

    private double score;

    private int comment_num;

    private String deal_url;

    private String deal_murl;

    public void setDeal_id(int deal_id){
        this.deal_id = deal_id;
    }
    public int getDeal_id(){
        return this.deal_id;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setTiny_image(String tiny_image){
        this.tiny_image = tiny_image;
    }
    public String getTiny_image(){
        return this.tiny_image;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setMin_title(String min_title){
        this.min_title = min_title;
    }
    public String getMin_title(){
        return this.min_title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setMarket_price(int market_price){
        this.market_price = market_price;
    }
    public int getMarket_price(){
        return this.market_price;
    }
    public void setCurrent_price(int current_price){
        this.current_price = current_price;
    }
    public int getCurrent_price(){
        return this.current_price;
    }
    public void setPromotion_price(int promotion_price){
        this.promotion_price = promotion_price;
    }
    public int getPromotion_price(){
        return this.promotion_price;
    }
    public void setSale_num(int sale_num){
        this.sale_num = sale_num;
    }
    public int getSale_num(){
        return this.sale_num;
    }
    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return this.score;
    }
    public void setComment_num(int comment_num){
        this.comment_num = comment_num;
    }
    public int getComment_num(){
        return this.comment_num;
    }
    public void setDeal_url(String deal_url){
        this.deal_url = deal_url;
    }
    public String getDeal_url(){
        return this.deal_url;
    }
    public void setDeal_murl(String deal_murl){
        this.deal_murl = deal_murl;
    }
    public String getDeal_murl(){
        return this.deal_murl;
    }
}
