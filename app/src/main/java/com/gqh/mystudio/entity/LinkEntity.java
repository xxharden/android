package com.gqh.mystudio.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author : 红仔
 * @date : 2016/4/6
 * desc:
 */
public class LinkEntity implements Serializable{
    private int total;

    private List<Shops> shops ;

    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setShops(List<Shops> shops){
        this.shops = shops;
    }
    public List<Shops> getShops(){
        return this.shops;
    }
}
