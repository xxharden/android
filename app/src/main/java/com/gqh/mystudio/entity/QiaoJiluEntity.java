package com.gqh.mystudio.entity;

import java.io.Serializable;

/**
 * @author : 红仔
 * @date : 2016/3/5
 * desc: 敲门  记录数
 */
public class QiaoJiluEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int knockNum;

    private int knockedNum;

    public void setKnockNum(int knockNum){
        this.knockNum = knockNum;
    }
    public int getKnockNum(){
        return this.knockNum;
    }
    public void setKnockedNum(int knockedNum){
        this.knockedNum = knockedNum;
    }
    public int getKnockedNum(){
        return this.knockedNum;
    }
}
