package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/10
 * desc:
 */
public class NumberEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private int number;

    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return this.number;
    }
}
